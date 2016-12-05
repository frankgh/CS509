package com.wordsweeper.adminclient.view;





import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;

import java.util.Random;


import com.wordsweeper.adminclient.model.AdminClientModel;

import com.wordsweeper.adminclient.controller.AdminClientMessageHandler;
import com.wordsweeper.adminclient.controller.BoardResponseController;
import com.wordsweeper.adminclient.controller.CheckGameController;
import com.wordsweeper.adminclient.controller.RefreshGameListController;
import com.wordsweeper.adminclient.ServerAccess;

import xml.Message;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * This is the class for admin client application.
 */

public class AdminClientApplication extends JFrame{
	/** GUI application maintains reference to Model for ease of navigation. */
	public final AdminClientModel model;
	
	JPanel contentPane;
	JPanel gamePane;

	ServerAccess serverAccess;
	

	JTextArea responseArea;
	JTextArea txtConnection;
	
	JButton btnCheckGame;
	JButton btnRefreshGame;
	
	JScrollPane gamelistOutput;

	JScrollPane scrollPane;
	JScrollPane PlayerOutput;
	JScrollPane scrollBoard;

	JTable TableBoard;
	JTable PlayerList;
	JTable gamelist;

	JLabel lblGameIdList;
	JLabel lblBoardInfo;

	JTextField txtGameID;
	JTextField txtBoard;

	private JLabel lblNewLabel;
	private JLabel lblBoard;

	public Color[][][] ColorMap;
	public Color[] randomColor;

	public int n;


	/**
	 *
	 * @param model the admin client application model
	 */

	
	public AdminClientApplication(final AdminClientModel model) {
		this.model = model;
		setTitle("Online Game List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/** setup the panel and its size . */
		gamePane = new JPanel();
		gamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		/** set the color array. */
		randomColor=randomColor();

		/** Check game list button setup and click ActionListener. */
		btnRefreshGame = new JButton("Refresh");
		btnRefreshGame.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		btnRefreshGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelgameIDTable = (DefaultTableModel) gamelist.getModel();
				modelgameIDTable.setRowCount(0);
				new RefreshGameListController(AdminClientApplication.this, model).process();
			}
		});
		
		/** Check game button setup and click ActionListener. */
		btnCheckGame = new JButton("Check");
		btnCheckGame.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		btnCheckGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelTable = (DefaultTableModel) PlayerList.getModel();
				modelTable.setRowCount(0);
//				ColorMap = new Color[7][7];
				new CheckGameController(AdminClientApplication.this, model).process();

			}
		});
		
		/** create the model and add elements. */
		gamelistOutput = new JScrollPane();
		scrollPane = new JScrollPane();
		txtConnection = new JTextArea();
		scrollPane.setViewportView(txtConnection);
		scrollBoard = new JScrollPane();
		PlayerOutput = new JScrollPane();
		lblGameIdList = new JLabel("Game ID List");
		lblBoard = new JLabel("Board:");
		lblBoardInfo = new JLabel("Board Info:");
		JLabel lblConnectionInfo = new JLabel("Connection Info:");
		lblNewLabel = new JLabel("Manager");

		/** setup Player list Jtable. */
		DefaultTableModel modelT = new DefaultTableModel();
		modelT.addColumn("");
		modelT.addColumn("Player:");
		modelT.addColumn("Score:");
		modelT.addColumn("Position");
		PlayerList = new JTable(modelT);
		PlayerOutput.setViewportView(PlayerList);
		/** set Player name column width to 120. */
		TableColumn col1 =PlayerList.getColumnModel().getColumn(1);
		col1.setPreferredWidth(120);
		/** set color cell column width to 10. */
		TableColumn col4 =PlayerList.getColumnModel().getColumn(0);
		col4.setPreferredWidth(10);

		/** setup the gameID List Jtable. */
		DefaultTableModel modelG = new DefaultTableModel();
		modelG.addColumn("Game ID");
		modelG.addColumn("Players");
		gamelist = new JTable(modelG);
		gamelistOutput.setViewportView(gamelist);

		/** set the gameID column width. */
		TableColumn colG1 =gamelist.getColumnModel().getColumn(0);
		colG1.setPreferredWidth(200);


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGameIdList)
						.addComponent(gamelistOutput, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBoard)
						.addComponent(scrollBoard, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBoardInfo)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(PlayerOutput, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)))
					.addContainerGap(11, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(btnRefreshGame, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(219)
					.addComponent(btnCheckGame, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(377, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConnectionInfo)
					.addContainerGap(829, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblConnectionInfo)
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGameIdList)
								.addComponent(lblBoard)
								.addComponent(lblBoardInfo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(gamelistOutput, 0, 0, Short.MAX_VALUE)
								.addComponent(PlayerOutput, 0, 0, Short.MAX_VALUE)
								.addComponent(scrollBoard, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnRefreshGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCheckGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(61)
							.addComponent(lblNewLabel)))
					.addGap(45))
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
	
	/** set the responded game list and player list info to the geme_list Jtable. */
	public JTable SetGameIDInfo(Object[] info){
		DefaultTableModel modelID = (DefaultTableModel) gamelist.getModel();
		modelID.addRow(info);
		return gamelist;
	}

	/** add the connection info to the connection text box area. */
	public JTextArea getConnection() {
		return txtConnection;
	}

	/** set the players info to the Player_list Jtable area. */
	public JTable setPlayerInfo(Object[] info) {
		DefaultTableModel model = (DefaultTableModel) PlayerList.getModel();
		model.addRow(info);
		return PlayerList;
	}

	/** Get game id from server.*/

	public String getGameID(){
		String game_idS = null;
        int[] selectedRow = gamelist.getSelectedRows();
        int[] selectedColumns = gamelist.getSelectedColumns();
        game_idS = (String) gamelist.getValueAt(selectedRow[0], selectedColumns[0]);
		return game_idS;
	}

	/** Get game contant from the server.*/

	public String getContant(){
		String game_contant = txtConnection.getText();
		return game_contant;
	}

	/**
	 * Convert content string(comma separate) into a 2d object array for Jtable to display
	 * @param alphabets the alphabets from game table
	 * @return words
	 */
	public Object[][] stringTo2D(String alphabets){
		String[] letters = alphabets.split(",");
		n=(int) Math.sqrt(letters.length);
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
	
	/** set the Jtable first row index. i.e. 1 2 3 4.....*/
	public String[] setColIndex(Object[][] alphabets){
		int col=alphabets.length;
		String[] colIndex = new String[col];
		int k=1;
		for(int i= 0;i<col;i++){
			colIndex[i]=Integer.toString(k);
			k++;
		}
		return colIndex;
	}
	

	/** set the board content. */
	public JTable setContent(String Content){
		Object[][] data = stringTo2D(Content);
		String[] columnIndex =setColIndex(data);
		TableBoard = new JTable(new DefaultTableModel(data,columnIndex));

		scrollBoard.setViewportView(TableBoard);
		TableBoard.setRowHeight(40);

		TableBoard.setCellSelectionEnabled(true);
		TableBoard.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//set the header center alignment
		JTableHeader header = TableBoard.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(TableBoard));
		//set the column width to be fixed, whatever the horizontal size increase, each column width is 40
		TableBoard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i= 0;i<data.length;i++){
			TableColumn col =TableBoard.getColumnModel().getColumn(i);
			col.setPreferredWidth(40);
		}

		/** set the view size. */
		TableBoard.setPreferredScrollableViewportSize(new Dimension(350,350));
		TableBoard.setFillsViewportHeight(true);

		/** initial the ColorMap color array. */
		ColorMap = new Color[n][n][4];

		return TableBoard;
	}
	
	/** mix the two input color. */
	// mix the two color function
	public static Color blend(Color c0, Color c1) {
	    double totalAlpha = c0.getAlpha() + c1.getAlpha();
	    double weight0 = c0.getAlpha() / totalAlpha;
	    double weight1 = c1.getAlpha() / totalAlpha;

	    double r = weight0 * c0.getRed() + weight1 * c1.getRed();
	    double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
	    double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
	    double a = Math.max(c0.getAlpha(), c1.getAlpha());

	    return new Color((int) r, (int) g, (int) b, (int) a);
	  }

	// add the color to the color matrix which is the same size as the board

	/** add the border color to the ColorMap array. */
	public void addcolor(int r, int c, int indexColor){
		//set the row and column index -1
		r=r-1;
		c=c-1;
		//start to add the boarder color around the 4*4 window
		for(int i=0; i<4; i++){
			//add the north border color
			if (ColorMap[r][c+i][0]== null){
				ColorMap[r][c+i][0]=randomColor[indexColor];
			}
			else{
				ColorMap[r][c+i][0]=blend(ColorMap[r][c+i][0],randomColor[indexColor]);
			}
			//add the south border color
			if (ColorMap[r+3][c+i][1]== null){
				ColorMap[r+3][c+i][1]=randomColor[indexColor];
			}
			else{
				ColorMap[r+3][c+i][1]=blend(ColorMap[r+3][c+i][1],randomColor[indexColor]);
			}
			//add the east border color
			if (ColorMap[r+i][c][2]== null){
				ColorMap[r+i][c][2]=randomColor[indexColor];
			}
			else{
				ColorMap[r+i][c][2]=blend(ColorMap[r+i][c][2],randomColor[indexColor]);
			}
			//add the west border color
			if (ColorMap[r+i][c+3][3]== null){
				ColorMap[r+i][c+3][3]=randomColor[indexColor];
			}
			else{
				ColorMap[r+i][c+3][3]=blend(ColorMap[r+i][c+3][3],randomColor[indexColor]);
			}
		}
	}


	/** set the border color for each player's view window and player list. */
	public void setBoardColor(){
		TableCellRenderer tcr = new ColorTableCellRenderer();
		TableBoard.setDefaultRenderer(Object.class,tcr);

		TableCellRenderer tcr1 = new ColorPlayerListCellRenderer();
		PlayerList.setDefaultRenderer(Object.class, tcr1);

	}
	/** Generate the color array. */
	//create a random color array
	public Color[] randomColor(){
		randomColor=new Color[10];
		randomColor[0] = Color.red;
		randomColor[1] = Color.green;
		randomColor[2] = Color.blue;
		randomColor[3] = Color.cyan;
		randomColor[4] = Color.gray;
		randomColor[5] = Color.magenta;
		randomColor[6] = Color.orange;
		randomColor[7] = Color.yellow;
		randomColor[8] = Color.pink;
		randomColor[9] = Color.darkGray;

		/** the actual random color code. */
//		final Random rand = new Random();
//		randomColor=new Color[20];
//		for(int i=0; i<20;i++){
//			int r = (int) (rand.nextInt(256));
//			int g = (int) (rand.nextInt(256));
//			int b = (int) (rand.nextInt(256));
//			randomColor[i] = new Color(r, g, b);
//		}
		return randomColor;
	}

	/** the header Renderer. */

	// Center class
	/** Center class */
	private static class HeaderRenderer implements TableCellRenderer {

	    DefaultTableCellRenderer renderer;

		/**
		 * Render deader in game table.
		 * @param table game table
		 */

	    public HeaderRenderer(JTable table) {
	        renderer = (DefaultTableCellRenderer)
	            table.getTableHeader().getDefaultRenderer();
	        renderer.setHorizontalAlignment(JLabel.CENTER);
	    }

		/**
		 * This get the table component from the server.
		 * @param table table in the game.
		 * @param value object value in the table
		 * @param isSelected
		 * @param hasFocus
		 * @param row table row
		 * @param col table column
		 * @return component from the server
		 */

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int col) {
	        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	    }
	}

	/**
	 * This is the class for board cell renderer.
	 */

//	cell render for highlight the each client view window

	/** the board color table Renderer. */
	private class ColorTableCellRenderer extends DefaultTableCellRenderer {
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		boolean north=false;
		boolean south=false;
		boolean east=false;
		boolean west=false;
		int r;
		int c;
		int lineWidth=5;

        /**
         * This get the table component from the server.
         * @param table table in the game.
         * @param value object value in the table
         * @param isSelected if value is selected.
         * @param hasFocus boolean
         * @param row table row
         * @param column table column
         * @return component from the server
         */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//			if(ColorMap[row][column]!=null){
//				super.setHorizontalAlignment(JLabel.CENTER);
//				setBackground(ColorMap[row][column]);
//				return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
//			}
//			else{
//				renderer.setHorizontalAlignment(JLabel.CENTER);
//				return renderer.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
//			}
			r=row;
			c=column;
			if(ColorMap[r][c]!=null){
				super.setHorizontalAlignment(JLabel.CENTER);
				if (ColorMap[r][c][0]!=null){
					north=true;
				}
				if (ColorMap[r][c][1]!=null){
					south=true;
				}
				if (ColorMap[r][c][2]!=null){
					east=true;
				}
				if (ColorMap[r][c][3]!=null){
					west=true;
				}
				return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
			}
			else{
				renderer.setHorizontalAlignment(JLabel.CENTER);
				return renderer.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
			}


		}

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			final BasicStroke stroke=new BasicStroke(2.0f);

			if(north){
				g2.setColor(ColorMap[r][c][0]);
				g2.setStroke(stroke);
				for (int i = 0; i < lineWidth; i++) {
					g2.drawLine(0,0+i,getWidth(),0+i);
				    }
				north=false;
			}
			if(south){
				g2.setColor(ColorMap[r][c][1]);
				g2.setStroke(stroke);
				for (int i = 0; i < lineWidth; i++) {
					g2.drawLine(0,getHeight()-i,getWidth(),getHeight()-i);
				}
				south=false;
			}
			if(east){
				g2.setColor(ColorMap[r][c][2]);
				g2.setStroke(stroke);
				for (int i = 0; i < lineWidth; i++) {
					g2.drawLine(0+i,0,0+i,getHeight());
				}
				east=false;
			}
			if(west){
				g2.setColor(ColorMap[r][c][3]);
				g2.setStroke(stroke);
				for (int i = 0; i < lineWidth; i++) {
					g2.drawLine(getWidth()-i,0,getWidth()-i,getHeight());
				}
				west=false;
			}
		}
	}


	/** the player list color Renderer. */
	private class ColorPlayerListCellRenderer extends DefaultTableCellRenderer {
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			if(column==0){
				setBackground(randomColor[row]);
				return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
			}
			else{
				return renderer.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
			}

			}
		}


}
