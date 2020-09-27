import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class StockList extends JFrame {

	private JPanel contentPane;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private JTable table;
	private JTextField CategorytextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockList frame = new StockList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	/**
	 * Create the frame.
	 */
	public StockList() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StockList.class.getResource("/image/logo.jpg")));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(StockList.class.getResource("/Images/logo.jpg")));
		setTitle("Stock List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1,1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(228, 94, 740, 584);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Category:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel.setBounds(236, 24, 116, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Show List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {				
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
                    String sql="select Product_Name,Quantity from Stock where Category=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,CategorytextField.getText());
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnNewButton.setBounds(645, 24, 148, 33);
		contentPane.add(btnNewButton);
		
		CategorytextField = new JTextField();
		CategorytextField.setForeground(new Color(0, 0, 128));
		CategorytextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		CategorytextField.setBounds(362, 26, 273, 29);
		contentPane.add(CategorytextField);
		CategorytextField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new Home().setVisible(true);
			}
		});
		btnBack.setForeground(new Color(0, 0, 128));
		btnBack.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnBack.setBounds(803, 24, 148, 33);
		contentPane.add(btnBack);
		 
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(StockList.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1366, 768);
		contentPane.add(wallpaperLabel);
			
		
		
	}
}
