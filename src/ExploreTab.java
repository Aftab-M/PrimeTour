import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class ExploreTab extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	String qDef = "select *from destination;";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExploreTab frame = new ExploreTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ExploreTab() {
		setTitle("Explore Tab ");
		Connection conn = null;
		final String url = "jdbc:mysql://localhost:3306/";
		final String dbname = "tourismdb";
		final String driver = "com.mysql.jdbc.Driver";
		final String username = "root";
		final String password = "";
		try {
			conn = DriverManager.getConnection(url+dbname, username, password);
			System.out.println("Connected to the DB !!!!!");
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 581);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("FormattedTextField.inactiveForeground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		final JPanel placeHere = new JPanel();
		placeHere.setBackground(UIManager.getColor("FormattedTextField.inactiveForeground"));
		placeHere.setBounds(10, 133, 719, 398);
		contentPane.add(placeHere);
		placeHere.setLayout(new GridLayout(10, 1, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ALL TRIPS ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 14, 121, 36);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(180, 12, 312, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("GO");
		btnSearch.setBounds(500, 11, 58, 42);
		contentPane.add(btnSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				placeHere.removeAll();
				Connection conn;
				try {
					String toSearch = textField.getText();
					placeHere.removeAll();
//					qDef = "select *from destination where desNme = 'England';";
					
					conn = DriverManager.getConnection(url+dbname, username, password);
					
					PreparedStatement ps = conn.prepareStatement("select *from destination where desNme=?;");
					
					ps.setString(1, toSearch);
					
					System.out.println(ps.toString());
					final ResultSet rs = ps.executeQuery();
					
					if(!rs.isBeforeFirst()) {
						placeHere.removeAll();
						OneListItem oli = new OneListItem("NO RESULTS", "NO RESULTS", "NO RESULTS");
						placeHere.add(oli);
					}
					
					else {
						placeHere.removeAll();
						while(rs.next()) {
							String pl = rs.getString("desNme");
							String pr = rs.getString("desPrice");
							String po = rs.getString("desPast");
							OneListItem oli = new OneListItem(pl, po, pr);
							placeHere.add(oli);
						}	// while - rs
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				placeHere.revalidate();
				
				
			}
		});
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sort by Price (asc)", "Sort by Price (desc)", "Sort by Popularity (asc)", "Sort by Popularity (desc)"}));
		comboBox.setBounds(581, 11, 148, 40);
		contentPane.add(comboBox);
		JPanel panel = new JPanel();
		panel.setBounds(10, 91, 719, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("PLACE NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(23, 11, 84, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("POPULARITY");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(625, 11, 84, 20);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PRICE");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(531, 11, 84, 20);
		panel.add(lblNewLabel_1_1_1);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexSelected = comboBox.getSelectedIndex();
				
				if(indexSelected == 0) { qDef = "select *from destination order by desPrice;"; }
				
				if(indexSelected == 1) { qDef = "select *from destination order by desPrice desc;"; }
				
				if(indexSelected == 2) { qDef = "select *from destination order by desPast;"; }

				if(indexSelected == 3) { qDef = "select *from destination order by desPast desc;"; }
				
				System.out.println(indexSelected + " has the query - " + qDef);
				
				Connection conn;
				
				try {
					
					placeHere.removeAll();
					
					conn = DriverManager.getConnection(url+dbname, username, password);
					
					Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					
					final ResultSet rs = st.executeQuery(qDef);
					
					while(rs.next()) {
						String pl = rs.getString("desNme");
						String pr = rs.getString("desPrice");
						String po = rs.getString("desPast");
						OneListItem oli = new OneListItem(pl, po, pr);
						placeHere.add(oli);
					}	// while - rs	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				placeHere.revalidate();
				
			}
		});
		}
		catch(Exception e) {
			System.out.println("EROROROROROROROROOROROR");
//			System.out.print(e.printStackTrace());
			e.printStackTrace();
		}
		
	}
}
