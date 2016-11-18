package com.wordsweeper.adminclient.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;


import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.controller.AdminClinetMessageHandler;
import com.wordsweeper.adminclient.controller.BoardResponseController;
import com.wordsweeper.adminclient.controller.CheckGameController;
import com.wordsweeper.adminclient.controller.RefreshGameListController;
import com.wordsweeper.adminclient.ServerAccess;

import xml.Message;
import javax.swing.JTextField;
import javax.swing.JLabel;





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
	
	JTable TableBoard;
	JTextArea PlayerList;
	JScrollPane PlayerOutput;
	JScrollPane scrollBoard;
	JLabel lblGameIdList;
	JLabel lblBoardInfo;
	JTextField txtGameID;
	private JLabel lblBoard;
	private JLabel lblPlayers;
	JTextField txtBoard;

	
	public AdminClientApplication(final AdminClientModel model) {
		this.model = model;
		setTitle("Online Game List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		gamePane = new JPanel();
		gamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnRefrshGame = new JButton("Refresh");
		btnRefrshGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				new RefreshGameListController(AdminClientApplication.this, model).process();
			}
		});
		
		btnCheckGame = new JButton("Check");
		btnCheckGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();
//				setVisible(false);
//				AdminClientBoardApplication app = new AdminClientBoardApplication(model);
//				app.setVisible(true);
////				app.getRequestArea().append(getGameID());
//				String t = getGameID();
//				app.setGameIDNumber().setText(t);
//				String letters=getContant();
//				app.setBoard().setText(letters);
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
		
		scrollBoard = new JScrollPane();
		
		PlayerOutput = new JScrollPane();
		
		lblGameIdList = new JLabel("Game ID List");
		
		lblBoard = new JLabel("Board:");
		
		lblBoardInfo = new JLabel("Board Info:");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnRefrshGame, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 711, Short.MAX_VALUE)
									.addComponent(btnCheckGame, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(gamelistOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGameIdList))
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollBoard, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBoard))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBoardInfo)
								.addComponent(PlayerOutput, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
							.addGap(17))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCheckGame)
						.addComponent(btnRefrshGame))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGameIdList)
						.addComponent(lblBoard)
						.addComponent(lblBoardInfo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollBoard, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
						.addComponent(gamelistOutput, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
						.addComponent(PlayerOutput, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JTextArea PlayerList = new JTextArea();
		PlayerOutput.setViewportView(PlayerList);
		
		
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
	
	public Object[][] stringTo2D(String alphabets){
		String[] letters = alphabets.split(",");
		int n=(int) Math.sqrt(letters.length);
		Object[][] alpha = new Object[n][n];
		int k=0;
		for(int i= 0;i<n;i++){
		       for(int j=0;j<n;j++){
		    	   alpha[i][j] = letters[k];
		    	   k++;
		    	   }
		       }
		return alpha; 
	}
	
	public JTable setContent(String Content){
		Object[][] data = stringTo2D(Content);
		String[] columnNames = {"1","2","3","4","5","6","7"};
		TableBoard = new JTable(data,columnNames);
		scrollBoard.setViewportView(TableBoard);
		TableBoard.setRowHeight(50);
		JTableHeader header = TableBoard.getTableHeader();
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		header.setDefaultRenderer(new HeaderRenderer(TableBoard));
		
		for(int i= 0;i<data.length;i++){
			TableBoard.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		header.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		TableBoard.setPreferredScrollableViewportSize(new Dimension(300,300));
		TableBoard.setFillsViewportHeight(true);
		
		return TableBoard;
	}
	
	private static class HeaderRenderer implements TableCellRenderer {

	    DefaultTableCellRenderer renderer;

	    public HeaderRenderer(JTable table) {
	        renderer = (DefaultTableCellRenderer)
	            table.getTableHeader().getDefaultRenderer();
	        renderer.setHorizontalAlignment(JLabel.CENTER);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int col) {
	        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	    }
	}
}

