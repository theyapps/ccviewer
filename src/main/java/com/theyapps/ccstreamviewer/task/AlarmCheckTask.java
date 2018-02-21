package com.theyapps.ccstreamviewer.task;

import com.theyapps.ccstreamviewer.controller.Zoneminder;
import com.theyapps.ccstreamviewer.entity.ZMMonitor;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;

import javax.naming.AuthenticationException;
import java.util.TimerTask;

public class AlarmCheckTask extends TimerTask{
    Zoneminder m_zm;

    public AlarmCheckTask(){
        m_zm = Zoneminder.getInstance();
    }

    @Override
    public void run() {
        for(ZMMonitor mon : m_zm.getMonitorList()){

        }
    }
}
