import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;

public class SearchBill extends JFrame {

	private JPanel contentPane;
	private JTextField txtInvoiceNo;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    private JTextField txtCustomerName;
    private JLabel lblDate;
    private JTextField txtDate;
    private JLabel lblTime;
    private JTextField txtTime;
    private JLabel lblAddress;
    private JTextField txtAddress;
    private JLabel lblContact;
    private JTextField txtContact;
    private JTextField txtTotalAmmount;
    private JTextField txtPaidAmmount;
    private JTextField txtPendingAmmount;
    private JTable table;
    private JComboBox cbCategory;
    private JComboBox cbName;
    private JButton btnUpdate;
    private JButton btnCancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBill frame = new SearchBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void ShowDataInvoiceNo()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select Product_No,Sr_No,Category,Products,Serial_No,Module_No,Rate_Rs,Discount,Quantity,Discount_Price,Total from Products where Invoice_No=?";
		    ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void total()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select sum(Total) from Products where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,txtInvoiceNo.getText());
			rs=ps.executeQuery();
			if(rs.next())
			{
				String sum=rs.getString("sum(Total)");
				
				txtTotalAmmount.setText(sum);
				
			}
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public SearchBill() {
		setTitle("Update Payment");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchBill.class.getResource("/image/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1,1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtInvoiceNo.setBounds(165, 11, 176, 30);
		contentPane.add(txtInvoiceNo);
		txtInvoiceNo.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					String s="select *from customer,Products,Bills where customer.Invoice_No=Bills.Invoice_No and Products.Product_No=Bills.Product_No and customer.Invoice_No=?";
				    ps=con.prepareStatement(s);
				    ps.setString(1,txtInvoiceNo.getText());
				    rs=ps.executeQuery();
				    if(rs.next())
				    {
				    	String Date=rs.getString("Date");
						txtDate.setText(Date);
						String Time=rs.getString("Time");
						txtTime.setText(Time);
						String Customer_Name=rs.getString("Customer_Name");
						txtCustomerName.setText(Customer_Name);
						String Address=rs.getString("Address");
						txtAddress.setText(Address);
						String Contact=rs.getString("Contact");
						txtContact.setText(Contact);
						String Total_Ammount=rs.getString("Total_Ammount");
						txtTotalAmmount.setText(Total_Ammount);
						String Paid_Ammount=rs.getString("Paid_Ammount");
						txtPaidAmmount.setText(Paid_Ammount);
						String Pending_Ammount=rs.getString("Pending_Ammount");
						txtPendingAmmount.setText(Pending_Ammount);
						
				    }
				    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ShowDataInvoiceNo();

			}
		});
		btnSearch.setBounds(353, 9, 120, 35);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel = new JLabel("Invoice No:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblNewLabel.setBounds(35, 11, 120, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblCustomerName.setBounds(35, 95, 192, 31);
		contentPane.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(237, 95, 496, 30);
		contentPane.add(txtCustomerName);
		
		lblDate = new JLabel("Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblDate.setBounds(35, 53, 60, 31);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		txtDate.setColumns(10);
		txtDate.setBounds(107, 52, 120, 30);
		contentPane.add(txtDate);
		
		lblTime = new JLabel("Time:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblTime.setBounds(237, 52, 63, 27);
		contentPane.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtTime.setColumns(10);
		txtTime.setBounds(310, 52, 120, 29);
		contentPane.add(txtTime);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblAddress.setBounds(35, 131, 171, 31);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtAddress.setColumns(10);
		txtAddress.setBounds(237, 136, 496, 30);
		contentPane.add(txtAddress);
		
		lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblContact.setBounds(35, 173, 171, 31);
		contentPane.add(lblContact);
		
		txtContact = new JTextField();
		txtContact.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtContact.setColumns(10);
		txtContact.setBounds(237, 177, 496, 30);
		contentPane.add(txtContact);
		
		JLabel lblTotalAmmount_2 = new JLabel("Total Ammount:");
		lblTotalAmmount_2.setForeground(Color.WHITE);
		lblTotalAmmount_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblTotalAmmount_2.setBounds(35, 215, 171, 31);
		contentPane.add(lblTotalAmmount_2);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(237, 226, 176, 30);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblDiscount_1_1_2 = new JLabel("Rs");
		lblDiscount_1_1_2.setForeground(Color.WHITE);
		lblDiscount_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblDiscount_1_1_2.setBounds(423, 226, 28, 31);
		contentPane.add(lblDiscount_1_1_2);
		
		JLabel lblPaidAmmount = new JLabel("Paid Ammount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblPaidAmmount.setBounds(35, 265, 171, 31);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				try {
				String p1=txtPaidAmmount.getText();
				int paid=Integer.parseInt(p1);
				String t5=txtTotalAmmount.getText();
				int total2=Integer.parseInt(t5);
				int pending=total2-paid;
				txtPendingAmmount.setText(String.valueOf(pending));
				}
				catch(NumberFormatException e)
				{
					
				}
			}
		});
		txtPaidAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(237, 266, 176, 30);
		contentPane.add(txtPaidAmmount);
		
		JLabel lblDiscount_1_1_1_3 = new JLabel("Rs");
		lblDiscount_1_1_1_3.setForeground(Color.WHITE);
		lblDiscount_1_1_1_3.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblDiscount_1_1_1_3.setBounds(423, 268, 28, 31);
		contentPane.add(lblDiscount_1_1_1_3);
		
		JLabel lblPendingAmmount = new JLabel("Pending Ammount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblPendingAmmount.setBounds(35, 312, 205, 31);
		contentPane.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(237, 312, 176, 30);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblDiscount_1_1_1_2_1 = new JLabel("Rs");
		lblDiscount_1_1_1_2_1.setForeground(Color.WHITE);
		lblDiscount_1_1_1_2_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblDiscount_1_1_1_2_1.setBounds(423, 312, 28, 31);
		contentPane.add(lblDiscount_1_1_1_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 413, 1339, 316);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(23);
		JTableHeader Theader=table.getTableHeader();
		Theader.setBackground(Color.WHITE);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Baskerville Old Face",Font.PLAIN,20));
		((DefaultTableCellRenderer)Theader.getDefaultRenderer()).setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
		scrollPane.setViewportView(table);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
				String sql3="update customer,Products,Bills set Date=?,Time=?,Customer_Name=?,Address=?,Contact=?,Total_Ammount=?,Paid_Ammount=?,Pending_Ammount=? where customer.Invoice_No=?";
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql3);
				ps.setString(9,txtInvoiceNo.getText());
				ps.setString(1,txtDate.getText());
				ps.setString(2,txtTime.getText());
				ps.setString(3,txtCustomerName.getText());
				ps.setString(4,txtAddress.getText());
				ps.setString(5,txtContact.getText());
				ps.setString(6,txtTotalAmmount.getText());
				ps.setString(7,txtPaidAmmount.getText());
				ps.setString(8,txtPendingAmmount.getText());
				
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Bill updated");
				
				}
				catch(Exception e1) {}
				ShowDataInvoiceNo();
				total();
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnUpdate.setBounds(245, 367, 120, 35);
		contentPane.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new Home().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnCancel.setBounds(382, 367, 120, 35);
		contentPane.add(btnCancel);
		
		ShowDataInvoiceNo();
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(SearchBill.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1366, 768);
		contentPane.add(wallpaperLabel);
	}
}
