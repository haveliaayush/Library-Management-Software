package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class AddLibrarian extends JDialog implements ActionListener{
	private JTextField tname;
	private JTextField tage;
	private JTextField tmob;
	private JTextField tadd;
	private JTextField tid;
	private JButton cancel;
	private JButton submit;
	private JRadioButton m;
	private JRadioButton f;
	private JTextField tuname;
	public int lid = 1001;
	private JPasswordField tpass;

	public AddLibrarian() {
		setTitle("Librarian Registration");
		getContentPane().setLayout(null);
		
		JLabel label_3 = new JLabel("Name:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(23, 26, 92, 25);
		getContentPane().add(label_3);
		
		JLabel lblAge = new JLabel("Age:\r\n");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(23, 62, 92, 25);
		getContentPane().add(lblAge);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(23, 98, 92, 25);
		getContentPane().add(lblGender);
		
		JLabel lblMobileNo = new JLabel("Mobile No.:");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMobileNo.setBounds(23, 134, 92, 25);
		getContentPane().add(lblMobileNo);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(23, 206, 92, 25);
		getContentPane().add(lblAddress);
		
		tname = new JTextField();
		tname.setBounds(145, 26, 161, 25);
		getContentPane().add(tname);
		tname.setColumns(10);
		
		JLabel lblLibrarianId = new JLabel("Librarian ID:");
		lblLibrarianId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLibrarianId.setBounds(23, 275, 92, 25);
		getContentPane().add(lblLibrarianId);
		
		tage = new JTextField();
		tage.setColumns(10);
		tage.setBounds(145, 66, 161, 25);
		getContentPane().add(tage);
		
		tmob = new JTextField();
		tmob.setColumns(10);
		tmob.setBounds(145, 136, 161, 25);
		getContentPane().add(tmob);
		
		tadd = new JTextField();
		tadd.setColumns(10);
		tadd.setBounds(145, 174, 161, 92);
		getContentPane().add(tadd);
		
		tid = new JTextField();
		tid.setEditable(false);
		tid.setBackground(Color.WHITE);
		tid.setColumns(10);
		tid.setBounds(145, 277, 161, 25);
		getContentPane().add(tid);
		
		m = new JRadioButton("Male");
		m.setBounds(145, 101, 61, 23);
		getContentPane().add(m);
		
		f = new JRadioButton("Female");
		f.setBounds(237, 101, 69, 23);
		getContentPane().add(f);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(m);
		bg.add(f);
		
		submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.BOLD, 16));
		submit.setBounds(33, 434, 101, 29);
		getContentPane().add(submit);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		cancel.setBounds(205, 434, 101, 29);
		getContentPane().add(cancel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(23, 329, 92, 25);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(23, 366, 92, 25);
		getContentPane().add(lblPassword);
		
		tuname = new JTextField();
		tuname.setColumns(10);
		tuname.setBackground(Color.WHITE);
		tuname.setBounds(145, 331, 161, 25);
		getContentPane().add(tuname);
		
		tpass = new JPasswordField();
		tpass.setBounds(145, 366, 161, 25);
		getContentPane().add(tpass);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
		generateId();
		setSize(380,524);
		setModal(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == submit){
			if((tname.getText().length())!=0 && (tage.getText().length())!=0 && (m.isSelected() || f.isSelected()) && (tmob.getText().length())!=0 && (tadd.getText().length())!=0 && (tuname.getText().length())!=0 && (tpass.getText().length())!=0){
				String name = tname.getText();
				int age = Integer.parseInt(tage.getText());
				String gender;
				if(m.isSelected())
					gender = "Male";
				else
					gender = "Female";
				String mob = tmob.getText();
				String add = tadd.getText();
				String uname = tuname.getText();
				String pass = new String(tpass.getPassword());
				String s = "insert into librariandetails(id,name,uname,pass,age,gender,mobno,address) values(?,?,?,?,?,?,?,?)";
				Connection cc = MyConnection.connect();
				try{
					PreparedStatement ps = cc.prepareStatement(s);
					ps.setString(2,name);
					ps.setString(3,uname);
					ps.setString(4,pass);
					ps.setInt(5,age);
					ps.setString(6,gender);
					ps.setString(7,mob);
					ps.setString(8,add);
					ps.setInt(1,lid);
					ps.executeUpdate();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "Info added successfully");
			}
			else{
				JOptionPane.showMessageDialog(null, "Please enter all details");
			}
		}
		else if(ob == cancel){
			dispose();
		}
	}
	void generateId(){
		String s = "select max(id) from librariandetails";
		Connection c = MyConnection.connect();
		try{
			PreparedStatement ps = c.prepareStatement(s);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				lid = rs.getInt(1);
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		if(lid ==0){
			lid = 1001;
		} else{
			++lid;
		}
		tid.setText(String.valueOf(lid));
	}
}
