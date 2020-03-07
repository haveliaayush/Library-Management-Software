package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDashboard extends JFrame implements ActionListener {
	private JMenuItem bbId;
	private JMenuItem bName;
	private JMenuItem bAuthor;
	private JMenuItem bPublisher;
	private JMenuItem logout;
	private JMenuItem view;
	int iid;
	String catg;
	
	public TeacherDashboard(int iid, String catg) {
		this.iid = iid;
		this.catg = catg;
		
		setTitle("Teacher Dashboard");
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSearchBooks = new JMenu("Search Books");
		menuBar.add(mnSearchBooks);
		
		bbId = new JMenuItem("By Book ID");
		mnSearchBooks.add(bbId);
		
		bName = new JMenuItem("By Name");
		mnSearchBooks.add(bName);
		
		bAuthor = new JMenuItem("By Author");
		mnSearchBooks.add(bAuthor);
		
		bPublisher = new JMenuItem("By Publisher");
		mnSearchBooks.add(bPublisher);
		
		bbId.addActionListener(this);
		bAuthor.addActionListener(this);
		bName.addActionListener(this);
		bPublisher.addActionListener(this);
		
		view = new JMenuItem("View Issued Books");
		menuBar.add(view);
		view.addActionListener(this);
		
		logout = new JMenuItem("Logout");
		menuBar.add(logout);
		logout.addActionListener(this);
		
		setSize(getMaximumSize());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == bbId){
			new SearchById();
		}
		else if(ob == bAuthor){
			new SearchByAuthor();
		}
		else if(ob == bPublisher){
			new SearchByPublisher();
		}
		else if(ob == bName){
			new SearchByName();
		}
		else if(ob == view) {
			new ViewIssued(iid, catg);
		}
		else if(ob == logout){
			dispose();
			new Login();
		}
	}
}
