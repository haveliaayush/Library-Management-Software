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

public class AddBook extends JDialog implements ActionListener{
	private JTextField bname;
	private JTextField bc;
	private JTextField bauthor;
	private JTextField bpub;
	private JTextField bid1;
	private JButton cancel;
	private JButton submit;
	public int bid = 1001;
	private JTextField bcost;

	public AddBook() {
		setTitle("Add Book");
		getContentPane().setLayout(null);
		
		JLabel label_3 = new JLabel("Name:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(23, 26, 92, 25);
		getContentPane().add(label_3);
		
		JLabel lblAge = new JLabel("Copies:\r\n");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(23, 62, 92, 25);
		getContentPane().add(lblAge);
		
		JLabel lblMobileNo = new JLabel("Author:");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMobileNo.setBounds(23, 98, 92, 25);
		getContentPane().add(lblMobileNo);
		
		JLabel lblAddress = new JLabel("Publisher:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(23, 134, 92, 25);
		getContentPane().add(lblAddress);
		
		bname = new JTextField();
		bname.setBounds(145, 26, 161, 25);
		getContentPane().add(bname);
		bname.setColumns(10);
		
		JLabel lblStudentId = new JLabel("Book ID:");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId.setBounds(23, 209, 92, 25);
		getContentPane().add(lblStudentId);
		
		bc = new JTextField();
		bc.setColumns(10);
		bc.setBounds(145, 64, 161, 25);
		getContentPane().add(bc);
		
		bauthor = new JTextField();
		bauthor.setColumns(10);
		bauthor.setBounds(145, 100, 161, 25);
		getContentPane().add(bauthor);
		
		bpub = new JTextField();
		bpub.setColumns(10);
		bpub.setBounds(145, 134, 161, 25);
		getContentPane().add(bpub);
		
		bid1 = new JTextField();
		bid1.setEditable(false);
		bid1.setBackground(Color.WHITE);
		bid1.setColumns(10);
		bid1.setBounds(145, 209, 161, 25);
		getContentPane().add(bid1);
		
		ButtonGroup bg = new ButtonGroup();
		
		submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.BOLD, 16));
		submit.setBounds(23, 245, 101, 29);
		getContentPane().add(submit);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		cancel.setBounds(205, 245, 101, 29);
		getContentPane().add(cancel);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCost.setBounds(23, 173, 92, 25);
		getContentPane().add(lblCost);
		
		bcost = new JTextField();
		bcost.setColumns(10);
		bcost.setBounds(145, 170, 161, 25);
		getContentPane().add(bcost);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
		generateId();
		setSize(380,346);
		setModal(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == submit){
			if((bname.getText().length())!=0 && (bc.getText().length())!=0 && (bauthor.getText().length())!=0 && (bpub.getText().length())!=0 ){
				String name = bname.getText();
				String author = bauthor.getText();
				String pub = bpub.getText();
				double cost = Double.parseDouble(bcost.getText());
				int copies = Integer.parseInt(bc.getText());
				String s = "insert into books(id,name,author,publisher,cost,copies) values(?,?,?,?,?,?)";
				Connection cc = MyConnection.connect();
				try{
					PreparedStatement ps = cc.prepareStatement(s);
					ps.setString(2,name);
					ps.setString(3,author);
					ps.setString(4,pub);
					ps.setDouble(5,cost);
					ps.setInt(6,copies);
					ps.setInt(1,bid);
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
		String s = "select max(id) from books";
		Connection c = MyConnection.connect();
		try{
			PreparedStatement ps = c.prepareStatement(s);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				bid = rs.getInt(1);
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		if(bid ==0){
			bid = 1001;
		} else{
			++bid;
		}
		bid1.setText(String.valueOf(bid));
	}
}
