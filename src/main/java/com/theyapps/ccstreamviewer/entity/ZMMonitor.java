package com.theyapps.ccstreamviewer.entity;

import com.theyapps.ccstreamviewer.views.VideoPanel;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import java.awt.*;


/**
 * Models the monitor object from zoneminder
 */
public class ZMMonitor {
    Logger log = Logger.getLogger(ZMMonitor.class);

    private int id;
    private String name;
    private int serverId;
    private String type;
    private String function;
    private int enabled;
    private String linkedMonitors;
    private String triggers;
    private String device;
    private int channel;
    private int format;
    private boolean V4LMultiBuffer;
    private int V4LCapturePerFrame;
    private String protocol;
    private String method;
    private String host;
    private String port;
    private String subPath;
    private String path;
    private String options;
    private String user;
    private String pass;
    private int width;
    private int height;
    private int colours;
    private int palette;
    private int orientation;
    private int deinterlacing;
    private boolean rtspDescribe;
    private int brightness;
    private int contrast;
    private int hue;
    private int color;
    private String eventPrefix;
    private String labelFormat;
    private int labelX;
    private int labelY;
    private int labelSize;
    private int imageBufferCount;
    private int warmupCount;
    private int preEventCount;
    private int postEventCount;
    private int streamReplayBuffer;
    private int alarmFrameCount;
    private int sectionLength;
    private int frameSkip;
    private int motionFrameSkip;
    private float analysisFPS;
    private int analysisUpdateDelay;
    private float maxFPS;
    private float alarmMaxFPS;
    private int FPSReportInterval;
    private int refBlendPerc;
    private int alarmRefBlendPerc;
    private int controllable;
    private int controlID;
    private String controlDevice;
    private String controlAddress;
    private int autoStopTimeout;
    private int trackMotion;
    private int trackDelay;
    private int returnLocation;
    private int returnDelay;
    private String defaultView;
    private int defaultRate;
    private int defaultScale;
    private String signalCheckColour;
    private String webColour;
    private boolean exif;
    private int sequence;

    public ZMMonitor(JSONObject jo) {
        try {
            setId(jo.getInt("Id"));
            setName(jo.getString("Name"));
            setServerId(jo.getInt("ServerId"));
            setType(jo.getString("Type"));
            setFunction(jo.getString("Function"));
            setEnabled(jo.getInt("Enabled"));
            setLinkedMonitors(jo.getString("LinkedMonitors"));
            setTriggers(jo.getString("Triggers"));
            setDevice(jo.getString("Device"));
            setChannel(jo.getInt("Channel"));
            setFormat(jo.getInt("Format"));
            setV4LMultiBuffer(jo.getBoolean("V4LMultiBuffer"));
            setV4LCapturePerFrame(jo.getInt("V4LCapturesPerFrame"));
            setProtocol(jo.getString("Protocol"));
            setMethod(jo.getString("Method"));
            setHost(jo.getString("Host"));
            setPort(jo.getString("Port"));
            setSubPath(jo.getString("SubPath"));
            setPath(jo.getString("Path"));
            setOptions(jo.getString("Options"));
            setUser(jo.getString("User"));
            setPass(jo.getString("Pass"));
            setWidth(jo.getInt("Width"));
            setHeight(jo.getInt("Height"));
            setColours(jo.getInt("Colours"));
            setPalette(jo.getInt("Palette"));
            setOrientation(jo.getInt("Orientation"));
            setDeinterlacing(jo.getInt("Deinterlacing"));
            setRtspDescribe(jo.getBoolean("RTSPDescribe"));
            setBrightness(jo.getInt("Brightness"));
            setContrast(jo.getInt("Contrast"));
            setHue(jo.getInt("Hue"));
            setColor(jo.getInt("Colour"));
            setEventPrefix(jo.getString("EventPrefix"));
            setLabelFormat(jo.getString("LabelFormat"));
            setLabelX(jo.getInt("LabelX"));
            setLabelY(jo.getInt("LabelY"));
            setLabelSize(jo.getInt("LabelSize"));
            setImageBufferCount(jo.getInt("ImageBufferCount"));
            setWarmupCount(jo.getInt("WarmupCount"));
            setPreEventCount(jo.getInt("PreEventCount"));
            setPostEventCount(jo.getInt("PostEventCount"));
            setStreamReplayBuffer(jo.getInt("StreamReplayBuffer"));
            setAlarmFrameCount(jo.getInt("AlarmFrameCount"));
            setSectionLength(jo.getInt("SectionLength"));
            setFrameSkip(jo.getInt("FrameSkip"));
            setMotionFrameSkip(jo.getInt("MotionFrameSkip"));
            setAnalysisFPS(jo.getFloat("AnalysisFPS"));
            setAnalysisUpdateDelay(jo.getInt("AnalysisUpdateDelay"));
            setMaxFPS(jo.getFloat("MaxFPS"));
            setAlarmMaxFPS(jo.getFloat("AlarmMaxFPS"));
            setFPSReportInterval(jo.getInt("FPSReportInterval"));
            setRefBlendPerc(jo.getInt("RefBlendPerc"));
            setAlarmRefBlendPerc(jo.getInt("AlarmRefBlendPerc"));
            setControllable(jo.getInt("Controllable"));
            setControlID(jo.getInt("ControlId"));
            setControlDevice(String.valueOf(jo.get("ControlDevice")));
            setControlAddress(String.valueOf(jo.get("ControlAddress")));
            setTrackMotion(Integer.valueOf(String.valueOf(jo.get("TrackMotion"))));
            setTrackDelay(jo.getInt("TrackDelay"));
            setReturnLocation(jo.getInt("ReturnLocation"));
            setReturnDelay(jo.getInt("ReturnDelay"));
            setDefaultView(jo.getString("DefaultView"));
            setDefaultRate(jo.getInt("DefaultRate"));
            setDefaultScale(jo.getInt("DefaultScale"));
            setSignalCheckColour(jo.getString("SignalCheckColour"));
            setWebColour(jo.getString("WebColour"));
            setExif(jo.getBoolean("Exif"));
            setSequence(jo.getInt("Sequence"));

        } catch (JSONException e) {
            log.error("Some properties may have failed to load from JSON.");
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getLinkedMonitors() {
        return linkedMonitors;
    }

    public void setLinkedMonitors(String linkedMonitors) {
        this.linkedMonitors = linkedMonitors;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public boolean isV4LMultiBuffer() {
        return V4LMultiBuffer;
    }

    public void setV4LMultiBuffer(boolean v4LMultiBuffer) {
        V4LMultiBuffer = v4LMultiBuffer;
    }

    public int getV4LCapturePerFrame() {
        return V4LCapturePerFrame;
    }

    public void setV4LCapturePerFrame(int v4LCapturePerFrame) {
        V4LCapturePerFrame = v4LCapturePerFrame;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColours() {
        return colours;
    }

    public void setColours(int colours) {
        this.colours = colours;
    }

    public int getPalette() {
        return palette;
    }

    public void setPalette(int palette) {
        this.palette = palette;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getDeinterlacing() {
        return deinterlacing;
    }

    public void setDeinterlacing(int deinterlacing) {
        this.deinterlacing = deinterlacing;
    }

    public boolean isRtspDescribe() {
        return rtspDescribe;
    }

    public void setRtspDescribe(boolean rtspDescribe) {
        this.rtspDescribe = rtspDescribe;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getContrast() {
        return contrast;
    }

    public void setContrast(int contrast) {
        this.contrast = contrast;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getEventPrefix() {
        return eventPrefix;
    }

    public void setEventPrefix(String eventPrefix) {
        this.eventPrefix = eventPrefix;
    }

    public String getLabelFormat() {
        return labelFormat;
    }

    public void setLabelFormat(String labelFormat) {
        this.labelFormat = labelFormat;
    }

    public int getLabelX() {
        return labelX;
    }

    public void setLabelX(int labelX) {
        this.labelX = labelX;
    }

    public int getLabelY() {
        return labelY;
    }

    public void setLabelY(int labelY) {
        this.labelY = labelY;
    }

    public int getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }

    public int getImageBufferCount() {
        return imageBufferCount;
    }

    public void setImageBufferCount(int imageBufferCount) {
        this.imageBufferCount = imageBufferCount;
    }

    public int getWarmupCount() {
        return warmupCount;
    }

    public void setWarmupCount(int warmupCount) {
        this.warmupCount = warmupCount;
    }

    public int getPreEventCount() {
        return preEventCount;
    }

    public void setPreEventCount(int preEventCount) {
        this.preEventCount = preEventCount;
    }

    public int getPostEventCount() {
        return postEventCount;
    }

    public void setPostEventCount(int postEventCount) {
        this.postEventCount = postEventCount;
    }

    public int getStreamReplayBuffer() {
        return streamReplayBuffer;
    }

    public void setStreamReplayBuffer(int streamReplayBuffer) {
        this.streamReplayBuffer = streamReplayBuffer;
    }

    public int getAlarmFrameCount() {
        return alarmFrameCount;
    }

    public void setAlarmFrameCount(int alarmFrameCount) {
        this.alarmFrameCount = alarmFrameCount;
    }

    public int getSectionLength() {
        return sectionLength;
    }

    public void setSectionLength(int sectionLength) {
        this.sectionLength = sectionLength;
    }

    public int getFrameSkip() {
        return frameSkip;
    }

    public void setFrameSkip(int frameSkip) {
        this.frameSkip = frameSkip;
    }

    public int getMotionFrameSkip() {
        return motionFrameSkip;
    }

    public void setMotionFrameSkip(int motionFrameSkip) {
        this.motionFrameSkip = motionFrameSkip;
    }

    public float getAnalysisFPS() {
        return analysisFPS;
    }

    public void setAnalysisFPS(float analysisFPS) {
        this.analysisFPS = analysisFPS;
    }

    public int getAnalysisUpdateDelay() {
        return analysisUpdateDelay;
    }

    public void setAnalysisUpdateDelay(int analysisUpdateDelay) {
        this.analysisUpdateDelay = analysisUpdateDelay;
    }

    public float getMaxFPS() {
        return maxFPS;
    }

    public void setMaxFPS(float maxFPS) {
        this.maxFPS = maxFPS;
    }

    public float getAlarmMaxFPS() {
        return alarmMaxFPS;
    }

    public void setAlarmMaxFPS(float alarmMaxFPS) {
        this.alarmMaxFPS = alarmMaxFPS;
    }

    public int getFPSReportInterval() {
        return FPSReportInterval;
    }

    public void setFPSReportInterval(int FPSReportInterval) {
        this.FPSReportInterval = FPSReportInterval;
    }

    public int getRefBlendPerc() {
        return refBlendPerc;
    }

    public void setRefBlendPerc(int refBlendPerc) {
        this.refBlendPerc = refBlendPerc;
    }

    public int getAlarmRefBlendPerc() {
        return alarmRefBlendPerc;
    }

    public void setAlarmRefBlendPerc(int alarmRefBlendPerc) {
        this.alarmRefBlendPerc = alarmRefBlendPerc;
    }

    public int getControllable() {
        return controllable;
    }

    public void setControllable(int controllable) {
        this.controllable = controllable;
    }

    public int getControlID() {
        return controlID;
    }

    public void setControlID(int controlID) {
        this.controlID = controlID;
    }

    public String getControlDevice() {
        return controlDevice;
    }

    public void setControlDevice(String controlDevice) {
        this.controlDevice = controlDevice;
    }

    public String getControlAddress() {
        return controlAddress;
    }

    public void setControlAddress(String controllAddress) {
        this.controlAddress = controllAddress;
    }

    public int getAutoStopTimeout() {
        return autoStopTimeout;
    }

    public void setAutoStopTimeout(int autoStopTimeout) {
        this.autoStopTimeout = autoStopTimeout;
    }

    public int getTrackMotion() {
        return trackMotion;
    }

    public void setTrackMotion(int trackMotion) {
        this.trackMotion = trackMotion;
    }

    public int getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(int returnLocation) {
        this.returnLocation = returnLocation;
    }

    public int getReturnDelay() {
        return returnDelay;
    }

    public void setReturnDelay(int returnDelay) {
        this.returnDelay = returnDelay;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public int getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(int defaultRate) {
        this.defaultRate = defaultRate;
    }

    public int getDefaultScale() {
        return defaultScale;
    }

    public void setDefaultScale(int defaultScale) {
        this.defaultScale = defaultScale;
    }

    public String getSignalCheckColour() {
        return signalCheckColour;
    }

    public void setSignalCheckColour(String signalCheckColour) {
        this.signalCheckColour = signalCheckColour;
    }

    public String getWebColour() {
        return webColour;
    }

    public void setWebColour(String webColour) {
        this.webColour = webColour;
    }

    public boolean isExif() {
        return exif;
    }

    public void setExif(boolean exif) {
        this.exif = exif;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getTrackDelay() {
        return trackDelay;
    }

    public void setTrackDelay(int trackDelay) {
        this.trackDelay = trackDelay;
    }
}
