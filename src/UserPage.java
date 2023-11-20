import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.ui.internal.dialogs.ContentTypesPreferencePage;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage("what up", 000);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	List<String> places = new ArrayList<String>();
	List<String> prices = new ArrayList<String>();
	List<String> past = new ArrayList<String>();
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public UserPage(String user, int id) {
		setTitle("Welcome, User !!");
		String uname = "Harry";
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbname = "tourismdb";
		String driver = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "";
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1303, 760);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new CompoundBorder());
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		
		
		
		try {
			
			
			conn = DriverManager.getConnection(url+dbname, username, password);
			System.out.println("Connected to the DB !!!!!");
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			
//			final ResultSet rs = st.executeQuery("select *from places;");
			final ResultSet rs = st.executeQuery("select *from destination;");
//			rs.first();
//			System.out.println(rs.getString("placename"));
//			places.add("Dove Something");
			int thats_enough = 0;
			while(rs.next() && thats_enough != 9) {
				thats_enough++;
				places.add(rs.getString("desNme"));
				prices.add(rs.getString("desPrice"));
			}	// while rs
			
			
			
			
		
			
			
		
			
			
			
			
			JLabel lblNewLabel = new JLabel("Welcome, "+user+" !");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblNewLabel.setBounds(22, 21, 460, 55);
			contentPane.add(lblNewLabel);
			
			JPanel gridPanel = new JPanel();
			gridPanel.setBackground(UIManager.getColor("InternalFrame.activeBorderColor"));
			gridPanel.setBounds(10, 154, 923, 538);
			contentPane.add(gridPanel);
			gridPanel.setLayout(new GridLayout(0, 3, 5, 5));
			
			JButton btnExplore = new JButton("Explore");
			btnExplore	.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnExplore.setBounds(1047, 34, 99, 40);
			contentPane.add(btnExplore);
			
			btnExplore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConnectMySQL cms = new ConnectMySQL();
//					dispose();
					ExploreTab et = new ExploreTab();
					et.show();
					
				}
			});
			
			
			JButton btnLogOut = new JButton("Log Out");
			btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnLogOut.setBounds(1156, 34, 108, 40);
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out ???");
					
					if(opt==2) { System.out.println("EXITING ?????"); }
					if(opt==1) { System.out.println("NONONONONO");  }
					if(opt==0) { System.out.println("YES");  dispose(); }
					
//					dispose();
				}	// actionPerformed()
			});
			contentPane.add(btnLogOut);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 153, 255));
			panel.setBounds(964, 154, 313, 538);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("YOUR PREVIOUS TRIPS");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(77, 0, 169, 51);
			panel.add(lblNewLabel_1);
			
			JPanel pastpanel = new JPanel();
			pastpanel.setBackground(new Color(0, 51, 102));
			pastpanel.setBounds(10, 55, 293, 472);
			panel.add(pastpanel);
			pastpanel.setLayout(new GridLayout(10, 1, 5, 5));
			
			JLabel lblNewLabel_2 = new JLabel("Best trips for you today...");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_2.setBounds(35, 100, 224, 43);
			contentPane.add(lblNewLabel_2);
			
			
//			final ResultSet prs = st.executeQuery("select *from usertrips where userid=?;");
			
			PreparedStatement ps = conn.prepareStatement("select *from usertrips where userid=?");
			ps.setInt(1,  id);
			
			final ResultSet prs = ps.executeQuery();
			
			while(prs.next()) {
				String pname = prs.getString("placename");
				String pprice = prs.getString("price");
				String date = prs.getDate("date").toString();
				
				OneTripBlock otb = new OneTripBlock(pname, pprice, date);
				pastpanel.add(otb);
				
			}	// while prs
			
			
			
			for(int i=0; i<places.size(); i++) {
				String s = places.get(i);
				String p = prices.get(i);
				OneTourGrid o = new OneTourGrid(id,s, p);
				gridPanel.add(o);
				
			}	// for - s
			

		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//																								***   END OF TRY   ***
			
			
		}	// try
		
		
		
		
		
		catch(Exception e) {
			System.out.println("YERRORORROROROROORROR");
			e.printStackTrace();
		}	// catch
		
		
		

		
		
		
		
		
	}
}










