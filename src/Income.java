import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Income extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDate;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Income frame = new Income();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private JTextField txtGrandTotal;
	
	public void total()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select Category,Products,Serial_No,Module_No,Quantity,Rate_Rs,Discount_per,Discount_Price,Total from BillsInfo where Date=? ORDER BY Time ASC";
			ps=con.prepareStatement(sql);
			ps.setString(1,textFieldDate.getText());
			rs=ps.executeQuery();
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				String sum=rs.getString("sum(Total)");
				
				txtGrandTotal.setText(sum);
				
			}
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	
	public void exit()
	{
		int a=JOptionPane.showConfirmDialog(null,"Exit the Mundhe Electronics?","Confirm Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	    if(a==JOptionPane.YES_OPTION)
	    {
	    	this.dispose();
	    }
	}
	
	
	
	public void ShowData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select *from Products where Date='26/08/2020' order by Time desc";
			ps=con.prepareStatement(sql);
			ps.setString(1,textFieldDate.getText());
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	/**
	 * Create the frame.
	 */
	public Income() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				//exit();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Income.class.getResource("/image/logo.jpg")));
		setTitle("Income");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Date:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblNewLabel.setBounds(39, 40, 121, 33);
		contentPane.add(lblNewLabel);
		
		textFieldDate = new JTextField();
		textFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDate.setForeground(new Color(0, 0, 128));
		textFieldDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		textFieldDate.setBounds(160, 40, 133, 30);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton btnNewButton = new JButton("Income");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					String sql="select Product_No,Time,Category,Products,Serial_No,Module_No,Rate_Rs,Discount,Quantity,Discount_Price,Total from Products where Date=? order by Time desc";
					ps=con.prepareStatement(sql);
					ps.setString(1,textFieldDate.getText());
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					String sql="select sum(Total) from Products where Date=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,textFieldDate.getText());
					rs=ps.executeQuery();
					while(rs.next())
					{
						String total=rs.getString("sum(Total)");
						txtGrandTotal.setText(String.valueOf(total));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnNewButton.setBounds(305, 40, 113, 33);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 99, 1261, 619);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(23);
		JTableHeader Theader=table.getTableHeader();
		Theader.setBackground(Color.WHITE);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Baskerville Old Face",Font.PLAIN,20));
		((DefaultTableCellRenderer)Theader.getDefaultRenderer()).setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
		table.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		table.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new Home().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnCancel.setBounds(430, 40, 113, 33);
		contentPane.add(btnCancel);
		
		JLabel lblTotal = new JLabel("Total Ammount:");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblTotal.setBounds(39, 933, 174, 33);
		contentPane.add(lblTotal);
		
		txtGrandTotal = new JTextField();
		txtGrandTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtGrandTotal.setForeground(new Color(0, 0, 128));
		txtGrandTotal.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		txtGrandTotal.setColumns(10);
		txtGrandTotal.setBounds(213, 934, 113, 30);
		contentPane.add(txtGrandTotal);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblRs.setBounds(331, 933, 33, 33);
		contentPane.add(lblRs);
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(Income.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1339,728);
		contentPane.add(wallpaperLabel);
	}
}
