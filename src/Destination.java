import java.awt.EventQueue;

import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

//import JDBC.MyConnection;

import javax.swing.*;
import javax.swing.JPanel;

public class Destination extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName,txtDesc,txtCode,txtgst,txtprice,txttotal,txtOrder;
	private JTable table;
	private JTextField txtId;
	Connection dbcon=null;
	PreparedStatement pstate=null;
	Statement state=null;
	int i;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Destination frame = new Destination();
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
	public Destination() {
		setUndecorated(false);
		//launch frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350,150,1000,700);
		setLocation(10, 10);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel("DESTINATION");
		lblHeading.setBounds(305, 29, 348, 70);
		lblHeading.setForeground(new Color(32, 178, 170));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Times New Roman", Font.BOLD, 45));
		contentPane.add(lblHeading);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBounds(24, 119, 391, 296);
		pnlForm.setBackground(new Color(32, 178, 170));
		contentPane.add(pnlForm);
		pnlForm.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(10, 107, 129, 32);
		pnlForm.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtName.setBounds(132, 111, 216,32);
		pnlForm.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblCat = new JLabel("Category");
		lblCat.setForeground(new Color(255, 255, 255));
		lblCat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCat.setBounds(10, 20, 109, 32);
		pnlForm.add(lblCat);
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setBounds(132, 23, 213, 30);
		pnlForm.add(comboBox);
		
		GetCon con=new GetCon();
		dbcon=con.getConn();
		try {
			pstate=dbcon.prepareStatement("select * from cat");
			ResultSet rs=pstate.executeQuery();
			comboBox.removeAllItems();
			comboBox.addItem("Select");
			while(rs.next()) {
				comboBox.addItem(rs.getString(1));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Exception.","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}
		JLabel lblCp = new JLabel("Price");
		lblCp.setForeground(new Color(255, 255, 255));
		lblCp.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCp.setBounds(10, 149, 98, 24);
		pnlForm.add(lblCp);
		
		JLabel lblRetail = new JLabel("GST");
		lblRetail.setForeground(new Color(255, 255, 255));
		lblRetail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRetail.setBounds(10, 193, 98, 24);
		pnlForm.add(lblRetail);
		
		JLabel lblQty = new JLabel("Final Total");
		lblQty.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblQty.setForeground(new Color(255, 255, 255));
		lblQty.setBounds(10, 232, 98, 24);
		pnlForm.add(lblQty);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtprice.setBounds(132, 149, 213,32);
		pnlForm.add(txtprice);
		txtprice.setColumns(10);
		
		txtgst = new JTextField();
		txtgst.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtgst.setBounds(132, 191, 213,32);
		pnlForm.add(txtgst);
		txtgst.setColumns(10);
		
		txttotal = new JTextField();
		txttotal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txttotal.setBounds(132, 230, 213,32);
		pnlForm.add(txttotal);
		txttotal.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblId.setBounds(10, 69, 129, 32);
		pnlForm.add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtId.setColumns(10);
		txtId.setBounds(132, 71, 216, 32);
		pnlForm.add(txtId);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"Category is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtId.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtprice.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Cost must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txttotal.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"GST must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txtId.getText().matches("^[0-9]*$"))) {
			JOptionPane.showMessageDialog(null,"ID  must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					/*
					 create table destination(
					 	desCat varchar(30),
					 	desId number,
					 	desNme varchar(30),
					 	desPrice float,
					 	desGst float,
					 	desTotal float
					 );
					 */
				try {
						pstate=dbcon.prepareStatement("select * from destination where desCat=?");
						pstate.setString(1, txtId.getText());
						ResultSet rs=pstate.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null,"Destination with same code is alread exist.","Error",JOptionPane.ERROR_MESSAGE);
						}
						else {
							DefaultTableModel tObj=(DefaultTableModel)table.getModel();
							String ca=(String) comboBox.getSelectedItem();
							tObj.addRow(new Object[] {ca,txtId.getText(),txtName.getText(),txtprice.getText(),txtgst.getText(),txttotal.getText()});
							pstate=dbcon.prepareStatement("insert into destination values(?,?,?,?,?,?,?)");
							String cate=(String) comboBox.getSelectedItem();
							pstate.setString(1,cate );
							pstate.setString(2, txtId.getText());	
							pstate.setString(3, txtName.getText());
							pstate.setString(4, txtprice.getText());
							pstate.setString(5, txtgst.getText());
							pstate.setString(6, txttotal.getText());
							pstate.setInt(7, 0);
							i=pstate.executeUpdate();
							if(i>0) {
								JOptionPane.showMessageDialog(null,"Destination added successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Exception","Error",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex);
						ex.printStackTrace();
					}
				}
			}
		});
		btnAdd.setBounds(24, 600, 131, 39);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setEditable(false);
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"Category is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
//				else if(txtCode.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()||txtprice.getText().trim().isEmpty()||txtDesc.getText().trim().isEmpty()||txtOrder.getText().trim().isEmpty()||txttotal.getText().trim().isEmpty()||txtgst.getText().trim().isEmpty()) {
//					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
//				}
				else if(!(txtprice.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Cost must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txttotal.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Quantity must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					/*
					 create table destination(
					 	desCat varchar(30),
					 	desId int,
					 	desNme varchar(30),
					 	desPrice float,
					 	desGst float,
					 	desTotal float
					 );
					 */
					try {
						pstate=dbcon.prepareStatement("update destination set desCat=?,desId=?,desNme=?,desPrice=?,desGst=?,desTotal=? where desId=?");
						String ca=(String) comboBox.getSelectedItem();
						pstate.setString(1, ca);
						pstate.setString(2, txtId.getText());
						pstate.setString(3, txtName.getText());
						pstate.setString(4, txtprice.getText());
					pstate.setString(5, txtgst.getText());
						pstate.setString(6, txttotal.getText());
						pstate.setString(7, txtId.getText());				
						i=pstate.executeUpdate();
						if(i>0) {
							JOptionPane.showMessageDialog(null,"Destination updated successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							comboBox.setSelectedIndex(0);
							txtId.setText(" ");
							txtName.setText(" ");
							txtprice.setText(" ");
							txtgst.setText(" ");
							txttotal.setText(" ");
							int  c;
							try {
								GetCon m=new GetCon();
								dbcon=m.getConn();
								pstate=dbcon.prepareStatement("select * from destination");
								ResultSet rs=pstate.executeQuery();
								ResultSetMetaData rsd=rs.getMetaData();
								c=rsd.getColumnCount();
								DefaultTableModel d=(DefaultTableModel)table.getModel();
								d.setRowCount(0);
								while(rs.next()) {
									Vector v2=new Vector();
									for(int i=1;i<=c;i++) {
										v2.add(rs.getString(1));
										v2.add(rs.getString(2));
										v2.add(rs.getString(3));
										v2.add(rs.getString(4));
										v2.add(rs.getString(5));
										v2.add(rs.getString(6));
										
									}
									d.addRow(v2);
								}
							}catch(Exception exe) {
								JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
								//System.out.println(exe);
								exe.printStackTrace();
							}
							txtId.setEditable(true);
						}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Exception at update.","Error",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
			}
			}
		);
	
		btnEdit.setBounds(177, 600, 131, 39);
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnEdit);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cat=comboBox.getSelectedIndex();
				if(cat<=0) {
					JOptionPane.showMessageDialog(null,"Category is not selected.","Error",JOptionPane.ERROR_MESSAGE);
				}
//				else if(txtCode.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()||txtprice.getText().trim().isEmpty()||txtDesc.getText().trim().isEmpty()||txtOrder.getText().trim().isEmpty()||txttotal.getText().trim().isEmpty()||txtgst.getText().trim().isEmpty()) {
//					JOptionPane.showMessageDialog(null,"Fill all the details.","Error",JOptionPane.ERROR_MESSAGE);
//				}
				else if(!(txtprice.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Cost must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(txttotal.getText().matches("^[0-9]*$"))) {
					JOptionPane.showMessageDialog(null,"Quantity must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
				}
//				else if(!(txtCode.getText().matches("^[0-9]*$"))) {
//			JOptionPane.showMessageDialog(null,"Barcode must be numeric.","Error",JOptionPane.ERROR_MESSAGE);
//				}
				else {
				try {
						pstate=dbcon.prepareStatement("delete from destination where desId=?");
						pstate.setString(1, txtId.getText());
     					i=pstate.executeUpdate();
						if(i>0) {
						JOptionPane.showMessageDialog(null,"Destination deleted successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							comboBox.setSelectedIndex(0);
							txtId.setText(" ");
							txtName.setText(" ");
							txtprice.setText(" ");
							txtgst.setText(" ");
							txttotal.setText(" ");
							int  c;
							try {
								GetCon m=new GetCon();
								dbcon=m.getConn();
								pstate=dbcon.prepareStatement("select * from destination");
								ResultSet rs=pstate.executeQuery();
								ResultSetMetaData rsd=rs.getMetaData();
								c=rsd.getColumnCount();
								DefaultTableModel d=(DefaultTableModel)table.getModel();
								d.setRowCount(0);
								while(rs.next()) {
									Vector v2=new Vector();
									for(int i=1;i<=c;i++) {
										v2.add(rs.getString(1));
										v2.add(rs.getString(2));
										v2.add(rs.getString(3));
										v2.add(rs.getString(4));
										v2.add(rs.getString(5));
									v2.add(rs.getString(6));
									}
									d.addRow(v2);
							}
							}catch(Exception exe) {
								JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
							//System.out.println(exe);
								exe.printStackTrace();
						}
							txtId.setEditable(true);
						}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Exception at delete.","Error",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
					}
				}
			}
		});
		btnNewButton_2.setBounds(336, 600, 131, 39);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedIndex(0);
				txtId.setText(" ");
				txtName.setText(" ");
				txtprice.setText(" ");
				txtgst.setText(" ");
				txttotal.setText(" ");
			}
		});
		btnNewButton_3.setBounds(488, 600, 131, 39);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 112, 525, 466);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtId.setEditable(false);
				DefaultTableModel tObj=(DefaultTableModel)table.getModel();
				int selected=table.getSelectedRow();
				comboBox.setSelectedItem(tObj.getValueAt(selected, 0).toString());
				txtId.setText(tObj.getValueAt(selected, 1).toString());
				txtName.setText(tObj.getValueAt(selected, 2).toString());
				txtprice.setText(tObj.getValueAt(selected, 3).toString());
				txtgst.setText(tObj.getValueAt(selected, 4).toString());
				txttotal.setText(tObj.getValueAt(selected, 5).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Category", "ID ", "Name ", "Price", "GST", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		int  c;
		try {
			GetCon m=new GetCon();
			dbcon=m.getConn();
			pstate=dbcon.prepareStatement("select * from destination");
			ResultSet rs=pstate.executeQuery();
			ResultSetMetaData rsd=rs.getMetaData();
			c=rsd.getColumnCount();
			DefaultTableModel d=(DefaultTableModel)table.getModel();
			d.setRowCount(0);
			while(rs.next()) {
				Vector v2=new Vector();
				for(int i=1;i<=c;i++) {
					v2.add(rs.getString(1));
					v2.add(rs.getString(2));
					v2.add(rs.getString(3));
					v2.add(rs.getString(4));
					v2.add(rs.getString(5));
					v2.add(rs.getString(6));
					
				}
				d.addRow(v2);
			}
		}catch(Exception exe) {
			JOptionPane.showMessageDialog(null,"Exception at table.","Error",JOptionPane.ERROR_MESSAGE);
			System.out.println(exe);
			exe.printStackTrace();
		}
	
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage lp = new LoginPage();
				dispose();
				lp.show();
			}
		});
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Color.BLACK);
		btnBack.setBounds(10, 10, 62, 21);
		
		contentPane.add(btnBack);
	}
			
			}
		
