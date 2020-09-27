import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Mundhe Electronics");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/image/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1,1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1902, 32);
		menuBar.setForeground(new Color(0, 0, 0));
		contentPane.add(menuBar);
		
		JMenu BillMenu = new JMenu("Bill");
		BillMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		BillMenu.setBackground(new Color(220, 220, 220));
		BillMenu.setHorizontalAlignment(SwingConstants.CENTER);
		BillMenu.setForeground(new Color(0, 0, 128));
		menuBar.add(BillMenu);
		
		JMenuItem CreateBillMenuItem = new JMenuItem("Create Bill");
		CreateBillMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String cmd=e.getActionCommand();
				if(cmd.equals("Create Bill"))
				{
					CreateBills c1=new CreateBills();
					c1.setVisible(true);
					
				}
			}
		});
		CreateBillMenuItem.setForeground(new Color(0, 0, 128));
		CreateBillMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		BillMenu.add(CreateBillMenuItem);
		
		JMenuItem SearchBill = new JMenuItem("SearchBill");
		SearchBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String cmd=e.getActionCommand();
					if(cmd.equals("SearchBill"))
					{
						SearchBill b1=new SearchBill();
						b1.setVisible(true);
						
					}
				}
			});
		SearchBill.setForeground(new Color(0, 0, 139));
		SearchBill.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		BillMenu.add(SearchBill);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("DeleteBill");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();
				if(cmd.equals("DeleteBill"))
				{
					DeleteBill d1=new DeleteBill();
					d1.setVisible(true);
					
				}
			}
		});
		mntmNewMenuItem_4.setForeground(new Color(0, 0, 139));
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		BillMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Income");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();
				if(cmd.equals("Income"))
				{
					Income e1=new Income();
					e1.setVisible(true);
					
				}
				
			}
		});
		mntmNewMenuItem_3.setForeground(new Color(0, 0, 139));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		BillMenu.add(mntmNewMenuItem_3);
		
		
		
		JMenu ProductMenu = new JMenu("Stock");
		ProductMenu.setForeground(new Color(0, 0, 128));
		ProductMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(ProductMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Stock");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				AddStock frame = new AddStock();
				frame.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setForeground(new Color(0, 0, 128));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ProductMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Stock List");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				StockList frame = new StockList();
				frame.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setForeground(new Color(0, 0, 128));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ProductMenu.add(mntmNewMenuItem_2);
		
		
		JMenu mnNewMenu = new JMenu("Dealer");
		mnNewMenu.setForeground(new Color(0, 0, 128));
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem AddDealerMenuItem = new JMenuItem("Add Dealer");
		AddDealerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new AddDealer().setVisible(true);
			}
		});
		AddDealerMenuItem.setForeground(new Color(0, 0, 128));
		AddDealerMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnNewMenu.add(AddDealerMenuItem);
		
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_1.setForeground(new Color(0, 0, 128));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Complaints");
		mnNewMenu_2.setForeground(new Color(0, 0, 128));
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddComplaint1().setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmNewMenuItem.setForeground(new Color(0, 0, 128));
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setBounds(1, 1, 1366, 728);
		wallpaperLabel.setIcon(new ImageIcon(Home.class.getResource("/image/background12.jpg")));
		contentPane.add(wallpaperLabel);
		
	}
}
