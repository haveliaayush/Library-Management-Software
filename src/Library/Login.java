package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;


public class Login extends JDialog implements ActionListener{
	private JButton admin;
	private JButton librarian;
	private JButton teacher;
	private JButton student;

	public Login() {
		setTitle("Login");
		getContentPane().setLayout(null);
		
		admin = new JButton("Admin Login");
		admin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		admin.setBounds(30, 26, 162, 61);
		getContentPane().add(admin);
		
		librarian = new JButton("Librarian Login");
		librarian.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		librarian.setBounds(235, 26, 162, 61);
		getContentPane().add(librarian);
		
		teacher = new JButton("Teacher Login");
		teacher.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		teacher.setBounds(30, 136, 162, 61);
		getContentPane().add(teacher);
		
		student = new JButton("Student Login");
		student.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		student.setBounds(235, 136, 162, 61);
		getContentPane().add(student);
		
		admin.addActionListener(this);
		librarian.addActionListener(this);
		teacher.addActionListener(this);
		student.addActionListener(this);
		
		setSize(450,260);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == admin){
			dispose();
			new AdminLogin();
		}
		else if(ob == student){
			dispose();
			new StudentLogin();
		}
		else if (ob == teacher){
			dispose();
			new TeacherLogin();
		}
		else if(ob == librarian){
			dispose();
			new LibrarianLogin();
		}
	}
	
	public static void main(String ar[]){
		new Login();
	}
}
