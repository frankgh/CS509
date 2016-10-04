package com.wordsweeper.server.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by francisco on 10/4/16.
 */
public class JAXBUtil {

    public static void println(Object obj) {
        if (obj == null) {
            return;
        }
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(obj, sw);
            System.out.println(sw.toString());
        } catch (JAXBException e) {
            System.err.println("Unable to marshall object");
        }
    }
}
