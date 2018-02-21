package com.theyapps.ccstreamviewer.views;

import com.theyapps.ccstreamviewer.controller.Zoneminder;
import com.theyapps.ccstreamviewer.entity.ZMMonitor;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;
import org.apache.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.Timer;

public class VideoContentPanel extends JPanel {
    private Logger log = Logger.getLogger(VideoContentPanel.class);
    private Zoneminder m_zm;
    private MainFrame mainFrame;

    LinkedList<VideoPanel> vpl;

    public VideoContentPanel(MainFrame mf){
        m_zm = Zoneminder.getInstance();

        mainFrame = mf;

        log.debug(String.format("Monitor List Size: %d", m_zm.getMonitorList().size()));

        setLayout(new FlowLayout());

        setBackground(PropertiesReader.getColor("views.contentpanel.backgroundcolor"));

        vpl = new LinkedList<>();
        for(ZMMonitor mon : m_zm.getMonitorList()){
            VideoPanel vp = new VideoPanel(mainFrame, mon);
            vpl.add(vp);
            add(vp);
        }

        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                for(ZMMonitor mon : m_zm.getMonitorList()){
                    for(VideoPanel vp : vpl){
                        if(vp.getZmMonitor().getId() == mon.getId()){
                            vp.setAlarm(m_zm.isAlarmed(mon.getId()));
                        }
                    }
                }
            }
        }, 500, 1000);

    }

    public void play(){
        for(VideoPanel vp : vpl){
            vp.play();
        }
    }
}
