package com.wordsweeper.adminclient.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.AbstractButton;
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

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.controller.CheckGameController;
import com.wordsweeper.adminclient.controller.RefreshGameListController;
import com.wordsweeper.adminclient.ServerAccess;

import com.wordsweeper.adminclient.controller.CheckGameController;
import com.wordsweeper.adminclient.controller.RefreshGameListController;
import com.wordsweeper.adminclient.ServerAccess;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class AdminClientBoardApplication extends JFrame{
	public final AdminClientModel model;
	JPanel gamePane;
	JTable TableBoard;
	ServerAccess serverAccess;
	JTextArea PlayerList;
	JScrollPane PlayerOutput;
	JScrollPane scrollBoard;
	JLabel lblGameID;
	JTextField txtGameID;
	private JLabel lblBoard;
	private JLabel lblPlayers;
	JTextField txtBoard;
		
	 
	public AdminClientBoardApplication(final AdminClientModel model) {
		this.model = model;
		setTitle("Board Status");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 610, 550);
		gamePane = new JPanel();
		gamePane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(gamePane);
		
		JButton RefreshBoard = new JButton("Refresh Board");
		RefreshBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] data = stringTo2D(txtBoard.getText());
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
				
			}
		});
		
//		BoardOutput = new JScrollPane();
//		BoardOutput.setViewportView(TableBoard);
		
		PlayerOutput = new JScrollPane();
		
		lblGameID = new JLabel("Game ID:");
		
		txtGameID = new JTextField(20);
		
		lblBoard = new JLabel("Board: ");
		
		lblPlayers = new JLabel("Players:");
		
		txtBoard = new JTextField(20);
		
		scrollBoard = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(gamePane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(RefreshBoard)
							.addGap(18)
							.addComponent(lblGameID, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtGameID, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(28)
								.addComponent(txtBoard, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(16)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblBoard)
									.addComponent(scrollBoard, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblPlayers)
									.addComponent(PlayerOutput, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(RefreshBoard)
						.addComponent(lblGameID)
						.addComponent(txtGameID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBoard)
						.addComponent(lblPlayers))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollBoard, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
						.addComponent(PlayerOutput, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
					.addGap(71))
		);
		
//		TableBoard = new JTable();
//		scrollBoard.setViewportView(TableBoard);
//		TableBoard.setPreferredScrollableViewportSize(new Dimension(50,50));
		
		PlayerList = new JTextArea();
		PlayerOutput.setRowHeaderView(PlayerList);
		gamePane.setLayout(gl_contentPane);
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
	public JTextArea getPlayerList() {
		return PlayerList;
	}
	
	public JTextField setGameIDNumber() {
		return txtGameID;
	}
	
	public String getGameID(){
		String game_id = txtGameID.getText();	
		return game_id;
	}
	
	public JTextField setBoard(){
		return txtBoard;	
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
