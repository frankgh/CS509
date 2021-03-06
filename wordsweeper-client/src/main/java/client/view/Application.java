package client.view;

import client.ServerAccess;
import client.controller.CreateGameController;
import client.controller.JoinGameController;
import client.controller.RepositionBoardController;
import client.model.Model;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the class for GUI application extended from JFrame.
 */
public class Application extends JFrame {

    /**
     * GUI application maintains reference to Model for ease of navigation.
     */
    public final Model model;

    JPanel contentPane;
    ServerAccess serverAccess;

    JTextArea requestArea;
    JTextArea responseArea;

    JButton btnJoinGame;
    JButton btnRepositionBoard;

    JScrollPane clientRequests;
    JScrollPane serverOutput;
    private JTextField name;
    private JTextField password;
    private JTextField game_id;
    JTextField rowChange;
    JTextField colChange;

    /**
     * Create the frame.
     */
    public Application(final Model model) {
        this.model = model;
        setTitle("Skeleton Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 473, 374);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        clientRequests = new JScrollPane();

        JLabel lblOutputFromServer = new JLabel("Output From Server");

        serverOutput = new JScrollPane();

        requestArea = new JTextArea(100, 20);
        clientRequests.setViewportView(requestArea);

        responseArea = new JTextArea();
        serverOutput.setViewportView(responseArea);

        name = new JTextField();
        name.setColumns(10);

        password = new JTextField();
        password.setColumns(10);

        game_id = new JTextField();
        game_id.setColumns(10);

        rowChange = new JTextField();
        rowChange.setColumns(10);

        colChange = new JTextField();
        colChange.setColumns(10);


        JButton btnCreateGame = new JButton("Create Game");
        btnCreateGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateGameController(Application.this, model).process();
            }
        });

        btnJoinGame = new JButton("Join Game");
        btnJoinGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JoinGameController(Application.this, model).process();
            }
        });

        btnRepositionBoard = new JButton("Reposition");
        btnRepositionBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RepositionBoardController(Application.this, model).process();
            }
        });

        JPanel panel = new JPanel();

        JButton btnName = new JButton("Name");
        btnName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnPassword = new JButton("Password");

        JButton btnGameid = new JButton("GameID");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(clientRequests, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                        .addComponent(lblOutputFromServer)
                                        .addComponent(serverOutput, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnCreateGame)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(btnName, 0, 0, Short.MAX_VALUE)
                                                        .addComponent(name, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnPassword, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnGameid, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(game_id, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(rowChange, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(colChange, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnJoinGame, Alignment.TRAILING)
                                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnRepositionBoard, Alignment.CENTER)
                                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(btnCreateGame)
                                                .addComponent(btnJoinGame)
                                                .addComponent(btnRepositionBoard))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnName)
                                                        .addComponent(btnPassword)
                                                        .addComponent(btnGameid))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(game_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(rowChange, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(colChange, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(clientRequests, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(lblOutputFromServer)
                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(serverOutput, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
        );
        contentPane.setLayout(gl_contentPane);
    }

    /**
     * Record the means to communicate with server.
     */
    public void setServerAccess(ServerAccess access) {
        this.serverAccess = access;
    }

    /**
     * Get the server access object.
     */
    public ServerAccess getServerAccess() {
        return serverAccess;
    }

    /**
     * Navigation access to actionable elements in the GUI.
     */
    public JTextArea getRequestArea() {
        return requestArea;
    }

    /**
     * Navigation access to actionable elements in the GUI.
     */
    public JTextArea getResponseArea() {
        return responseArea;
    }

    public String getName() {
        String nameS = name.getText();
        return nameS;
    }

    public String getPassword() {
        String passowrdS = password.getText();
        return passowrdS;
    }

    public String getGameID() {
        String game_idS = game_id.getText();
        return game_idS;
    }

    public String getRowChange() {
        return rowChange.getText();
    }

    public String getColChange() {
        return colChange.getText();
    }
}
