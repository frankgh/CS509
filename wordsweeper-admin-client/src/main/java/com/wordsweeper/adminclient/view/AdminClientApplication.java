package com.wordsweeper.adminclient.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JList;


import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.controller.AdminClinetMessageHandler;
import com.wordsweeper.adminclient.controller.CheckGameController;
import com.wordsweeper.adminclient.controller.RefreshGameListController;
import com.wordsweeper.adminclient.ServerAccess;


public class AdminClientApplication extends JFrame{
	/** GUI application maintains reference to Model for ease of navigation. */
	public final AdminClientModel model;
	
	JPanel contentPane;
	JPanel gamePane;
	ServerAccess serverAccess;
	
	JTextArea responseArea;
	JList<String> gamelist;
	DefaultListModel<String> listModel;
	
	JButton btnCheckGame;
	JButton btnRefreshGame;
	
	JScrollPane gamelistOutput;
	JTextArea txtConnection;
	JScrollPane scrollPane;

	
	public AdminClientApplication(final AdminClientModel model) {
		this.model = model;
		setTitle("Online Game List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		gamePane = new JPanel();
		gamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnRefrshGame = new JButton("Refresh");
		btnRefrshGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RefreshGameListController(AdminClientApplication.this, model).process();
			}
		});
		
		btnCheckGame = new JButton("Check");
		btnCheckGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();
//				setVisible(false);
				AdminClientBoardApplication app = new AdminClientBoardApplication(model);
				app.setVisible(true);
//				app.getRequestArea().append(getGameID());
				String t = getGameID();
				app.setGameIDNumber().setText(t);
				String letters=getContant();
				app.setBoard().setText(letters);
				new CheckGameController(AdminClientApplication.this, model).process();
			}
		});
		
//		create the model and add elements 
        listModel = new DefaultListModel<String>(); 
	
		gamelistOutput = new JScrollPane();
		gamelist = new JList<String>(listModel);
		gamelistOutput.setViewportView(gamelist);
		gamelist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		gamelist.setLayoutOrientation(JList.VERTICAL);
		gamelist.setSelectedIndex(0);
//		gamelist.addListSelectionListener(this);
        gamelist.setVisibleRowCount(5);
		
		scrollPane = new JScrollPane();
		txtConnection = new JTextArea();
		scrollPane.setViewportView(txtConnection);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
						.addComponent(gamelistOutput, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRefrshGame, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
							.addComponent(btnCheckGame, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCheckGame)
						.addComponent(btnRefrshGame))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(gamelistOutput, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		
		contentPane.setLayout(gl_contentPane);
	}

	/** Record the means to communicate with server. */
	public void setServerAccess(ServerAccess access) {
		this.serverAccess = access;
	}
	
	/** Get the server access object. */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}
	
	
	/** Navigation access to actionable elements in the GUI. */
	public DefaultListModel<String> getResponseArea() {
		return listModel;
	}
	
	public JTextArea getConnection() {
		return txtConnection;
	}
	
	public String getGameID(){
		String game_idS = listModel.getElementAt(gamelist.getSelectedIndex()).toString();	
		return game_idS;
	}
	
	public String getContant(){
		String game_contant = txtConnection.getText();
		return game_contant;
	}
}

