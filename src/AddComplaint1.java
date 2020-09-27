import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddComplaint1 extends JFrame {

	private JPanel contentPane;
	private JTextField ComplaintNotextField;
	private JTextField CustomerNamextField;
	private JTextField AddresstextField;
	private JTextField ContacttextField;
	private JTextField SerialNotextField;
	private JTable table;
	private JTextField ModuleNotextField;
    private JComboBox CategorycomboBox;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddComplaint1 frame = new AddComplaint1();
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
   private JTextField SearchtextField;
   
   
	public void ShowData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select *from Complaints";
			ps=con.prepareStatement(sql);
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
	public AddComplaint1() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddComplaint1.class.getResource("/image/logo.jpg")));
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AddDealer.class.getResource("/Images/logo.jpg")));
		setTitle("Add Complaints");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Complaint No:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel.setBounds(37, 36, 242, 26);
		contentPane.add(lblNewLabel);
		
		ComplaintNotextField = new JTextField();
		ComplaintNotextField.setForeground(new Color(0, 0, 128));
		ComplaintNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		ComplaintNotextField.setBounds(315, 37, 167, 26);
		contentPane.add(ComplaintNotextField);
		ComplaintNotextField.setColumns(10);
		
		JLabel lblDealercompanyName = new JLabel("Customer Name:");
		lblDealercompanyName.setForeground(new Color(255, 255, 255));
		lblDealercompanyName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblDealercompanyName.setBounds(37, 75, 254, 26);
		contentPane.add(lblDealercompanyName);
		
		CustomerNamextField = new JTextField();
		CustomerNamextField.setForeground(new Color(0, 0, 128));
		CustomerNamextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CustomerNamextField.setColumns(10);
		CustomerNamextField.setBounds(315, 76, 512, 26);
		contentPane.add(CustomerNamextField);
		
		JLabel lblDate = new JLabel("Address:");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblDate.setBounds(37, 114, 226, 26);
		contentPane.add(lblDate);
		
		AddresstextField = new JTextField();
		AddresstextField.setForeground(new Color(0, 0, 128));
		AddresstextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		AddresstextField.setColumns(10);
		AddresstextField.setBounds(315, 114, 945, 26);
		contentPane.add(AddresstextField);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblCategory.setBounds(37, 270, 116, 26);
		contentPane.add(lblCategory);
		
		JComboBox CategorycomboBox = new JComboBox();
		CategorycomboBox.setForeground(new Color(0, 0, 128));
		CategorycomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CategorycomboBox.setBounds(317, 272, 165, 26);
		contentPane.add(CategorycomboBox);
		contentPane.add(CategorycomboBox);
		CategorycomboBox.addItem("Sent");
		CategorycomboBox.addItem("Unsent");
		contentPane.add(CategorycomboBox);
		
		JLabel lblPaidAmmount = new JLabel("Contact:");
		lblPaidAmmount.setForeground(new Color(255, 255, 255));
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblPaidAmmount.setBounds(37, 153, 175, 26);
		contentPane.add(lblPaidAmmount);
		
		ContacttextField = new JTextField();
		ContacttextField.setForeground(new Color(0, 0, 128));
		ContacttextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		ContacttextField.setColumns(10);
		ContacttextField.setBounds(317, 153, 165, 26);
		contentPane.add(ContacttextField);
		
		JLabel lblPendingAmmount = new JLabel("Serial No:");
		lblPendingAmmount.setForeground(new Color(255, 255, 255));
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblPendingAmmount.setBounds(37, 192, 226, 26);
		contentPane.add(lblPendingAmmount);
		
		SerialNotextField = new JTextField();
		SerialNotextField.setForeground(new Color(0, 0, 128));
		SerialNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		SerialNotextField.setColumns(10);
		SerialNotextField.setBounds(317, 192, 165, 26);
		contentPane.add(SerialNotextField);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
				String sql1="insert into Complaints values(?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql1);
				ps.setString(1,null);
				ps.setString(2,CustomerNamextField.getText());
				ps.setString(3,AddresstextField.getText());
				String Category=(String) CategorycomboBox.getSelectedItem();
				System.out.println(Category);
				ps.setString(4,Category);
				ps.setString(5,ContacttextField.getText());
				ps.setString(6,SerialNotextField.getText());
				ps.setString(7,ModuleNotextField.getText());
				ps.executeUpdate();
				ComplaintNotextField.setText("");
				CustomerNamextField.setText("");
				AddresstextField.setText("");
				ContacttextField.setText("");
				SerialNotextField.setText("");
				ModuleNotextField.setText("");
				
				}
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e1);	
				}
				ShowData();
			}
		});
		btnSave.setForeground(new Color(0, 0, 128));
		btnSave.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnSave.setBounds(315,  322, 122, 35);
		contentPane.add(btnSave);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 384, 1240, 334);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				ComplaintNotextField.setText(model.getValueAt(i,0).toString());
				CustomerNamextField.setText(model.getValueAt(i,1).toString());
				AddresstextField.setText(model.getValueAt(i,2).toString());
				CategorycomboBox.setSelectedItem(model.getValueAt(i,3).toString());
				ContacttextField.setText(model.getValueAt(i,4).toString());
				SerialNotextField.setText(model.getValueAt(i,5).toString());
				ModuleNotextField.setText(model.getValueAt(i,6).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Invoice No", "Date ", "Dealer/Company Name", "Category", "Total Ammount", "Paid Ammount", "Pending Ammount"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new Home().setVisible(true);
			}
		});
		btnBack.setForeground(new Color(0, 0, 128));
		btnBack.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnBack.setBounds(810, 322, 122, 35);
		contentPane.add(btnBack);
		
		JLabel lblTotalAmmount = new JLabel("Module No:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblTotalAmmount.setBounds(37, 231, 175, 26);
		contentPane.add(lblTotalAmmount);
		
		ModuleNotextField= new JTextField();
		ModuleNotextField.setForeground(new Color(0, 0, 128));
		ModuleNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		ModuleNotextField.setColumns(10);
		ModuleNotextField.setBounds(317, 231, 165, 26);
		contentPane.add(ModuleNotextField);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
				String sql2="DELETE FROM Complaints WHERE Complaint_No=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql2);
				ps.setString(1,ComplaintNotextField.getText());
				ps.executeUpdate();
				ComplaintNotextField.setText("");
				CustomerNamextField.setText("");
				AddresstextField.setText("");
				ContacttextField.setText("");
				SerialNotextField.setText("");
				ModuleNotextField.setText("");
				
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnDelete.setBounds(643, 322, 122, 35);
		contentPane.add(btnDelete);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
				String sql3="UPDATE Complaints SET Customer_Name=?,Address=?,Category=?,Contact=?,Serial_No=?,Module_No=? WHERE Complaint_No=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql3);
				ps.setString(7,ComplaintNotextField.getText());
				ps.setString(1,CustomerNamextField.getText());
				ps.setString(2,AddresstextField.getText());
				String s1=(String) CategorycomboBox.getSelectedItem();
				ps.setString(3,s1);
				ps.setString(4,ContacttextField.getText());
				ps.setString(5,SerialNotextField.getText());
				ps.setString(6,ModuleNotextField.getText());
				ps.executeUpdate();
				ComplaintNotextField.setText("");
				CustomerNamextField.setText("");
				AddresstextField.setText("");
				ContacttextField.setText("");
				SerialNotextField.setText("");
				ModuleNotextField.setText("");
				
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnEdit.setForeground(new Color(0, 0, 128));
		btnEdit.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnEdit.setBounds(474, 322, 122, 35);
		contentPane.add(btnEdit);
		
		JLabel lblNewLabel_2 = new JLabel("Search Complaint No:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(668, 36, 264, 26);
		contentPane.add(lblNewLabel_2);
		
		SearchtextField = new JTextField();
		SearchtextField.setForeground(new Color(0, 0, 128));
		SearchtextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		SearchtextField.setColumns(10);
		SearchtextField.setBounds(948, 38, 198, 26);
		contentPane.add(SearchtextField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					String sql5="select *from Complaints where Complaint_No=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,SearchtextField.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String Complaint_No=rs.getString("Complaint_No");
						ComplaintNotextField.setText(Complaint_No);
						String Customer_Name=rs.getString("Customer_Name");
						CustomerNamextField.setText(Customer_Name);
						String Address=rs.getString("Address");
						AddresstextField.setText(Address);
						String Category=rs.getString("Category");
						CategorycomboBox.setSelectedItem(Category);
						String Contact=rs.getString("Contact");
						ContacttextField.setText(Contact);
						String Serial_No=rs.getString("Serial_No");
						SerialNotextField.setText(Serial_No);
						String Module_No=rs.getString("Module_No");
						ModuleNotextField.setText(Module_No);
						
						
					}
				} 
				catch(Exception e)
				{
					
				}
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnSearch.setBounds(1177, 32, 122, 35);
		contentPane.add(btnSearch);
		
		
		ShowData();
	
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(AddComplaint1.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(0,0,1366, 729);
		contentPane.add(wallpaperLabel);
			
	}
}

