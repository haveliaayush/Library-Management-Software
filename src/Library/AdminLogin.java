package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AdminLogin extends JDialog implements ActionListener{
	private JTextField u;
	private JPasswordField p;
	private JButton login;
	private JButton cancel;

	public AdminLogin() {
		setTitle("Admin Login");
		
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(43, 31, 112, 40);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(43, 93, 112, 40);
		getContentPane().add(lblPassword);
		
		u = new JTextField();
		u.setBounds(185, 33, 145, 40);
		getContentPane().add(u);
		u.setColumns(10);
		
		p = new JPasswordField();
		p.setBounds(185, 92, 145, 41);
		getContentPane().add(p);
		
		login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, 17));
		login.setBounds(59, 174, 97, 46);
		getContentPane().add(login);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		cancel.setBounds(233, 174, 97, 46);
		getContentPane().add(cancel);
		
		login.addActionListener(this);
		cancel.addActionListener(this);
		
		login.setEnabled(false);
		
		u.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent k){
				if(u.getText().length()!=0)
					login.setEnabled(true);
				else
					login.setEnabled(false);
			}
		});
		setLocationRelativeTo(null);
		setSize(420,290);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == login){
			String user = u.getText();
			String pass = new String(p.getPassword());
			if(user.equals("admin") && pass.equals("admin")) {
				new AdminDashboard();
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid credentials");
		}
		else if (ob == cancel){
			dispose();
		}
		
	}
}
