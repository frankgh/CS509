package com.wordsweeper.adminclient.view;

import com.wordsweeper.adminclient.ServerAccess;
import com.wordsweeper.adminclient.controller.ListGamesResponseController;
import com.wordsweeper.adminclient.view.AdminClientApplication.ColorPlayerListCellRenderer;
import com.wordsweeper.adminclient.view.AdminClientApplication.ColorTableCellRenderer;
import com.wordsweeper.core.util.JAXBUtil;
import com.wordsweeper.core.xml.Response;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import static org.junit.Assert.*;


/**
 * This is the class for admin client model.
 */
public class AdminClientApplicationTest {
    @Test
    public void constructor() throws Exception {

        AdminClientApplication app = new AdminClientApplication();
        assertTrue(app.getLayout().toString().length() > 1);
        assertEquals(app.btnRefreshGame.getText(), "Refresh");
        assertEquals(app.btnCheckGame.getText(), "Check");

        assertNull(app.getServerAccess());
        ServerAccess sa = new ServerAccess("127.0.0.1", 11425);
        app.setServerAccess(sa);
        assertFalse(app.getServerAccess().isWaiting());

        JTextArea testText = app.getConnection();
        assertEquals(testText.getText(), "");
        ListGamesResponseController resCont = new ListGamesResponseController(app);
        assertEquals(app.gamelist.getRowCount(), 0);
        BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"cc667680-81d6-4d5b-a23b-3edc2cdf866f\" success=\"true\" version=\"1.0\"><listGamesResponse><gameBrief gameId=\"nc99qujnk002q3akkhpde400s0\" players=\"1\"/><gameBrief gameId=\"n65agh23rrusg7lcc4rm80fuj\" players=\"1\"/></listGamesResponse></response>";

        Mockito.when(bufferedReader.readLine()).thenReturn(input).thenReturn(input);
        String xml = JAXBUtil.getXmlStringFromReader(bufferedReader, "</response>");
        Response response = JAXBUtil.deserialize(xml, Response.class);
        resCont.process(response);
        String test = "nc99qujnk002q3akkhpde400s0";

        app.gamelist.selectAll();
        assertEquals(app.getGameID(), test);

        assertEquals(app.getContant(), "");

        Color yellow = Color.green;

        Color blue = Color.blue;
        Color testColor = app.blend(yellow, blue);
        assertEquals(testColor, new Color(0, 127, 127));

        app.ColorMap = new Color[7][7][5];
        app.addcolor(2, 2, 1);
        ColorTableCellRenderer colRend = app.new ColorTableCellRenderer();
        colRend.getTableCellRendererComponent(app.TableBoard, new Object(), true, true, 1, 1);
        assertTrue(colRend.north);
        assertTrue(colRend.east);

        colRend.south = true;
        colRend.east = true;
        colRend.north = true;
        colRend.west = true;
        colRend.background = true;
        BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        app.setSize(50, 50);
        colRend.paintComponent((Graphics) g2);

        colRend.south = false;
        colRend.east = false;
        colRend.north = false;
        colRend.west = false;
        colRend.background = false;
        colRend.paintComponent((Graphics) g2);

        g2.dispose();

        JTable testTable = new JTable();
        app.ColorMap[0][0][0] = Color.YELLOW;
        app.ColorMap[0][0][1] = Color.YELLOW;
        app.ColorMap[0][0][2] = Color.YELLOW;
        app.ColorMap[0][0][3] = Color.YELLOW;
        app.ColorMap[0][0][4] = Color.YELLOW;
        colRend.getTableCellRendererComponent(testTable, blue, false, false, 0, 0);
        colRend.getTableCellRendererComponent(testTable, blue, true, false, 0, 0);
        colRend.getTableCellRendererComponent(null, blue, false, true, 1, 1);
        colRend.getTableCellRendererComponent(null, blue, true, true, 1, 1);

        ColorPlayerListCellRenderer cellRend = app.new ColorPlayerListCellRenderer();
        cellRend.getTableCellRendererComponent(testTable, blue, false, false, 0, 0);
        colRend.getTableCellRendererComponent(null, blue, false, true, 1, 1);
    }
}
