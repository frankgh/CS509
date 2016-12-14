package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.Player;
import com.wordsweeper.core.xml.Response;

/**
 * This is a class for Board response controller.
 */

public class BoardResponseController {

    public AdminClientApplication app;
    public AdminClientModel model;

    /**
     * @param a the admin client application
     * @param m the admin client model
     */
    public BoardResponseController(AdminClientApplication a, AdminClientModel m) {
        this.app = a;
        this.model = m;
    }

    /**
     * @param response the respond from the board controller
     */
    public void process(Response response) {

        if (!response.isSuccess()) {
            return;
        }

        String boardcontents = response.getBoardResponse().getContents();
        String boardbonus = response.getBoardResponse().getBonus();

        String[] bonusArray = boardbonus.split(",");
        int Bonus_c = Integer.parseInt(bonusArray[0]);
        int Bonus_r = Integer.parseInt(bonusArray[1]);

        app.setContent(boardcontents);
        app.setBonus(Bonus_r, Bonus_c);

        int i = 0;

        for (Player player : response.getBoardResponse().getPlayer()) {
            String pname = player.getName();
            String score = String.valueOf(player.getScore());
            String position = player.getPosition();
            String index = Integer.toString(i + 1);
            Object[] info = new Object[]{index, pname, score, position};
            app.setPlayerInfo(info);

            String[] parts = position.split(",");
            int c = Integer.parseInt(parts[0]);
            int r = Integer.parseInt(parts[1]);

            app.addcolor(r, c, i);
            i++;
        }
        app.setBoardColor();
    }

}
