package server;

import com.wordsweeper.server.xml.ConnectResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import com.wordsweeper.server.util.JAXBUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * Thread to handle individual requests from a client.
 */
public class ServerThread extends Thread implements ClientState {
    final Socket client;             // Socket used by thread to communicate with client.
    final BufferedReader fromClient; // Used to process strings from client.
    final PrintWriter toClient;      // Used to send strings to client.
    final IProtocolHandler handler;  // Handler to process protocol.
    final Server server;             // Server for which we are operating.
    final String id;                 // Associated unique ID for this thread.
    Object data;                     // User-defined object associated with each thread.
    private Request request;

    /**
     * Create objects to handle input/output to client.
     */
    ServerThread(Server srv, Socket s, IProtocolHandler h) throws IOException {
        server = srv;
        fromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        toClient = new PrintWriter(s.getOutputStream(), true);
        client = s;
        handler = h;
        id = UUID.randomUUID().toString();
    }

    /**
     * Thread receives strings from the client, processes them and returns
     * result back to the client.
     * <p>
     * Each client connection is assigned a unique identifier by which one can reference
     * it in the future.
     * <p>
     * If protocol is violated, then immediately close connection to client.
     */
    public void run() {
        // Initial connect request comes in
        Request request = getRequest();

        if (request == null) {
            closeConnection();
            return;
        }

        if (request.getConnectRequest() == null) {
            closeConnection();
            System.err.println("Received invalid initial request from Remote Client.");
            return;
        }

        Response responseWrapper = new Response(new ConnectResponse(id), request.getId());

        // Return connect response with our (statistically) unique ID.
        if (!sendMessage(responseWrapper)) {
            closeConnection();
            System.err.println("Unable to respond to connect Request from remote Client.");
            return;
        }

        // register our thread with the server
        Server.register(id, this);

        // have handler manage the protocol until it decides it is done.
        while ((request = getRequest()) != null) {

            Response response = handler.process(this, request);
            if (!sendMessage(response)) {
                break;
            }
        }

        // client is done so thread can be de-registered
        if (handler instanceof IShutdownHandler) {
            ((IShutdownHandler) handler).logout(Server.getState(id));
        }
        Server.unregister(id);

        // close communication to client.
        closeConnection();
    }

    /**
     * Return (statistically) unique id.
     */
    public String id() {
        return id;
    }

    /**
     * Associate new object data as the user-defined data for the thread and return the old previous value.
     */
    public Object setData(Object newData) {
        Object old = data;
        data = newData;
        return old;
    }

    /**
     * Retrieve user-defined data associated with this thread.
     */
    public Object getData() {
        return data;
    }

    /**
     * Send a response object to the client.
     *
     * @param response the response
     * @return true on success, otherwise false
     */
    public boolean sendMessage(Response response) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(response.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(response, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println("Sending Response to " + id + ":");
        JAXBUtil.prettyPrintln(response);
        toClient.println(sw.toString());
        return !toClient.checkError();
    }

    /**
     * Deserialize the request from the stream
     *
     * @return the request
     */
    private Request getRequest() {
        String xml = getXmlStringFromReader(fromClient, "</request>");

        if (xml == null) {
            return null;
        }

        try {
            JAXBContext requestContext = JAXBContext.newInstance(Request.class);
            Unmarshaller unmarshaller = requestContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            return (Request) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            System.err.println("Unable to deserialize request from Remote Client.");
            return null;
        }
    }

    /**
     * Extract the XML message and construct String object based on
     * the terminator string (either "</request>" or "</response>"). Returns
     * null if communication is interrupted in any way.
     */
    static String getXmlStringFromReader(BufferedReader in, String terminator) {
        try {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            StringBuilder buf = new StringBuilder(line);
            while (!buf.substring(buf.length() - terminator.length(), buf.length()).equals(terminator)) {
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

    /**
     * Close the connection to the client
     */
    private void closeConnection() {
        try {
            fromClient.close();
            toClient.close();
            client.close();
        } catch (IOException e) {
            System.err.println("Unable to close connection:" + e.getMessage());
        }
    }
}
