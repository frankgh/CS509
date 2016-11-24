package com.wordsweeper.server.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The utility class that gets/sets a list of properties from/to a property file, given the property file name.
 *
 * @author francisco
 */
public class PropertiesUtil {

    /**
     * Method that gets properties from property file.
     *
     * @param filePath the file path
     * @param fileName the name of a property file (i.e., dealers-sql.properties)
     * @return java.util.Properties object, or empty java.util.Properties
     */
    public static Properties getProperties(String filePath, String fileName) {
        Properties prop = new Properties();
        try {
            if (null == filePath) {
                prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
            } else {
                InputStream in = new FileInputStream(filePath + getPathDelim() + fileName);
                prop.load(in);
            }
        } catch (IOException e) {
            System.err.println("Cannot read prop file exception: {}" + e);
        } catch (NullPointerException e) {
            System.err.println("Cannot find prop file exception: {}" + e);
        }
        return prop;
    }

    /**
     * Gets the correct delimeter.
     *
     * @return the path delim
     */
    private static String getPathDelim() {
        return System.getProperty("file.separator", "/");
    }

}
