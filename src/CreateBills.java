import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.xdevapi.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CreateBills extends JFrame {

	private JComboBox comboBoxclass;
	private JComboBox comboBoxcategory;
	private JPanel contentPane;
	private JTextField CustomerNametextField;
	private JTextField ContactNotextField;
	private JTextField SrNotextField;
	private JTextField InvoiceNotextField;
	private JTextField QuantitytextField;
	private JTextField RateRstextField;
	private JTextField DicounttextField;
	private JTextField TotalAmmounttextField;
	private JTable table;
	private JTextField PaidAmmounttextField;
	private JTextField PendingAmmounttextField;
	private JTextField textFieldSerialNo;
	private JTextField textFieldModuleNo;

	private JTextField textFieldDate;
	private JTextField AllProductsTotalAmmounttextField;
	private JTextField textFieldTime;
	private JComboBox ProductNamecomboBox;
	private JComboBox cbCategory;
    long eventMask;
	ResultSet rs;
	Statement stmt;
	String sql;
	String q;
	int q1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() throws NumberFormatException {
				try {
					CreateBills frame = new CreateBills();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Statement st;
	
	public void total()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select sum(Total) from Products where Invoice_No=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,InvoiceNotextField.getText());
			rs=ps.executeQuery();
			if(rs.next())
			{
				String sum=rs.getString("sum(Total)");
				
				AllProductsTotalAmmounttextField.setText(sum);
				
			}
			
		} 
		catch(NumberFormatException | SQLException e) 
		{
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void GenerateNo()
	{
		try {
			java.sql.Statement st=con.createStatement();
			ResultSet rs=((java.sql.Statement) st).executeQuery("select MAX(Invoice_No) from customer");
			rs.next();
			rs.getString("MAX(Invoice_No)");
			if(rs.getString("MAX(Invoice_No)")==null)
			{
				InvoiceNotextField.setText("E-0000001");
			}
			else
			{
				long id=Long.parseLong(rs.getString("MAX(Invoice_No)").substring(2,rs.getString("MAX(Invoice_No)").length()));
				id++;
				InvoiceNotextField.setText("E-"+String.format("%07d", id));
				
			}
			
		} catch (SQLException | ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void ProductNo()
	{
		try {
			java.sql.Statement st=con.createStatement();
			ResultSet rs=((java.sql.Statement) st).executeQuery("select MAX(Product_No) from Products");
			rs.next();
			rs.getString("MAX(Product_No)");
			if(rs.getString("MAX(Product_No)")==null)
			{
				textFieldProduct_No.setText("P-0000001");
			}
			else
			{
				long id=Long.parseLong(rs.getString("MAX(Product_No)").substring(2,rs.getString("MAX(Product_No)").length()));
				id++;
				textFieldProduct_No.setText("P-"+String.format("%07d", id));
				
			}
			
		} catch (SQLException | ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void ShowDataInvoiceNo()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select Sr_No,Products,Serial_No,Module_No,Rate_Rs,Quantity,Discount,Discount_Price,Total from Products where Invoice_No=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,InvoiceNotextField.getText());
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	public void date()
	{
	
		Date date=new Date();
		SimpleDateFormat d=new SimpleDateFormat("dd/MM/YYYY");
		textFieldDate.setText(d.format(date));
	}
	
	public void clock()
	{
		Thread clock=new Thread()
				{
			public void run()
			{
				try 
				{
					for(;;) {
					Calendar cal=new GregorianCalendar();
					
					
					int sec=cal.get(Calendar.SECOND);
					
					int min=cal.get(Calendar.MINUTE);
					
					int hour=cal.get(Calendar.HOUR_OF_DAY);
					
					String t=hour+":"+min+":"+sec;
					
					textFieldTime.setText(t);
					sleep(1000);
					}
				} 
				catch(Exception e)
				{
					
				}
			}
				};
		
				clock.start();
		
	}
	
	
	
	/**
	 * Create the frame.
	 */
	Connection con;
	private JTextField textFieldAddress;
	private JTextField UnitDiscounttextField;
	PreparedStatement ps;
	private JTextField textFieldProduct_No;
	
	
	
	
	
	
	public CreateBills() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			
		} 
		catch(Exception e)
		{
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1,1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cbName = new JComboBox();
		cbName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					String sql="SELECT Unit_Price FROM Stock WHERE Product_Name=?";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					PreparedStatement ps=con.prepareStatement(sql);
					String pname=(String) cbName.getSelectedItem();
					
                    ps.setString(1,pname);
                    ResultSet rs=ps.executeQuery();
					
					while(rs.next())
					{
						String Rate=rs.getString("Unit_Price");
						RateRstextField.setText(Rate);
					}
					
				} 
				catch(Exception e1)
				{
					
				}
				
				try
				{
					
					String s="select Quantity from Stock where Product_Name=?";
					ps=con.prepareStatement(s);
					
					String prod1=(String) cbName.getSelectedItem();
					
					ps.setString(1,prod1);
					
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						
						String Quan=rs.getString("Quantity");
						
						int q5=Integer.parseInt(Quan);
						
						if(q5<=0)
						{
							
							String msg="Sorry! "+prod1+" Not available.";
							
							JOptionPane.showMessageDialog(null, msg);
						}
					}
				} 
				catch(Exception e1) {}
			}
		});
		cbName.setForeground(new Color(0, 0, 128));
		cbName.setFont(new Font("Dialog", Font.PLAIN, 19));
		cbName.setBounds(241, 368, 326, 26);
		contentPane.add(cbName);
		
		
		JComboBox cbCategory = new JComboBox();
		cbCategory.setForeground(new Color(0, 0, 128));
		cbCategory.setFont(new Font("Dialog", Font.PLAIN, 19));
		cbCategory.addItem("Electronics");
		cbCategory.addItem("Electricals");
		cbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					String sql="SELECT Product_Name FROM Stock WHERE Category=? ORDER BY Product_Name ASC";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					PreparedStatement ps=con.prepareStatement(sql);
					String sname=(String) cbCategory.getSelectedItem();
					ps.setString(1,sname);
					
					ResultSet rs=ps.executeQuery();
					cbName.removeAllItems();
					while(rs.next())
					{
					String a=rs.getString(1);
					cbName.addItem(a);
					
					}
					
				} 
				catch(Exception e)
				{
					
				}
			}
		});
		cbCategory.setBounds(241, 327, 150, 30);
		contentPane.add(cbCategory);
		
		JLabel lblNewLabel = new JLabel("Add Customer Details");
		lblNewLabel.setBounds(174, 36, 217, 30);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setBounds(52, 103, 167, 30);
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblCustomerName);
		
		CustomerNametextField = new JTextField();
		CustomerNametextField.setForeground(new Color(0, 0, 128));
		CustomerNametextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		CustomerNametextField.setBounds(241, 103, 425, 26);
		contentPane.add(CustomerNametextField);
		CustomerNametextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(52, 140, 85, 30);
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblAddress);
		
		JLabel lblContactNo = new JLabel("Contact No:");
		lblContactNo.setBounds(52, 176, 110, 30);
		lblContactNo.setForeground(Color.WHITE);
		lblContactNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblContactNo);
		
		ContactNotextField = new JTextField();
		ContactNotextField.setForeground(new Color(0, 0, 128));
		ContactNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		ContactNotextField.setBounds(236, 176, 279, 26);
		ContactNotextField.setColumns(10);
		contentPane.add(ContactNotextField);
		
		JLabel lblAddProductDetails = new JLabel("Add Product Details");
		lblAddProductDetails.setBounds(174, 209, 185, 30);
		lblAddProductDetails.setForeground(Color.WHITE);
		lblAddProductDetails.setFont(new Font("Dialog", Font.PLAIN, 20));
		contentPane.add(lblAddProductDetails);
		
		JLabel lblSrNo = new JLabel("Sr No:");
		lblSrNo.setBounds(52, 287, 62, 30);
		lblSrNo.setForeground(Color.WHITE);
		lblSrNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblSrNo);
		
		SrNotextField = new JTextField();
		SrNotextField.setForeground(new Color(0, 0, 128));
		SrNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		SrNotextField.setBounds(241, 287, 150, 26);
		SrNotextField.setColumns(10);
		contentPane.add(SrNotextField);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceNo.setBounds(54, 64, 110, 34);
		lblInvoiceNo.setForeground(Color.WHITE);
		lblInvoiceNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblInvoiceNo);
		
		InvoiceNotextField = new JTextField();
		InvoiceNotextField.setForeground(new Color(0, 0, 128));
		InvoiceNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		InvoiceNotextField.setBounds(241, 66, 207, 26);
		InvoiceNotextField.setColumns(10);
		contentPane.add(InvoiceNotextField);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(52, 327, 97, 30);
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblCategory);
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(46, 366, 134, 30);
		lblProductName.setForeground(Color.WHITE);
		lblProductName.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblProductName);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(52, 574, 85, 30);
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblQuantity);
		
		QuantitytextField = new JTextField();
		QuantitytextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					
					String s="select Quantity from Stock where Product_Name=?";
					ps=con.prepareStatement(s);
					
					String prod1=(String) cbName.getSelectedItem();
					
					ps.setString(1,prod1);
					
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						
						String Quan=rs.getString("Quantity");
						
						int q5=Integer.parseInt(Quan);
						
						
						String q6=QuantitytextField.getText();
						int quantity1=Integer.parseInt(q6);
						
						if(q5>=quantity1 && q5>=0)
						{
							String r1=RateRstextField.getText();
							int rate=Integer.parseInt(r1);
							
							String d1=DicounttextField.getText();
							int discountInPer=Integer.parseInt(d1);
							
							String q1=QuantitytextField.getText();
							int quantity=Integer.parseInt(q1);
							
							int discount=(discountInPer*rate)/100;
							int unitDiscount=rate-discount;
							
							UnitDiscounttextField.setText(String.valueOf(unitDiscount));
							int total=unitDiscount*quantity;
							
							TotalAmmounttextField.setText(String.valueOf(total));
						}
						else if(q5>0 && q5<quantity1)
						{
							String msg2="Only "+q5+" Pieces are available.";
							JOptionPane.showMessageDialog(null,msg2);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Unavailable");
						}
					}
					}
			
				catch(Exception e1) {}
				
			}
		});
		QuantitytextField.setForeground(new Color(0, 0, 128));
		QuantitytextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		QuantitytextField.setBounds(241, 574, 150, 26);
		QuantitytextField.setColumns(10);
		contentPane.add(QuantitytextField);
		
		JLabel lblRateRs = new JLabel("Rate Rs:");
		lblRateRs.setBounds(46, 466, 85, 30);
		lblRateRs.setForeground(Color.WHITE);
		lblRateRs.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblRateRs);
		
		RateRstextField = new JTextField();
		RateRstextField.setForeground(new Color(0, 0, 128));
		RateRstextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		RateRstextField.setBounds(241, 462, 150, 26);
		RateRstextField.setColumns(10);
		contentPane.add(RateRstextField);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(417, 800, 54, 30);
		lblUnit.setForeground(Color.WHITE);
		lblUnit.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		contentPane.add(lblUnit);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setBounds(46, 503, 85, 26);
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblDiscount);
		
		DicounttextField = new JTextField();
		DicounttextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try {
				String r1=RateRstextField.getText();
				int rate=Integer.parseInt(r1);
				
				String d1=DicounttextField.getText();
				int discountInPer=Integer.parseInt(d1);
				
				String q1=QuantitytextField.getText();
				int quantity=Integer.parseInt(q1);
				
				int discount=(discountInPer*rate)/100;
				int unitDiscount=rate-discount;
				
				UnitDiscounttextField.setText(String.valueOf(unitDiscount));
				int total=unitDiscount*quantity;
				
				TotalAmmounttextField.setText(String.valueOf(total));
				}
				catch(NumberFormatException e2)
				{
					
				}
			}
		});
		DicounttextField.setForeground(new Color(0, 0, 128));
		DicounttextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		DicounttextField.setBounds(241, 499, 150, 26);
		DicounttextField.setColumns(10);
		contentPane.add(DicounttextField);
		
		JLabel lblRs = new JLabel("%");
		lblRs.setBounds(401, 501, 25, 30);
		lblRs.setForeground(Color.WHITE);
		lblRs.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblRs);
		
		JLabel lblUnit_1_1 = new JLabel("Rs");
		lblUnit_1_1.setBounds(401, 460, 35, 30);
		lblUnit_1_1.setForeground(Color.WHITE);
		lblUnit_1_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblUnit_1_1);
		
		JLabel lblTotalAmmount = new JLabel("Total Price:");
		lblTotalAmmount.setBounds(52, 615, 103, 23);
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblTotalAmmount);
		
		TotalAmmounttextField = new JTextField();
		TotalAmmounttextField.setForeground(new Color(0, 0, 128));
		TotalAmmounttextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		TotalAmmounttextField.setBounds(241, 611, 150, 23);
		TotalAmmounttextField.setColumns(10);
		contentPane.add(TotalAmmounttextField);
		
		JLabel lblUnit_1_1_1 = new JLabel("Rs");
		lblUnit_1_1_1.setBounds(401, 613, 35, 26);
		lblUnit_1_1_1.setForeground(Color.WHITE);
		lblUnit_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblUnit_1_1_1);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(81, 662, 150, 40);
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String qq=QuantitytextField.getText();
				int qq1=Integer.parseInt(qq);	
				
				
				try 
				{
				String sql1="insert into Products values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql1);
				ps.setString(1,InvoiceNotextField.getText());
				ps.setString(2,textFieldProduct_No.getText());
				ps.setString(3,SrNotextField.getText());
				String Category=(String) cbCategory.getSelectedItem();
				ps.setString(4,Category);
				String pname=(String) cbName.getSelectedItem();
				ps.setString(5,pname);
				ps.setString(6,textFieldSerialNo.getText());
				ps.setString(7,textFieldModuleNo.getText());
				ps.setString(8,RateRstextField.getText());
				ps.setString(9,DicounttextField.getText());
				ps.setString(10,QuantitytextField.getText());
				ps.setString(11,UnitDiscounttextField.getText());
				ps.setString(12,TotalAmmounttextField.getText());
				ps.setString(13,textFieldDate.getText());
				ps.setString(14,textFieldTime.getText());
				
				
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"inserted into products");
				SrNotextField.setText("");
				textFieldSerialNo.setText("");
				textFieldModuleNo.setText("");
				RateRstextField.setText("");
				DicounttextField.setText("");
				UnitDiscounttextField.setText("");
				QuantitytextField.setText("");
				TotalAmmounttextField.setText("");
				ProductNo();
				total();
				ShowDataInvoiceNo();
				
			
				
				}
				
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
				ShowDataInvoiceNo();
				
				try 
				{
					String sql11="insert into bills values(?,?)";
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
					ps=con.prepareStatement(sql11);
					ps.setString(1,InvoiceNotextField.getText());
					ps.setString(2,textFieldProduct_No.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"inserted into bills");
				} 
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
				
				try
				{
					
					String sql="select Quantity from Stock where Product_Name=?";
					ps=con.prepareStatement(sql);
					
					String Prod_Name=(String) cbName.getSelectedItem();
					ps.setString(1,Prod_Name);
					
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						
						String q2=rs.getString("Quantity");
						
						int q3=Integer.parseInt(q2);	
						int q4=q3-qq1;
						String Final_Quantity=String.valueOf(q4);
						
						try 
						{
						
						String sql3="UPDATE Stock SET Quantity=? WHERE Product_Name=?";	
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
						ps=con.prepareStatement(sql3);
						
						String Prod=(String) cbName.getSelectedItem();
						ps.setString(2,Prod);
						
						
						ps.setString(1,Final_Quantity);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"Stock updated");
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,"Stock updated failed");
						}
					}
					
				} 
				catch(Exception e) {}
				
				ShowDataInvoiceNo();
				total();
					
			}
		});
		btnAddProduct.setForeground(new Color(0, 0, 128));
		btnAddProduct.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(btnAddProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(692, 36, 648, 452);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				SrNotextField.setText(model.getValueAt(i,0).toString());
				textFieldSerialNo.setText(model.getValueAt(i,1).toString());
				textFieldModuleNo.setText(model.getValueAt(i,2).toString());
				cbName.setSelectedItem(model.getValueAt(i,3).toString());
				QuantitytextField.setText(model.getValueAt(i,4).toString());
				RateRstextField.setText(model.getValueAt(i,5).toString());
				DicounttextField.setText(model.getValueAt(i,6).toString());
				UnitDiscounttextField.setText(model.getValueAt(i,7).toString());
				TotalAmmounttextField.setText(model.getValueAt(i,8).toString());


				
			}
		});
		table.setRowHeight(23);
		JTableHeader Theader=table.getTableHeader();
		Theader.setBackground(Color.WHITE);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Baskerville Old Face",Font.PLAIN,20));
		((DefaultTableCellRenderer)Theader.getDefaultRenderer()).setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
		table.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		table.setBorder(UIManager.getBorder("ScrollPane.border"));
		table.setSelectionBackground(new Color(100, 149, 237));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setRowMargin(2);
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Baskerville Old Face", Font.PLAIN, 23));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr No", "Serial No", "Module No", "Description of Goods", "Quantity", "Rate Rs", "Discount%", "Total Ammount"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(422, 662, 134, 40);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new Home().setVisible(true);
				CreateBills.this.dispose();
			}
		});
		btnCancel.setForeground(new Color(0, 0, 128));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Paid Ammount:");
		lblNewLabel_1.setBounds(773, 570, 150, 30);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel_1);
		
		PaidAmmounttextField = new JTextField();
		PaidAmmounttextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				String p1=PaidAmmounttextField.getText();
				int paid=Integer.parseInt(p1);
				String t5=AllProductsTotalAmmounttextField.getText();
				int total2=Integer.parseInt(t5);
				int pending=total2-paid;
				PendingAmmounttextField.setText(String.valueOf(pending));
				
			}
		});
		PaidAmmounttextField.setForeground(new Color(0, 0, 128));
		PaidAmmounttextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		PaidAmmounttextField.setBounds(973, 570, 149, 30);
		contentPane.add(PaidAmmounttextField);
		PaidAmmounttextField.setColumns(10);
		
		JLabel lblUnit_1_1_1_1 = new JLabel("Rs");
		lblUnit_1_1_1_1.setBounds(1132, 574, 35, 31);
		lblUnit_1_1_1_1.setForeground(Color.WHITE);
		lblUnit_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblUnit_1_1_1_1);
		
		JLabel lblUnit_1_1_1_1_1 = new JLabel("Rs");
		lblUnit_1_1_1_1_1.setBounds(1142, 611, 25, 30);
		lblUnit_1_1_1_1_1.setForeground(Color.WHITE);
		lblUnit_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblUnit_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pending Ammount:");
		lblNewLabel_1_1.setBounds(773, 611, 185, 30);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1_1);
		
		PendingAmmounttextField = new JTextField();
		PendingAmmounttextField.setForeground(new Color(0, 0, 128));
		PendingAmmounttextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		PendingAmmounttextField.setBounds(973, 613, 149, 27);
		PendingAmmounttextField.setColumns(10);
		contentPane.add(PendingAmmounttextField);
		
		
		
		JLabel lblSrNo_1 = new JLabel("Serial No:");
		lblSrNo_1.setBounds(46, 407, 97, 29);
		lblSrNo_1.setForeground(Color.WHITE);
		lblSrNo_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblSrNo_1);
		
		textFieldSerialNo = new JTextField();
		textFieldSerialNo.setForeground(new Color(0, 0, 128));
		textFieldSerialNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		textFieldSerialNo.setBounds(241, 401, 150, 26);
		textFieldSerialNo.setColumns(10);
		contentPane.add(textFieldSerialNo);
		
		JLabel lblNewLabel_2 = new JLabel("Module No:");
		lblNewLabel_2.setBounds(46, 441, 110, 26);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_2);
		
		textFieldModuleNo = new JTextField();
		textFieldModuleNo.setForeground(new Color(0, 0, 128));
		textFieldModuleNo.setFont(new Font("Dialog", Font.PLAIN, 19));
		textFieldModuleNo.setBounds(241, 431, 150, 26);
		contentPane.add(textFieldModuleNo);
		textFieldModuleNo.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(60, 6, 54, 37);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setForeground(new Color(0, 0, 128));
		textFieldDate.setFont(new Font("Dialog", Font.PLAIN, 19));
		textFieldDate.setBounds(133, 9, 110, 30);
		textFieldDate.setColumns(10);
		contentPane.add(textFieldDate);
		
		JLabel lblNewLabel_1_2 = new JLabel("Total Ammount:");
		lblNewLabel_1_2.setBounds(767, 529, 156, 30);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1_2);
		
		AllProductsTotalAmmounttextField = new JTextField();
		AllProductsTotalAmmounttextField.setForeground(new Color(0, 0, 128));
		AllProductsTotalAmmounttextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		AllProductsTotalAmmounttextField.setBounds(973, 529, 149, 30);
		AllProductsTotalAmmounttextField.setColumns(10);
		contentPane.add(AllProductsTotalAmmounttextField);
		
		JLabel lblUnit_1_1_1_1_2 = new JLabel("Rs");
		lblUnit_1_1_1_1_2.setBounds(1132, 523, 35, 40);
		lblUnit_1_1_1_1_2.setForeground(Color.WHITE);
		lblUnit_1_1_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		contentPane.add(lblUnit_1_1_1_1_2);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
				String sql1="insert into customer values(?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql1);
				ps.setString(1,textFieldDate.getText());
				ps.setString(2,textFieldTime.getText());
				ps.setString(3,InvoiceNotextField.getText());
				ps.setString(4,CustomerNametextField.getText());
				ps.setString(5,textFieldAddress.getText());
				ps.setString(6,ContactNotextField.getText());
				ps.setString(7,AllProductsTotalAmmounttextField.getText());
				ps.setString(8,PaidAmmounttextField.getText());
				ps.setString(9,PendingAmmounttextField.getText());
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"inserted into customer");
				CustomerNametextField.setText("");
				ContactNotextField.setText("");
				AllProductsTotalAmmounttextField.setText("");
				PaidAmmounttextField.setText("");
				PendingAmmounttextField.setText("");
				textFieldAddress.setText("");
				
				}
				
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
				GenerateNo();
				
				
				
				MessageFormat header=new MessageFormat("Invoice Print");
				MessageFormat footer=new MessageFormat("Page");
				try
				{
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch(Exception e4)
				{
					JOptionPane.showInternalMessageDialog(null,"Unable to print.");
				}
				
				
				
				
			}
		});
		btnPrint.setForeground(new Color(0, 0, 128));
		btnPrint.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnPrint.setBounds(973, 662, 134, 40);
		contentPane.add(btnPrint);
		
		JButton btnDiscard = new JButton("Discard");
		
		btnDiscard.setForeground(new Color(0, 0, 128));
		btnDiscard.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnDiscard.setBounds(259, 662, 134, 40);
		contentPane.add(btnDiscard);
		
		textFieldTime = new JTextField();
		textFieldTime.setForeground(new Color(0, 0, 128));
		textFieldTime.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		textFieldTime.setColumns(10);
		textFieldTime.setBounds(417, 12, 150, 30);
		contentPane.add(textFieldTime);
		
		JLabel lbltime = new JLabel("Time:");
		lbltime.setForeground(Color.WHITE);
		lbltime.setFont(new Font("Dialog", Font.PLAIN, 19));
		lbltime.setBounds(343, 9, 62, 30);
		contentPane.add(lbltime);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setForeground(new Color(0, 0, 128));
		textFieldAddress.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		textFieldAddress.setBounds(241, 140, 425, 26);
		contentPane.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JLabel lblDiscountunit = new JLabel("Discount Price:");
		lblDiscountunit.setForeground(Color.WHITE);
		lblDiscountunit.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblDiscountunit.setBounds(46, 540, 142, 29);
		contentPane.add(lblDiscountunit);
		
		UnitDiscounttextField = new JTextField();
		UnitDiscounttextField.setForeground(new Color(0, 0, 128));
		UnitDiscounttextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		UnitDiscounttextField.setColumns(10);
		UnitDiscounttextField.setBounds(241, 539, 150, 23);
		contentPane.add(UnitDiscounttextField);
		
		JLabel lblUnit_1_1_2 = new JLabel("Rs");
		lblUnit_1_1_2.setForeground(Color.WHITE);
		lblUnit_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblUnit_1_1_2.setBounds(401, 540, 35, 28);
		contentPane.add(lblUnit_1_1_2);
		
		JButton btnNewInvoice = new JButton("New Invoice");
		btnNewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				GenerateNo();
				ShowDataInvoiceNo();
				
				try 
				{
				String sql1="insert into FinalBills values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql1);
				ps.setString(1,textFieldDate.getText());
				ps.setString(2,textFieldTime.getText());
				ps.setString(3,InvoiceNotextField.getText());
				ps.setString(4,CustomerNametextField.getText());
				ps.setString(5,textFieldAddress.getText());
				ps.setString(6,ContactNotextField.getText());
				ps.setString(7,SrNotextField.getText());
				String Category=(String) cbCategory.getSelectedItem();
				ps.setString(8,Category);
				String pname=(String) cbName.getSelectedItem();
				ps.setString(9,pname);
				ps.setString(10,textFieldSerialNo.getText());
				ps.setString(11,textFieldModuleNo.getText());
				ps.setString(12,RateRstextField.getText());
				ps.setString(13,DicounttextField.getText());
				ps.setString(14,UnitDiscounttextField.getText());
				ps.setString(15,QuantitytextField.getText());
				ps.setString(16,TotalAmmounttextField.getText());
				ps.setString(17,PaidAmmounttextField.getText());
				ps.setString(18,PendingAmmounttextField.getText());
				ps.setString(19,AllProductsTotalAmmounttextField.getText());
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"inserted into final bills");
				}
				
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
				
				CustomerNametextField.setText("");
				ContactNotextField.setText("");
				textFieldAddress.setText("");
				SrNotextField.setText("");
				textFieldSerialNo.setText("");
				textFieldModuleNo.setText("");
				RateRstextField.setText("");
				DicounttextField.setText("");
				UnitDiscounttextField.setText("");
				QuantitytextField.setText("");
				TotalAmmounttextField.setText("");
				PaidAmmounttextField.setText("");
				PendingAmmounttextField.setText("");
				AllProductsTotalAmmounttextField.setText("");
			}
		});
		btnNewInvoice.setForeground(new Color(0, 0, 128));
		btnNewInvoice.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnNewInvoice.setBounds(575, 662, 142, 38);
		contentPane.add(btnNewInvoice);
		
		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setForeground(Color.WHITE);
		lblProductId.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblProductId.setBounds(52, 250, 110, 30);
		contentPane.add(lblProductId);
		
		textFieldProduct_No = new JTextField();
		textFieldProduct_No.setForeground(new Color(0, 0, 128));
		textFieldProduct_No.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		textFieldProduct_No.setColumns(10);
		textFieldProduct_No.setBounds(241, 250, 150, 26);
		contentPane.add(textFieldProduct_No);
		
		
		clock();
		date();
		GenerateNo();
		ProductNo();
		
		
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(CreateBills.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1366,768);
		contentPane.add(wallpaperLabel);
		
	
	}
}
