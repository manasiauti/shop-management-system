import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DeleteBill extends JFrame {

	private JPanel contentPane;
	private JTextField txtInvoiceNo;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtTotalAmmount;
	private JTextField txtPaidAmmount;
	private JTextField txtPendingAmmount;
	private JTable table;
	private JTextField txtDate;
	private JTextField txtTime;
	private JComboBox cbName;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBill frame = new DeleteBill();
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
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public DeleteBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1,1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice No:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblNewLabel.setBounds(45, 11, 115, 30);
		contentPane.add(lblNewLabel);
		
		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setForeground(new Color(0, 0, 128));
		txtInvoiceNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtInvoiceNo.setBounds(258, 11, 137, 30);
		contentPane.add(txtInvoiceNo);
		txtInvoiceNo.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
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
				    
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShowDataInvoiceNo();
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnSearch.setBounds(407, 7, 98, 35);
		contentPane.add(btnSearch);
		try 
		{
			String sql="SELECT Invoice_No FROM customer ORDER BY Time ASC";
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			String a=rs.getString(1);
			cbName.addItem(a);
			
			}
			
		} 
		catch(Exception e1)
		{
			
		}
		
		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(0, 0, 128));
		txtCustomerName.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(270, 93, 649, 27);
		contentPane.add(txtCustomerName);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblCustomerName.setBounds(45, 93, 155, 30);
		contentPane.add(lblCustomerName);
		
		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(0, 0, 128));
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtAddress.setColumns(10);
		txtAddress.setBounds(270, 131, 649, 27);
		contentPane.add(txtAddress);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblAddress.setBounds(45, 134, 84, 30);
		contentPane.add(lblAddress);
		
		txtContact = new JTextField();
		txtContact.setForeground(new Color(0, 0, 128));
		txtContact.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtContact.setColumns(10);
		txtContact.setBounds(270, 175, 252, 27);
		contentPane.add(txtContact);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblContact.setBounds(45, 175, 84, 30);
		contentPane.add(lblContact);
		
		JLabel lblTotalAmmount = new JLabel("Total Ammount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblTotalAmmount.setBounds(45, 216, 149, 30);
		contentPane.add(lblTotalAmmount);
		
		txtTotalAmmount = new JTextField();
		txtTotalAmmount.setForeground(new Color(0, 0, 128));
		txtTotalAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtTotalAmmount.setColumns(10);
		txtTotalAmmount.setBounds(270, 213, 155, 27);
		contentPane.add(txtTotalAmmount);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblRs.setBounds(430, 214, 34, 30);
		contentPane.add(lblRs);
		
		JLabel lblPaidAmmount = new JLabel("Paid Ammount:");
		lblPaidAmmount.setForeground(Color.WHITE);
		lblPaidAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblPaidAmmount.setBounds(45, 257, 137, 30);
		contentPane.add(lblPaidAmmount);
		
		txtPaidAmmount = new JTextField();
		txtPaidAmmount.setForeground(new Color(0, 0, 128));
		txtPaidAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtPaidAmmount.setColumns(10);
		txtPaidAmmount.setBounds(270, 257, 155, 30);
		contentPane.add(txtPaidAmmount);
		
		JLabel lblRs_1 = new JLabel("Rs");
		lblRs_1.setForeground(Color.WHITE);
		lblRs_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblRs_1.setBounds(430, 255, 34, 30);
		contentPane.add(lblRs_1);
		
		JLabel lblPendingAmmount = new JLabel("Pending Ammount:");
		lblPendingAmmount.setForeground(Color.WHITE);
		lblPendingAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblPendingAmmount.setBounds(45, 298, 171, 30);
		contentPane.add(lblPendingAmmount);
		
		txtPendingAmmount = new JTextField();
		txtPendingAmmount.setForeground(new Color(0, 0, 128));
		txtPendingAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtPendingAmmount.setColumns(10);
		txtPendingAmmount.setBounds(270, 298, 155, 27);
		contentPane.add(txtPendingAmmount);
		
		JLabel lblRs_2 = new JLabel("Rs");
		lblRs_2.setForeground(Color.WHITE);
		lblRs_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblRs_2.setBounds(430, 296, 34, 30);
		contentPane.add(lblRs_2);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Bill?","Delete this Record?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			    if(a==JOptionPane.YES_OPTION)
			    {
			    	try 
					{
					String sql2="DELETE FROM customer WHERE customer.Invoice_No=?";	
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					ps=con.prepareStatement(sql2);
					ps.setString(1,txtInvoiceNo.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"successfully deleted");
					txtInvoiceNo.setText("");
					txtDate.setText("");
					txtTime.setText("");
					txtCustomerName.setText("");
					txtAddress.setText("");
					txtContact.setText("");
					txtTotalAmmount.setText("");
					txtPaidAmmount.setText("");
					txtPendingAmmount.setText("");
					}
					catch(Exception e1) {}
			    }
				
			    ShowDataInvoiceNo();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnDelete.setBounds(270, 341, 98, 35);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new Home().setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnCancel.setBounds(407, 341, 103, 35);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 397, 1330, 321);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				//txtInvoiceNo.setText(model.getValueAt(i,0).toString());
				txtDate.setText(model.getValueAt(i,1).toString());
				txtTime.setText(model.getValueAt(i,2).toString());
				//cbName.setSelectedItem(model.getValueAt(i,3).toString());
				txtCustomerName.setText(model.getValueAt(i,3).toString());
				txtAddress.setText(model.getValueAt(i,4).toString());
				txtContact.setText(model.getValueAt(i,5).toString());
				txtTotalAmmount.setText(model.getValueAt(i,6).toString());
				txtPaidAmmount.setText(model.getValueAt(i,7).toString());
				txtPendingAmmount.setText(model.getValueAt(i,8).toString());

				
			}
		});
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblDate.setBounds(45, 52, 59, 30);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setForeground(new Color(0, 0, 128));
		txtDate.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtDate.setColumns(10);
		txtDate.setBounds(114, 52, 137, 30);
		contentPane.add(txtDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblTime.setBounds(268, 54, 66, 23);
		contentPane.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setForeground(new Color(0, 0, 128));
		txtTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		txtTime.setColumns(10);
		txtTime.setBounds(339, 52, 137, 30);
		contentPane.add(txtTime);
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(DeleteBill.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1366, 768);
		contentPane.add(wallpaperLabel);
	}

}
