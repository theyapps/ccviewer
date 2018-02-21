package com.theyapps.ccstreamviewer.controller;

import com.theyapps.ccstreamviewer.entity.ZMMonitor;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Interfaces with zoneminder server API to read in status
 * and send commands to the zoneminder instance.
 *
 * Documentation: http://zoneminder.readthedocs.io/en/stable/api.html
 */
public class Zoneminder extends Observable {
    private static Logger log = Logger.getLogger(Zoneminder.class);

    private String m_hostAddress;
    private String m_authCookie;

    private ArrayList<ZMMonitor> m_monList;
    private LocalDateTime m_monListDate;

    private boolean m_authFailure;


    private static Zoneminder instance;

    /**
     * Assumes host 127.0.0.1:8080/zm
     */
    private Zoneminder(){
        m_hostAddress = PropertiesReader.get("server.path");
        m_authFailure = false;

    }

    public static Zoneminder getInstance(){
        if(instance == null){
            instance = new Zoneminder();
        }
        return instance;
    }

    /**
     * Connect to the zoneminder server.
     * @return
     */
    public boolean connect(){
        m_authCookie = loginAndGetCookie(PropertiesReader.get("server.user"), PropertiesReader.get("server.pass"));
        return m_authCookie.startsWith("ZMSESSID");
    }

    /**
     * The version of the current zoneminder server.
     * @return The version of the current zoneminder server
     */
    public String getVersion(){
        String json = readJsonFromPath("/host/getVersion.json", "GET");
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString("version");
        }
        return null;
    }

    /**
     * Get the api version of the current running zm instance.
     * @return The current API version
     */
    public String getApiVersion(){
        String json = readJsonFromPath("/host/getVersion.json", "GET");
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString("apiversion");
        }
        return null;
    }

    /**
     * Reads the current status of the zoneminder service
     * API: http://server/zm/api/host/daemonCheck.json # 1 = ZM running 0=not running
     * @return True if zoneminder service is running
     */
    public boolean isRunning(){
        String resp = readJsonFromPath("/host/daemonCheck.json", "GET");

        if(resp != null){
            JSONObject jsonObject = new JSONObject(resp);
            return (Integer) jsonObject.get("result") == 1;
        }

        return false;
    }

    /**
     * Sets the instance of zoneminder to running if run is true
     * and not running is run is false. Method is synchronous.
     *
     * API: http://server/zm/api/states/change/stop.json #Stops ZM
     *      http://server/zm/api/states/change/start.json #Starts ZM
     * @param run
     */
    public void setRunning(boolean run){
        if(run){
            readJsonFromPath("/states/change/start.json", "POST");
        }
        else {
            readJsonFromPath("/states/change/stop.json", "POST");
        }
    }

    /**
     * Asynch method to set the run state of the server.
     * @param run
     */
    public void setRunningAsync(final boolean run){
        new Thread(() -> setRunning(run)).start();
    }

    public ArrayList<ZMMonitor> getMonitorList(){
        if(m_monList == null){
            if(m_monListDate == null || m_monListDate.plusHours(1).isBefore(LocalDateTime.now())) {
                ArrayList<ZMMonitor> monList = new ArrayList<>();
                String monListJson;

                monListJson = readJsonFromPath("monitors.json", "GET");

                if(monListJson != null) {
                    JSONObject jsonMonsObject = new JSONObject(monListJson);

                    JSONArray jsonMonArray = new JSONArray(jsonMonsObject.get("monitors").toString());
                    for(int i = 0; i < jsonMonArray.length(); i++){
                        monList.add(new ZMMonitor(((JSONObject)jsonMonArray.get(i)).getJSONObject("Monitor")));
                    }
                }
                m_monList = monList;
                m_monListDate = LocalDateTime.now();
            }
        }


        return m_monList;
    }

    /**
     * Assuming status of 4 means not alarmed and all else means alarmed.
     * May need to look into this some more.
     * @param id Id of cam to check
     * @return boolean value of alarm
     */
    public boolean isAlarmed(int id){
        String json = readJsonFromPath("/monitors/alarm/id:" + id + "/command:status.json", "GET");
        JSONObject jsonObject = new JSONObject(json);
        int status = -1;
        try {
            status = jsonObject.getInt("status");
        } catch (JSONException | NumberFormatException e) {
            log.warn("Failed to get server status, server may be offline");
        }
        return status != 4;
    }

    /**
     * Read JSON from given path at configured server.
     * @param path Path after api
     * @return Http response
     */
    private String readJsonFromPath(String path, String method){
        String apiAddress = String.format("%s/api/%s", m_hostAddress, path);

        try {
            URL url = new URL(apiAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            // Determine if we have a cookie to attach to the request
            if(m_authCookie != null){
                conn.setRequestProperty("Cookie", m_authCookie);
            }

            conn.connect();

            switch(conn.getResponseCode()){
                case HttpURLConnection.HTTP_OK:
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer buffer = new StringBuffer();
                    char chars[] = new char[1024];
                    int read;

                    while ((read = reader.read(chars)) != -1){
                        buffer.append(chars, 0, read);
                    }

                    return buffer.toString();

                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    log.info("Authentication required, trying to authenticate now.");

                    if(connect()) {
                        setAuthFailure(false);
                        return readJsonFromPath(path, method);
                    }
                    else {
                        log.debug("Setting auth failure to true.");
                        setAuthFailure(true);
                    }


            }

        } catch (java.net.ConnectException e){
            System.out.println("Failure to connect, unable to connect.");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String sendJsonToPath(String json){
        return null;
    }

    private String loginAndGetCookie(String user, String pass){
        try {
            String postParams =
                    String.format("username=%s&password=%s&action=login&view=console", user, pass);

            byte[] postData       = postParams.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;

            URL url = new URL(m_hostAddress + "/index.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );

            conn.setDoOutput(true);

            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            }

            conn.connect();

            switch(conn.getResponseCode()){
                case HttpURLConnection.HTTP_OK:
                    return conn.getHeaderField("Set-Cookie");
                default:
                    System.out.println("Failure to connect, invalid response code ("
                            + conn.getResponseCode() + " " + conn.getResponseMessage() + ").");
                    break;
            }

        } catch (java.net.ConnectException e){
            System.out.println("Failure to connect.");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAuthFailure() {
        return m_authFailure;
    }

    public void setAuthFailure(boolean m_authFailure) {
        this.m_authFailure = m_authFailure;
        setChanged();
        notifyObservers(this);
    }


}
