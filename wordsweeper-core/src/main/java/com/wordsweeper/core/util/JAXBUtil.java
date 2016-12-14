package com.wordsweeper.core.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Utilities for XML operations
 *
 * @author francisco
 */
public class JAXBUtil {

    /**
     * Pretty println XML.
     *
     * @param obj the obj
     */
    public static void prettyPrintln(Object obj) {
        if (obj == null) {
            return;
        }
        System.out.println(serialize(obj, true));
    }

    /**
     * Serialize an object to an XML string
     *
     * @param object the object
     * @return the XML string representation of the object
     */
    public static String serialize(Object object) {
        return serialize(object, false);
    }

    /**
     * Serialize an object to an XML string
     *
     * @param object    the object
     * @param formatted true if formatted, false otherwise
     * @return* @return the XML string representation of the object
     */
    public static String serialize(Object object, boolean formatted) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            if (formatted) {
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            }
            jaxbMarshaller.marshal(object, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return sw.toString();
    }

    /**
     * Deserialize an XML string into an object
     *
     * @param xml   the xml string
     * @param clazz the class of the object
     * @param <T>   the type of the object
     * @return the object from the deserialized XML string
     */
    public static <T> T deserialize(String xml, Class<T> clazz) {
        try {
            JAXBContext requestContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = requestContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            System.err.println("Unable to deserialize request from Remote Client.");
            return null;
        }
    }

    /**
     * Extract the XML message and construct String object based on
     * the terminator string (either "</request>" or "</response>"). Returns
     * null if communication is interrupted in any way.
     *
     * @param in         the in
     * @param terminator the terminator
     * @return the xml string from reader
     */
    public static String getXmlStringFromReader(BufferedReader in, String terminator) {
        try {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            // The default capacity of 16 is way too low
            StringBuilder buf = new StringBuilder(1024);
            buf.append(line);

            while (!terminator.equals(buf.substring(buf.length() - terminator.length()))) {
                line = in.readLine();
                if (line == null) {
                    return null;
                }
                buf.append(line);
            }

            return buf.toString();
        } catch (IOException ioe) {
            return null;
        }
    }
}
