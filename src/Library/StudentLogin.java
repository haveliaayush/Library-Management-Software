package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class StudentLogin extends JDialog implements ActionListener{
	private JTextField id;
	private JPasswordField tpass;
	private JButton login;
	private JButton cancel;
	private JTextField tuname;

	public StudentLogin() {
		setTitle("Student Login");
		
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(43, 83, 112, 40);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(43, 134, 112, 40);
		getContentPane().add(lblPassword);
		
		id = new JTextField();
		id.setBounds(185, 33, 145, 40);
		getContentPane().add(id);
		id.setColumns(10);
		
		tpass = new JPasswordField();
		tpass.setBounds(185, 136, 145, 41);
		getContentPane().add(tpass);
		
		login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, 17));
		login.setBounds(59, 194, 97, 46);
		getContentPane().add(login);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		cancel.setBounds(233, 194, 97, 46);
		getContentPane().add(cancel);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStudentId.setBounds(43, 32, 112, 40);
		getContentPane().add(lblStudentId);
		
		tuname = new JTextField();
		tuname.setColumns(10);
		tuname.setBounds(185, 83, 145, 40);
		getContentPane().add(tuname);
		
		login.addActionListener(this);
		cancel.addActionListener(this);
		
		setLocationRelativeTo(null);
		setSize(420,290);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == login){
//			int sid = Integer.parseInt(id.getText());
//			String uname = tuname.getText();
//			String pass = new String(tpass.getPassword());
//			String s = "select uname,pass from studentdetails where id = ?";
//			Connection cc = MyConnection.connect();
//			try{
//				PreparedStatement ps = cc.prepareStatement(s);
//				ps.setInt(1,sid);
//				ResultSet rs = ps.executeQuery();
//				if(rs.next()){
//					dispose();
//				}				
//			}
//			catch(SQLException se){
//				se.printStackTrace();
//			}
			new StudentDashboard(1, "Student");
			dispose();
		}
		else if (ob == cancel){
			dispose();
		}
		
	}
}