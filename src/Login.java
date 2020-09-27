import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.mysql.cj.xdevapi.Statement;

import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField Idtxtfield;
	private JPasswordField passwordField;
	protected Component frame;
    private Connection con;
    private java.sql.Statement stmt;
    private ResultSet rs;
    PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	public Login()
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		setTitle("Mundhe Electronics");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1,1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel login_label = new JLabel("Login");
		login_label.setBounds(610, 240, 140, 59);
		login_label.setForeground(new Color(255, 255, 255));
		login_label.setFont(new Font("Baskerville Old Face", Font.PLAIN, 55));
		contentPane.add(login_label);
		
		JLabel user_id_label = new JLabel("User ID:");
		user_id_label.setBounds(419, 375, 155, 31);
		user_id_label.setForeground(new Color(255, 255, 255));
		user_id_label.setFont(new Font("Baskerville Old Face", Font.PLAIN, 40));
		contentPane.add(user_id_label);
		
		JLabel password_label = new JLabel("Password:");
		password_label.setBounds(380, 448, 194, 31);
		password_label.setForeground(new Color(255, 255, 255));
		password_label.setFont(new Font("Baskerville Old Face", Font.PLAIN, 40));
		contentPane.add(password_label);
		
		JLabel password_icon = new JLabel("");
		password_icon.setBounds(621, 440, 50, 39);
		password_icon.setIcon(new ImageIcon(Login.class.getResource("/image/passowrd icon.jpg")));
		contentPane.add(password_icon);
		
		JLabel user_id_icon = new JLabel("");
		user_id_icon.setBounds(621, 354, 50, 52);
		user_id_icon.setIcon(new ImageIcon(Login.class.getResource("/image/user id icon.jpg")));
		contentPane.add(user_id_icon);
		
		Idtxtfield = new JTextField();
		Idtxtfield.setForeground(new Color(0, 0, 128));
		Idtxtfield.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		Idtxtfield.setBounds(690, 365, 289, 33);
		contentPane.add(Idtxtfield);
		Idtxtfield.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(690, 446, 289, 33);
		contentPane.add(passwordField);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			

			public  void actionPerformed(ActionEvent arg0) 
			{	try {
                String d1, d2;
                d1 = Idtxtfield.getText();
                d2 = new String(passwordField.getText());
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MundheElectronics","root","Manasi@1103");
                stmt =con.createStatement();
                rs = ((java.sql.Statement) stmt).executeQuery("select * from Login");
                while (rs.next()) {
                    String uname = rs.getString(1);
                    String pass = rs.getString(2);
                    if (uname.equals(d1) && pass.equals(d2))
                    {
                       new Home().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Id and Password!");
                        Idtxtfield.setText("");
                        passwordField.setText("");
                    }
                }
               


            } catch (Exception ie) {
                ie.printStackTrace();
            }
		}
	});

                        
                   
	
		login_button.setBounds(573, 527, 129, 39);

		login_button.setForeground(new Color(0, 0, 128));
		login_button.setFont(new Font("Baskerville Old Face", Font.PLAIN, 35));
		contentPane.add(login_button);
		
		
		
		JLabel login_icon = new JLabel("");
		login_icon.setBounds(582, 29, 200, 200);
		login_icon.setIcon(new ImageIcon(Login.class.getResource("/image/login icon.png")));
		contentPane.add(login_icon);
		
		JButton cancel_button_1 = new JButton("Cancel");
		cancel_button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				MundheElectronicsName window = new MundheElectronicsName();
				window.frame.setVisible(true);  
			}
		});
		cancel_button_1.setBounds(770, 524, 146, 42);
		cancel_button_1.setForeground(new Color(0, 0, 128));
		cancel_button_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 35));
		contentPane.add(cancel_button_1);
		
		JButton reset_button = new JButton("Reset Password");
		reset_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ResetPassword frame = new ResetPassword();
				frame.setVisible(true);
			}
		});
		reset_button.setBounds(610, 600, 295, 39);
		reset_button.setForeground(new Color(0, 0, 128));
		reset_button.setFont(new Font("Baskerville Old Face", Font.PLAIN, 35));
		contentPane.add(reset_button);
		
		JLabel wallpaperLabel = new JLabel("");
		wallpaperLabel.setIcon(new ImageIcon(Login.class.getResource("/image/background12.jpg")));
		wallpaperLabel.setBounds(1,1,1366, 768);
		contentPane.add(wallpaperLabel);
		
		
		
	}
}
