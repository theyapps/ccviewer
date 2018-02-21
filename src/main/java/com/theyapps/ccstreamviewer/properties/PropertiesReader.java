package com.theyapps.ccstreamviewer.properties;

import org.apache.log4j.Logger;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Reads properties from properties/ccstreamviewer.properties
 */
public class PropertiesReader {
    private static Logger log = Logger.getLogger(PropertiesReader.class);

    private static ResourceBundle res;
    private PropertiesReader(){}

    public static String get(String p){
        if(res == null){
            res = ResourceBundle.getBundle("properties/ccstreamviewer");

        }

        return res.getString(p);
    }

    public static Color getColor(String p){
        return new Color(Integer.parseInt(get(p), 16));
    }

}
