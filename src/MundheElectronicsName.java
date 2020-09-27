import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MundheElectronicsName extends JFrame {

	protected static Object m;
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MundheElectronicsName window = new MundheElectronicsName();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MundheElectronicsName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Mundhe Electronics");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MundheElectronicsName.class.getResource("/image/logo.jpg")));
		frame.setBounds(1, 1, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton start_button = new JButton("Start");
		start_button.setBounds(1117, 666, 112, 36);

		start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String cmd=e.getActionCommand();
				if(cmd.equals("Start"))
						{
					Login l=new Login();
					l.setVisible(true);
					frame.dispose();
						}
			}
		});
		frame.getContentPane().setLayout(null);
		start_button.setForeground(new Color(0, 0, 128));
		start_button.setFont(new Font("Baskerville Old Face", Font.PLAIN, 35));
		frame.getContentPane().add(start_button);
		
		JLabel wallpaper1 = new JLabel("");
		wallpaper1.setIcon(new ImageIcon(MundheElectronicsName.class.getResource("/image/mundeelectronics.jpg")));
		wallpaper1.setBounds(0, 0, 1349, 728);
		frame.getContentPane().add(wallpaper1);
	}
}
