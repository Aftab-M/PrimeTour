import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.JPanel;
//import javax.swing.border.*;
public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setUndecorated(false);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		setLocation(20, 20);
		Content = new JPanel();
		Content.setBackground(Color.WHITE);
		Content.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(Content);
		Content.setLayout(null);
		
		JLabel lblHeading = new JLabel("Tourism Management System");
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setBackground(new Color(255, 255, 255));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 37));
		lblHeading.setBounds(67, 22, 876, 65);
		Content.add(lblHeading);
//		ImageIcon icon=new ImageIcon(this.getClass().getResource("/user.jpg"));
//		ImageIcon icon2=new ImageIcon(this.getClass().getResource("/product.jpg"));
//		ImageIcon icon5=new ImageIcon(this.getClass().getResource("/sales.jpg"));
//		ImageIcon icon6=new ImageIcon(this.getClass().getResource("/category.png"));
//		ImageIcon icon7=new ImageIcon(this.getClass().getResource("/supplier.jpg"));
		
		JButton btnUser = new JButton("Destinations");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Vendor v=new Vendor();
//				dispose();
				//v.show();
			}
		});
		btnUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUser.setBounds(137, 397, 175, 50);
		Content.add(btnUser);
		
		JButton btnPrdt = new JButton("Guide");
		btnPrdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Product prdt=new Product();
//				dispose();
//				prdt.show();
			}
		});
		btnPrdt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnPrdt.setBounds(678, 397, 161, 50);
		Content.add(btnPrdt);
	}

}
