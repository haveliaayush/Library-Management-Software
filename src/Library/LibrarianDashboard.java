package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LibrarianDashboard extends JFrame implements ActionListener{
	private JMenuItem bAdd;
	private JMenuItem bAll;
	private JMenuItem logout;
	private JMenuItem bIssue;
	private JMenuItem bReturn;

	public LibrarianDashboard() {
		setTitle("Librarian Dashboard");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLibrarian = new JMenu("Books");
		menuBar.add(mnLibrarian);
		
		bAdd = new JMenuItem("Add");
		mnLibrarian.add(bAdd);
		
		bAll = new JMenuItem("View All");
		mnLibrarian.add(bAll);
		bAll.addActionListener(this);
		
		bIssue = new JMenuItem("Issue");
		mnLibrarian.add(bIssue);
		
		bReturn = new JMenuItem("Return");
		mnLibrarian.add(bReturn);
		
		logout = new JMenuItem("Logout");
		menuBar.add(logout);
		
		bAdd.addActionListener(this);
		bAll.addActionListener(this);
		bIssue.addActionListener(this);
		bReturn.addActionListener(this);
		logout.addActionListener(this);
		
		getContentPane().setLayout(null);
		setSize(getMaximumSize());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == bAdd){
			new AddBook();
		}
		else if (ob == bAll){
			new ViewBook();
		}
		else if (ob == bIssue){
			new Issue();
		}
		else if (ob == bReturn){
			new Return();
		}
		else if(ob == logout){
		dispose();
		new Login();
		}
	}
	
}
