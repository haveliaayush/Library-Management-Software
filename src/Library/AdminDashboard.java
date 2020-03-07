package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AdminDashboard extends JFrame implements ActionListener{
	private JMenuItem lAdd;
	private JMenuItem lDelete;
	private JMenuItem tAdd;
	private JMenuItem tDelete;
	private JMenuItem sAdd;
	private JMenuItem sDelete;
	private JMenuItem sAll;
	private JMenuItem tAll;
	private JMenuItem lAll;
	private JMenuItem logout;

	public AdminDashboard() {
		setTitle("Admin Dashboard");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLibrarian = new JMenu("Librarian");
		menuBar.add(mnLibrarian);
		
		lAdd = new JMenuItem("Add");
		mnLibrarian.add(lAdd);
		
		lAll = new JMenuItem("View All");
		mnLibrarian.add(lAll);
		lAll.addActionListener(this);
		
		lDelete = new JMenuItem("Delete");
		mnLibrarian.add(lDelete);
		
		JMenu mnTeacher = new JMenu("Teacher");
		menuBar.add(mnTeacher);
		
		tAdd = new JMenuItem("Add");
		mnTeacher.add(tAdd);
		
		tAll = new JMenuItem("View All");
		mnTeacher.add(tAll);
		tAll.addActionListener(this);
		
		tDelete = new JMenuItem("Delete");
		mnTeacher.add(tDelete);
		
		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);
		
		sAdd = new JMenuItem("Add");
		mnStudent.add(sAdd);
		
		sAll = new JMenuItem("View All");
		mnStudent.add(sAll);
		sAll.addActionListener(this);
		
		sDelete = new JMenuItem("Delete");
		mnStudent.add(sDelete);
		
		logout = new JMenuItem("Logout");
		menuBar.add(logout);
		
		lAdd.addActionListener(this);
		lDelete.addActionListener(this);
		tAdd.addActionListener(this);
		tDelete.addActionListener(this);
		sAdd.addActionListener(this);
		sDelete.addActionListener(this);
		logout.addActionListener(this);
		
		getContentPane().setLayout(null);
		setSize(getMaximumSize());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == lAdd){
			new AddLibrarian();
		}
		else if (ob == lAll){
			new ViewAllLibrarian();
		}
		else if (ob == lDelete){
			new DeleteLibrarian();
		}
		else if (ob == tAdd){
			new AddTeacher();
		}
		else if (ob == tAll){
			new ViewAllTeacher();
		}
		else if (ob == tDelete){
			new DeleteTeacher();
		}
		else if (ob == sAdd){
			new AddStudent();
		}
		else if (ob == sAll){
			new ViewAllStudent();
		}
		else if (ob == sDelete){
			new DeleteStudent();
		}
		else if(ob == logout){
			dispose();
			new Login();
		}
	}
}
