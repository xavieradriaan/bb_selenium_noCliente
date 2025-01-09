package org.bancobolivariano.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static Properties getProperties () {
        var propsPath = System.getProperty("application.properties.path");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(propsPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
}