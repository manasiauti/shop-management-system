import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class ResetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField NewPasstextField;
    Connection con;
    private JTextField UserIdtextField;
    private JLabel lblUserId;
    PreparedStatement ps;
    private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPassword frame = new ResetPassword();
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
	public ResetPassword() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResetPassword.class.getResource("/image/passowrd icon.jpg")));
		setTitle("Reset Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(625, 400, 673, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter New Password:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblNewLabel.setBounds(25, 80, 244, 33);
		contentPane.add(lblNewLabel);
		
		NewPasstextField = new JTextField();
		NewPasstextField.setForeground(new Color(0, 0, 128));
		NewPasstextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		NewPasstextField.setBounds(279, 80, 311, 33);
		contentPane.add(NewPasstextField);
		NewPasstextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
				String sql3="UPDATE Login SET Pass=? WHERE uname=?";	
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
				ps=con.prepareStatement(sql3);
				ps.setString(2,UserIdtextField.getText());
				ps.setString(1,NewPasstextField.getText());
				ps.executeUpdate();
				Login frame = new Login();
				frame.setVisible(true);
				}
				catch(Exception e1) {}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 22));
		btnNewButton.setBounds(193, 126, 110, 33);
		contentPane.add(btnNewButton);
		
		UserIdtextField = new JTextField();
		UserIdtextField.setForeground(new Color(0, 0, 128));
		UserIdtextField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		UserIdtextField.setColumns(10);
		UserIdtextField.setBounds(279, 34, 311, 33);
		contentPane.add(UserIdtextField);
		
		lblUserId = new JLabel("User Id:");
		lblUserId.setForeground(new Color(0, 0, 128));
		lblUserId.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
		lblUserId.setBounds(47, 34, 220, 33);
		contentPane.add(lblUserId);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 22));
		btnNewButton_1.setBounds(331, 126,  110, 33);
		contentPane.add(btnNewButton_1);
	}
}
