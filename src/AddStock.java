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

public class AddStock extends JFrame {

	private JPanel contentPane;
	private JTextField ProductNamextField;
	private JTextField QuantitytextField;
	private JTable table;
	private JTextField UnitPricetextField;
    private JComboBox CategorycomboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStock frame = new AddStock();
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
   private JTextField ProductSearchtextField;
   
   
	public void ShowData()
	{
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
			String sql="select *from Stock";
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
	public AddStock() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddStock.class.getResource("/image/logo.jpg")));
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AddDealer.class.getResource("/Images/logo.jpg")));
		setTitle("Add Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ProductNamelbl = new JLabel("Product Name:");
		ProductNamelbl.setForeground(new Color(255, 255, 255));
		ProductNamelbl.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		ProductNamelbl.setBounds(37, 36, 254, 26);
		contentPane.add(ProductNamelbl);
		
		ProductNamextField = new JTextField();
		ProductNamextField.setForeground(new Color(0, 0, 128));
		ProductNamextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		ProductNamextField.setColumns(10);
		ProductNamextField.setBounds(315, 37, 447, 26);
		contentPane.add(ProductNamextField);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblCategory.setBounds(37, 75, 122, 26);
		contentPane.add(lblCategory);
		
		JComboBox CategorycomboBox = new JComboBox();
		CategorycomboBox.setForeground(new Color(0, 0, 128));
		CategorycomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		CategorycomboBox.setBounds(317, 77, 165, 26);
		contentPane.add(CategorycomboBox);
		contentPane.add(CategorycomboBox);
		CategorycomboBox.addItem("Electronics");
		CategorycomboBox.addItem("Electricals");
		contentPane.add(CategorycomboBox);
		
		JLabel lblPaidAmmount = new JLabel("Quantity:");
		lblPaidAmmount.setForeground(new Color(255, 255, 255));
		lblPaidAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblPaidAmmount.setBounds(37, 153, 175, 26);
		contentPane.add(lblPaidAmmount);
		
		QuantitytextField = new JTextField();
		QuantitytextField.setForeground(new Color(0, 0, 128));
		QuantitytextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		QuantitytextField.setColumns(10);
		QuantitytextField.setBounds(317, 153, 165, 26);
		contentPane.add(QuantitytextField);
		
		JLabel lblNewLabel_1 = new JLabel("Rs");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(494, 118, 32, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Units");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(494, 157, 88, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnSave = new JButton("Add");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{

				String sql1="insert into Stock values(?,?,?,?)";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql1);
				ps.setString(1,ProductNamextField.getText());
				String Category=(String) CategorycomboBox.getSelectedItem();
				System.out.println(Category);
				ps.setString(2,Category);
				ps.setString(3,UnitPricetextField.getText());
				ps.setString(4,QuantitytextField.getText());
				ps.executeUpdate();
				ProductNamextField.setText("");
				UnitPricetextField.setText("");
				QuantitytextField.setText("");
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
		btnSave.setBounds(315,  192, 122, 35);
		contentPane.add(btnSave);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 252, 1240, 477);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				ProductNamextField.setText(model.getValueAt(i,0).toString());
				CategorycomboBox.setSelectedItem(model.getValueAt(i,1).toString());
				UnitPricetextField.setText(model.getValueAt(i,2).toString());
				QuantitytextField.setText(model.getValueAt(i,3).toString());
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
		btnBack.setBounds(757, 192, 122, 35);
		contentPane.add(btnBack);
		
		JLabel lblTotalAmmount = new JLabel("Unit Price:");
		lblTotalAmmount.setForeground(Color.WHITE);
		lblTotalAmmount.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblTotalAmmount.setBounds(37, 114, 175, 26);
		contentPane.add(lblTotalAmmount);
		
		UnitPricetextField = new JTextField();
		UnitPricetextField.setForeground(new Color(0, 0, 128));
		UnitPricetextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		UnitPricetextField.setColumns(10);
		UnitPricetextField.setBounds(317, 114, 165, 26);
		contentPane.add(UnitPricetextField);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
				String sql2="DELETE FROM Stock WHERE Product_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql2);
				ps.setString(1,ProductNamextField.getText());
				ps.executeUpdate();
				ProductNamextField.setText("");
				UnitPricetextField.setText("");
				QuantitytextField.setText("");
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnDelete.setForeground(new Color(0, 0, 128));
		btnDelete.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnDelete.setBounds(608, 192, 122, 35);
		contentPane.add(btnDelete);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
				String sql3="UPDATE Stock SET Category=?,Unit_Price=?,Quantity=? WHERE Product_Name=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql3);
				ps.setString(4,ProductNamextField.getText());
				String s1=(String) CategorycomboBox.getSelectedItem();
				ps.setString(1,s1);
				ps.setString(2,UnitPricetextField.getText());
				ps.setString(3,QuantitytextField.getText());
				ps.executeUpdate();
				ProductNamextField.setText("");
				UnitPricetextField.setText("");
				QuantitytextField.setText("");
				}
				catch(Exception e1) {}
				ShowData();
			}
		});
		btnEdit.setForeground(new Color(0, 0, 128));
		btnEdit.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnEdit.setBounds(460, 192, 122, 35);
		contentPane.add(btnEdit);
		
		JLabel lblNewLabel_2 = new JLabel("Product Name:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(795, 36, 181, 26);
		contentPane.add(lblNewLabel_2);
		
		ProductSearchtextField = new JTextField();
		ProductSearchtextField.setForeground(new Color(0, 0, 128));
		ProductSearchtextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		ProductSearchtextField.setColumns(10);
		ProductSearchtextField.setBounds(986, 38, 217, 26);
		contentPane.add(ProductSearchtextField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					String sql5="select *from Stock where Product_Name=?";
					ps=con.prepareStatement(sql5);
					ps.setString(1,ProductSearchtextField.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						String Product_Name=rs.getString("Product_Name");
						ProductNamextField.setText(Product_Name);
						String Category=rs.getString("Category");
						CategorycomboBox.setSelectedItem(Category);
						String Unit_Price=rs.getString("Unit_Price");
						UnitPricetextField.setText(Unit_Price);
						String Quantity=rs.getString("Quantity");
						QuantitytextField.setText(Quantity);
					}
				} 
				catch(Exception e)
				{
					
				}
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		btnSearch.setBounds(1218, 32, 122, 35);
		contentPane.add(btnSearch);
		
		
		ShowData();
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(AddStock.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1366, 768);
		contentPane.add(wallpaperLabel);
		
			
	}
}
