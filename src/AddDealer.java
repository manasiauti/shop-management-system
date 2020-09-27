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

public class AddDealer extends JFrame {

	private JPanel contentPane;
	private JTextField InvoiceNotextField;
	private JTextField DealertNamextField;
	private JTextField DatetextField;
	private JTextField PaidAmmounttextField;
	private JTextField PendingAmmounttextField;
	private JTable table;
	private JTextField TotalAmmounttextField;
    private JComboBox CategorycomboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDealer frame = new AddDealer();
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
   private JTextField InvoiceSearchtextField;
   
   
	public void ShowData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select *from DealerBillsInfo";
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
	public AddDealer() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddDealer.class.getResource("/image/logo.jpg")));
		setTitle("Add Dealer Bills");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice No:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel.setBounds(38, 35, 139, 26);
		contentPane.add(lblNewLabel);
		
		InvoiceNotextField = new JTextField();
		InvoiceNotextField.setForeground(new Color(0, 0, 128));
		InvoiceNotextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		InvoiceNotextField.setBounds(315, 37, 167, 26);
		contentPane.add(InvoiceNotextField);
		InvoiceNotextField.setColumns(10);
		
		JLabel lblDealercompanyName = new JLabel("Dealer/Company Name:");
		lblDealercompanyName.setForeground(new Color(255, 255, 255));
		lblDealercompanyName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblDealercompanyName.setBounds(38, 113, 285, 26);
		contentPane.add(lblDealercompanyName);
		
		DealertNamextField = new JTextField();
		DealertNamextField.setForeground(new Color(0, 0, 128));
		DealertNamextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		DealertNamextField.setColumns(10);
		DealertNamextField.setBounds(315, 115, 512, 26);
		contentPane.add(DealertNamextField);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblDate.setBounds(37, 75, 68, 26);
		contentPane.add(lblDate);
		
		DatetextField = new JTextField();
		DatetextField.setForeground(new Color(0, 0, 128));
		DatetextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		DatetextField.setColumns(10);
		DatetextField.setBounds(315, 75, 167, 26);
		contentPane.add(DatetextField);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblCategory.setBounds(37, 153, 115, 26);
		contentPane.add(lblCategory);
		
		JComboBox CategorycomboBox = new JComboBox();
		CategorycomboBox.setForeground(new Color(0, 0, 128));
		CategorycomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CategorycomboBox.setBounds(317, 155, 165, 26);
		contentPane.add(CategorycomboBox);
		contentPane.add(CategorycomboBox);
		CategorycomboBox.addItem("Paid Invoice");
		CategorycomboBox.addItem("Pending Invoice");
		contentPane.add(CategorycomboBox);
		
		JLabel lblPaidAmmount = new JLabel("Paid Ammount:");
		lblPaidAmmount.setForeground(new Color(255, 255, 255));
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblPaidAmmount.setBounds(37, 231, 175, 26);
		contentPane.add(lblPaidAmmount);
		
		PaidAmmounttextField = new JTextField();
		PaidAmmounttextField.setForeground(new Color(0, 0, 128));
		PaidAmmounttextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		PaidAmmounttextField.setColumns(10);
		PaidAmmounttextField.setBounds(317, 231, 165, 26);
		contentPane.add(PaidAmmounttextField);
		
		JLabel lblNewLabel_1 = new JLabel("Rs");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(494, 196, 32, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPendingAmmount = new JLabel("Pending Ammount:");
		lblPendingAmmount.setForeground(new Color(255, 255, 255));
		lblPendingAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblPendingAmmount.setBounds(37, 270, 226, 26);
		contentPane.add(lblPendingAmmount);
		
		PendingAmmounttextField = new JTextField();
		PendingAmmounttextField.setForeground(new Color(0, 0, 128));
		PendingAmmounttextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		PendingAmmounttextField.setColumns(10);
		PendingAmmounttextField.setBounds(317, 270, 165, 26);
		contentPane.add(PendingAmmounttextField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Rs");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(494, 235, 32, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
				String sql1="insert into DealerBillsInfo values(?,?,?,?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql1);
				ps.setString(1,InvoiceNotextField.getText());
				ps.setString(2,DatetextField.getText());
				ps.setString(3,DealertNamextField.getText());
				String Category=(String) CategorycomboBox.getSelectedItem();
				System.out.println(Category);
				ps.setString(4,Category);
				ps.setString(5,TotalAmmounttextField.getText());
				ps.setString(6,PaidAmmounttextField.getText());
				ps.setString(7,PendingAmmounttextField.getText());
				ps.executeUpdate();
				InvoiceNotextField.setText("");
				DatetextField.setText("");
				DealertNamextField.setText("");
				TotalAmmounttextField.setText("");
				PaidAmmounttextField.setText("");
				PendingAmmounttextField.setText("");
				
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
		btnSave.setBounds(336,  322, 122, 35);
		contentPane.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 384, 1240, 345);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				InvoiceNotextField.setText(model.getValueAt(i,0).toString());
				DatetextField.setText(model.getValueAt(i,1).toString());
				DealertNamextField.setText(model.getValueAt(i,2).toString());
				CategorycomboBox.setSelectedItem(model.getValueAt(i,3).toString());
				TotalAmmounttextField.setText(model.getValueAt(i,4).toString());
				PaidAmmounttextField.setText(model.getValueAt(i,5).toString());
				PendingAmmounttextField.setText(model.getValueAt(i,6).toString());
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
		btnBack.setBounds(855, 322, 122, 35);
		contentPane.add(btnBack);
		
		JLabel lblTotalAmmount = new JLabel("Total Ammount:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblTotalAmmount.setBounds(37, 192, 190, 26);
		contentPane.add(lblTotalAmmount);
		
		TotalAmmounttextField = new JTextField();
		TotalAmmounttextField.setForeground(new Color(0, 0, 128));
		TotalAmmounttextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		TotalAmmounttextField.setColumns(10);
		TotalAmmounttextField.setBounds(317, 192, 165, 26);
		contentPane.add(TotalAmmounttextField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Rs");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(494, 270, 32, 26);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
				String sql2="DELETE FROM DealerBillsInfo WHERE Invoice_No=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql2);
				ps.setString(1,InvoiceNotextField.getText());
				ps.executeUpdate();
				InvoiceNotextField.setText("");
				DatetextField.setText("");
				DealertNamextField.setText("");
				TotalAmmounttextField.setText("");
				PaidAmmounttextField.setText("");
				PendingAmmounttextField.setText("");
				
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnDelete.setBounds(674, 322, 122, 35);
		contentPane.add(btnDelete);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
				String sql3="UPDATE DealerBillsInfo SET Date=?,Dealer_Company_Name=?,Category=?,Total_Ammount=?,Paid_Ammount=?,Pending_Ammount=? WHERE Invoice_No=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql3);
				ps.setString(7,InvoiceNotextField.getText());
				ps.setString(1,DatetextField.getText());
				ps.setString(2,DealertNamextField.getText());
				String s1=(String) CategorycomboBox.getSelectedItem();
				ps.setString(3,s1);
				ps.setString(4,TotalAmmounttextField.getText());
				ps.setString(5,PaidAmmounttextField.getText());
				ps.setString(6,PendingAmmounttextField.getText());
				ps.executeUpdate();
				InvoiceNotextField.setText("");
				DatetextField.setText("");
				DealertNamextField.setText("");
				TotalAmmounttextField.setText("");
				PaidAmmounttextField.setText("");
				PendingAmmounttextField.setText("");
				
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnEdit.setForeground(new Color(0, 0, 128));
		btnEdit.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnEdit.setBounds(514, 322, 122, 35);
		contentPane.add(btnEdit);
		
		JLabel lblNewLabel_2 = new JLabel("Search Invoice No:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(608, 35, 219, 26);
		contentPane.add(lblNewLabel_2);
		
		InvoiceSearchtextField = new JTextField();
		InvoiceSearchtextField.setForeground(new Color(0, 0, 128));
		InvoiceSearchtextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		InvoiceSearchtextField.setColumns(10);
		InvoiceSearchtextField.setBounds(875, 37, 226, 26);
		contentPane.add(InvoiceSearchtextField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					String sql5="select *from DealerBillsInfo where Invoice_No=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,InvoiceSearchtextField.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String Ino=rs.getString("Invoice_No");
						InvoiceNotextField.setText(Ino);
						String Date=rs.getString("Date");
						DatetextField.setText(Date);
						String Cname=rs.getString("Dealer_Company_Name");
						DealertNamextField.setText(Cname);
						String Category=rs.getString("Category");
						CategorycomboBox.setSelectedItem(Category);
						String Total_Ammount=rs.getString("Total_Ammount");
						TotalAmmounttextField.setText(Total_Ammount);
						String Paid_Ammount=rs.getString("Paid_Ammount");
						PaidAmmounttextField.setText(Paid_Ammount);
						String Pending_Ammount=rs.getString("Pending_Ammount");
						PendingAmmounttextField.setText(Pending_Ammount);
					}
				} 
				catch(Exception e)
				{
					
				}
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnSearch.setBounds(1155, 31, 122, 35);
		contentPane.add(btnSearch);
		
		
		ShowData();
	
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(AddDealer.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(-16,0,1366, 768);
		contentPane.add(wallpaperLabel);
			
	}
}
