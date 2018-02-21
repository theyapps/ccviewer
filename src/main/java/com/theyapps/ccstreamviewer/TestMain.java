package com.theyapps.ccstreamviewer;

import com.theyapps.ccstreamviewer.controller.Zoneminder;
import com.theyapps.ccstreamviewer.entity.ZMMonitor;
import com.theyapps.ccstreamviewer.properties.PropertiesReader;
import org.apache.log4j.Logger;

import javax.naming.AuthenticationException;

public class TestMain {
    private static Logger log = Logger.getLogger(TestMain.class);
    public static void main(String[] args) {
        System.out.println("Running test");

        Zoneminder zm;

        zm = Zoneminder.getInstance();


    }
}
