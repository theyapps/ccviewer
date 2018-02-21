package com.theyapps.ccstreamviewer.views;

import com.theyapps.ccstreamviewer.controller.Zoneminder;
import com.theyapps.ccstreamviewer.entity.ZMMonitor;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;
import org.apache.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsContentPanel extends JPanel {
    private Logger log = Logger.getLogger(SettingsContentPanel.class);
    private Zoneminder m_zm;
    private MainFrame mainFrame;

    public SettingsContentPanel(MainFrame mf){
        m_zm = Zoneminder.getInstance();

        mainFrame = mf;
        setLayout(new FlowLayout());
        setBackground(Color.GRAY);
    }
}
