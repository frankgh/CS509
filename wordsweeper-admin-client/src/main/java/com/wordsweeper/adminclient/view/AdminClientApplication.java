package com.wordsweeper.adminclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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


import com.wordsweeper.adminclient.model.AdminClientModel;

import com.wordsweeper.adminclient.controller.CheckGameController;
import com.wordsweeper.adminclient.controller.RefreshGameListController;
import com.wordsweeper.adminclient.ServerAccess;

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
	JList<String> gamelist;
	DefaultListModel<String> listModel;
	
	JButton btnCheckGame;
	JButton btnRefreshGame;
	
	JScrollPane gamelistOutput;
	JTextArea txtConnection;
	JScrollPane scrollPane;
	
	JTable TableBoard;
	JScrollPane PlayerOutput;
	JScrollPane scrollBoard;
	JLabel lblGameIdList;
	JLabel lblBoardInfo;
	JTextField txtGameID;
	private JLabel lblBoard;
	private JLabel lblPlayers;
	JTextField txtBoard;
	JTable PlayerList;
	private JLabel lblNewLabel;

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
		
		gamePane = new JPanel();
		gamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		btnRefreshGame = new JButton("Refresh");
		btnRefreshGame.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		btnRefreshGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				new RefreshGameListController(AdminClientApplication.this, model).process();
			}
		});
		
		btnCheckGame = new JButton("Check");
		btnCheckGame.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		btnCheckGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelTable = (DefaultTableModel) PlayerList.getModel();
				modelTable.setRowCount(0);
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
		
		JLabel lblConnectionInfo = new JLabel("Connection Info:");
		
		lblNewLabel = new JLabel("Manager");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGameIdList)
						.addComponent(gamelistOutput, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
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
					.addGap(217)
					.addComponent(btnCheckGame, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(379, Short.MAX_VALUE))
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
								.addComponent(PlayerOutput, 0, 0, Short.MAX_VALUE)
								.addComponent(scrollBoard, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
								.addComponent(gamelistOutput))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnCheckGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRefreshGame, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(61)
							.addComponent(lblNewLabel)))
					.addGap(45))
		);
		
		DefaultTableModel modelT = new DefaultTableModel(); 
		modelT.addColumn("Player:");
		modelT.addColumn("Score:");
		modelT.addColumn("Position");
		PlayerList = new JTable(modelT); 
		PlayerOutput.setViewportView(PlayerList);
		TableColumn col =PlayerList.getColumnModel().getColumn(0);
		col.setPreferredWidth(120);
		
		
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

	/** Pull connection status from server. */
	public JTextArea getConnection() {
		return txtConnection;
	}

	/** Set player's information in the GUI.*/
	public JTable setPlayerInfo(Object[] info) {
		DefaultTableModel model = (DefaultTableModel) PlayerList.getModel();
		model.addRow(info);
		return PlayerList;
	}

	/** Get game id from server.*/
	
	public String getGameID(){
		String game_idS = listModel.getElementAt(gamelist.getSelectedIndex()).toString();	
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
	
	/** set the JTable content */
	public JTable setContent(String Content){
		Object[][] data = stringTo2D(Content);
		String[] columnIndex =setColIndex(data);
		TableBoard = new JTable(data,columnIndex);
		scrollBoard.setViewportView(TableBoard);
		TableBoard.setRowHeight(40);
		TableBoard.setCellSelectionEnabled(true);
		TableBoard.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		TableBoard.setDefaultRenderer(Object.class, new BorderCellRenderer());
		JTableHeader header = TableBoard.getTableHeader();
		// center the row position
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		header.setDefaultRenderer(new HeaderRenderer(TableBoard));
		//center the column position
		TableBoard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i= 0;i<data.length;i++){
			TableColumn col =TableBoard.getColumnModel().getColumn(i);
			col.setPreferredWidth(40);
			col.setCellRenderer(centerRenderer);
//			TableBoard.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//			TableBoard.getColumnModel().getColumn(i).setPreferredWidth(30);
		}
		header.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
//		int [] col = {1,2,3,4};
//		int [] row= {2,3,4};
//		setCellBorder(col, row);
		//set the view size
		TableBoard.setPreferredScrollableViewportSize(new Dimension(350,350));
		TableBoard.setFillsViewportHeight(true);
		// set the player view border
//		Color color = UIManager.getColor("Table.gridColor");
//		MatteBorder border = new MatteBorder(0, 2, 0, 0, Color.red);		
//		TableBoard.setBorder(border);
		return TableBoard;
	}
	
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
	
	public class BorderCellRenderer extends DefaultTableCellRenderer
	{

		protected Border focusBorder = new LineBorder(Color.BLACK,2);

		/**
		 * Board cell renderer method.
		 */

		public BorderCellRenderer() {

		}

		/**
		 * This get the table component from the server.
		 * @param table table in the game.
		 * @param value object value in the table
		 * @param isSelected if value is selected.
		 * @param hasFocus boolean
		 * @param row table row
		 * @param col table column
		 * @return component from the server
		 */
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (hasFocus) {
			        setBorder(focusBorder);
				if (table.isCellEditable(row, column)) {
					setForeground( UIManager.getColor("Table.focusCellForeground") );
					setBackground( UIManager.getColor("Table.focusCellBackground") );
				}
			} else {
				setBorder(noFocusBorder);
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

}

/**
 * This class create border cell renderer for game board
 */

class BorderCellRenderer extends JLabel implements TableCellRenderer {
	  protected Border noFocusBorder;
	  protected Border columnBorder;

    /**
     * This method create a new empty border
     */

    public BorderCellRenderer() {
		noFocusBorder = new EmptyBorder(1, 2, 1, 2);
		setOpaque(true);
	  }

    /**
     * This method get the table cell renderer component.
     * @param table table in the game.
     * @param value object value in the table
     * @param isSelected if value is selected.
     * @param hasFocus boolean
     * @param row table row
     * @param col table column
     * @return component from the server
     */


	  public Component getTableCellRendererComponent(JTable table, Object value,
	  	boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
	  	setForeground(table.getSelectionForeground());
	  	setBackground(table.getSelectionBackground());
		} else {
	  	setForeground(table.getForeground());
	  	setBackground(table.getBackground());
		}
		setFont(table.getFont());


		if (hasFocus) {
	  	setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
	  	if (table.isCellEditable(row, column)) {
	    	setForeground(UIManager.getColor("Table.focusCellForeground"));
	    	setBackground(UIManager.getColor("Table.focusCellBackground"));
	  	}
		} else {
	  	if (value instanceof CellBorder) {
	    	Border border = ((CellBorder) value).getBorder();
	    	setBorder(border);
	  	} else {
	    	if (columnBorder != null) {
	      	setBorder(columnBorder);
	    	} else {
	      	setBorder(noFocusBorder);
	    	}
	  	}
		}
		setText((value == null) ? "" : value.toString());
		return this;
	  }

    /**
     * This method set the border column.
     * @param border border for the game board.
     */

	  public void setColumnBorder(Border border) {
		columnBorder = border;
	  }

    /**
     * This method get the column border.
     * @return column border
     */

	  public Border getColumnBorder() {
		return columnBorder;
	  }
}

/**
 * Cell border interface.
 */

interface CellBorder {


	  public Border getBorder();


	  public Border getBorder(int row, int column);


	  public void setBorder(Border border);


	  public void setBorder(Border border, int row, int column);


	}



class LinesBorder extends AbstractBorder implements SwingConstants {
	  protected int northThickness;
	  protected int southThickness;
	  protected int eastThickness;
	  protected int westThickness;
	  protected Color northColor;
	  protected Color southColor;
	  protected Color eastColor;
	  protected Color westColor;

    /**
     * This method set line of border
     * @param color color
     */
	  public LinesBorder(Color color) {
		this(color, 1);
	  }

    /**
     * This method set border line
     * @param color color
     * @param thickness line thickness
     */

	  public LinesBorder(Color color, int thickness) {
		setColor(color);
		setThickness(thickness);
	  }

    /**
     * This method set border line
     * @param color color
     * @param insets insets
     */

	  public LinesBorder(Color color, Insets insets) {
		setColor(color);
		setThickness(insets);
	  }

    /**
     * This creates board method
     * @param c component
     * @param g graphics
     * @param x integer x for border
     * @param y integer y for border
     * @param width integer width for border
     * @param height integer height for border
     */

	  public void paintBorder(Component c, Graphics g, int x, int y, int width,int height) {
		  Color oldColor = g.getColor();
		  
		  g.setColor(northColor);
			for (int i = 0; i < northThickness; i++) {
		  	g.drawLine(x, y + i, x + width - 1, y + i);
			}
			g.setColor(southColor);
			for (int i = 0; i < southThickness; i++) {
		  	g.drawLine(x, y + height - i - 1, x + width - 1, y + height- i - 1);
			}
			g.setColor(eastColor);
			for (int i = 0; i < westThickness; i++) {
		  	g.drawLine(x + i, y, x + i, y + height - 1);
			}
			g.setColor(westColor);
			for (int i = 0; i < eastThickness; i++) {
		  	g.drawLine(x + width - i - 1, y, x + width - i - 1, y + height - 1);
			}
			g.setColor(oldColor);
		  }

    /**
     * This method creates getting border insets
      * @param c component
     * @return new insets
     */

	  public Insets getBorderInsets(Component c) {
		return new Insets(northThickness, westThickness, southThickness,eastThickness);
	  }

    /**
     * This method creates getting insets for border.
     * @param c component
     * @param insets insets
     * @return new insets
     */


	  public Insets getBorderInsets(Component c, Insets insets) {
		return new Insets(northThickness, westThickness, southThickness,eastThickness);
	  }

    /**
     * Check if border is opaque.
     * @return true if it is opaque
     */


	  public boolean isBorderOpaque() {
		return true;
	  }

    /**
     * Set color
     * @param c color
     */


	  public void setColor(Color c) {
		northColor = c;
		southColor = c;
		eastColor = c;
		westColor = c;
	  }

    /**
     * This method set color for the game
     * @param c color
     * @param direction deirection
     */


	  public void setColor(Color c, int direction) {
		switch (direction) {
		case NORTH:
	  	northColor = c;
	  	break;
		case SOUTH:
	  	southColor = c;
	  	break;
		case EAST:
	  	eastColor = c;
	  	break;
		case WEST:
	  	westColor = c;
	  	break;
		default:
		}
	  }

    /**
     * Method for setting thickness.
     * @param n
     */


	  public void setThickness(int n) {
		northThickness = n;
		southThickness = n;
		eastThickness = n;
		westThickness = n;
	  }

    /**
     * Method for setting thickness.
     * @param insets
     */


	  public void setThickness(Insets insets) {
		northThickness = insets.top;
		southThickness = insets.bottom;
		eastThickness = insets.right;
		westThickness = insets.left;
	  }

    /**
     * This method set thickness.
     * @param n
     * @param direction
     */


	  public void setThickness(int n, int direction) {
		switch (direction) {
		case NORTH:
	  	northThickness = n;
	  	break;
		case SOUTH:
	  	southThickness = n;
	  	break;
		case EAST:
	  	eastThickness = n;
	  	break;
		case WEST:
	  	westThickness = n;
	  	break;
		default:
		}
	  }

    /**
     * @param insets insert into table
     */

	  public void append(Insets insets) {

	  	northThickness = Math.max(northThickness, insets.top);
	  	southThickness = Math.max(southThickness, insets.bottom);
	  	eastThickness = Math.max(eastThickness, insets.right);
	  	westThickness = Math.max(westThickness, insets.left);
	  }
	  
	}
