import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;
//import UserPage.java
public class Sampple extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
//					MainPage mp = new MainPage();
//					mp.show();
					
					LoginPage lp = new LoginPage();
					lp.show();
					
//					Sampple frame = new Sampple();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sampple() {
		setForeground(Color.BLACK);
		setTitle("Tourism System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 439);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logBtn = new JButton("LOGIN");
		logBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logBtn.setBounds(162, 245, 114, 37);
		contentPane.add(logBtn);
		
		logBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectMySQL cms = new ConnectMySQL();
				dispose();
				UserPage u = new UserPage("Aftab", 101);
				u.show();
				
			}
		});
		
		
		
		
//		ExploreTab et = new ExploreTab();
//		et.show();
		
		
		JButton regBtn = new JButton("REGISTER");
		regBtn.setBounds(433, 245, 114, 37);
		contentPane.add(regBtn);
		
		regBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ConnectMySQL cms = new ConnectMySQL();
//				dispose();
//				UserPage u = new UserPage("Aftab", 101);
//				u.show();
				
				LoginPage lp = new LoginPage();
				lp.show();
				
//				MainPage mp = new MainPage();
//				mp.show();
				
			}
		});
		
		
		
		JLabel titleLabel = new JLabel("TOURISM SYSTEM");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(283, 130, 135, 74);
		contentPane.add(titleLabel);
	}
}
