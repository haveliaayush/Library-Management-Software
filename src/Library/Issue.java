package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Issue extends JFrame implements ActionListener{
	private JTextField return_date;
	private JTextField issue_date;
	private JTextField book_name;
	private JTextField book_id;
	private JTextField issuer_name;
	private JTextField issuer_id;
	private JButton issue_btn;
	private JComboBox cat;
	private JButton fill_btn;
	Connection cc = MyConnection.connect();
	int copies = 0;

	public Issue() {
		getContentPane().setLayout(null);
		
		JLabel lblBookDetails = new JLabel("Book Details");
		lblBookDetails.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblBookDetails.setBounds(128, 24, 175, 39);
		getContentPane().add(lblBookDetails);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBookId.setBounds(29, 90, 67, 28);
		getContentPane().add(lblBookId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(29, 141, 67, 28);
		getContentPane().add(lblName);
		
		JLabel lblIssueDate = new JLabel("Issue Date");
		lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIssueDate.setBounds(29, 192, 87, 28);
		getContentPane().add(lblIssueDate);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReturnDate.setBounds(29, 243, 87, 28);
		getContentPane().add(lblReturnDate);
		
		issue_date = new JTextField(LocalDate.now().toString());
		issue_date.setBackground(Color.WHITE);
		issue_date.setEditable(false);
		issue_date.setFont(new Font("Tahoma", Font.BOLD, 14));
		issue_date.setColumns(10);
		issue_date.setBounds(154, 192, 241, 28);
		getContentPane().add(issue_date);
		
		return_date = new JTextField(LocalDate.now().plusDays(14).toString());
		return_date.setEditable(false);
		return_date.setBackground(Color.WHITE);
		return_date.setFont(new Font("Tahoma", Font.BOLD, 14));
		return_date.setBounds(154, 243, 241, 28);
		getContentPane().add(return_date);
		return_date.setColumns(10);
		
		book_name = new JTextField();
		book_name.setBackground(Color.WHITE);
		book_name.setEditable(false);
		book_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		book_name.setColumns(10);
		book_name.setBounds(154, 141, 241, 28);
		getContentPane().add(book_name);
		
		book_id = new JTextField();
		book_id.setFont(new Font("Tahoma", Font.BOLD, 14));
		book_id.setColumns(10);
		book_id.setBounds(154, 90, 241, 28);
		getContentPane().add(book_id);
		
		JLabel lblIssuerDetails = new JLabel("Issuer Details");
		lblIssuerDetails.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblIssuerDetails.setBounds(124, 308, 189, 39);
		getContentPane().add(lblIssuerDetails);
		
		JLabel lblIssuerId = new JLabel("Issuer ID");
		lblIssuerId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIssuerId.setBounds(29, 365, 67, 28);
		getContentPane().add(lblIssuerId);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategory.setBounds(29, 416, 67, 28);
		getContentPane().add(lblCategory);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName_1.setBounds(29, 471, 67, 28);
		getContentPane().add(lblName_1);
		
		issuer_name = new JTextField();
		issuer_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		issuer_name.setEditable(false);
		issuer_name.setColumns(10);
		issuer_name.setBackground(Color.WHITE);
		issuer_name.setBounds(154, 471, 241, 28);
		getContentPane().add(issuer_name);
		
		issuer_id = new JTextField();
		issuer_id.setFont(new Font("Tahoma", Font.BOLD, 14));
		issuer_id.setColumns(10);
		issuer_id.setBounds(154, 365, 241, 28);
		getContentPane().add(issuer_id);
		
		cat = new JComboBox();
		cat.setBackground(Color.WHITE);
		cat.setBounds(154, 416, 241, 28);
		getContentPane().add(cat);
		cat.addItem("Select Category");
		cat.addItem("Teacher");
		cat.addItem("Student");
		
		issue_btn = new JButton("Issue Book");
		issue_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		issue_btn.setBounds(271, 528, 124, 23);
		getContentPane().add(issue_btn);
		
		fill_btn = new JButton("Fill Details");
		fill_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		fill_btn.setBounds(49, 530, 124, 23);
		getContentPane().add(fill_btn);
		
		fill_btn.addActionListener(this);
		issue_btn.addActionListener(this);
		
		setSize(458,612);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == issue_btn) {
			int c = 0;
			int bid = Integer.parseInt(book_id.getText());
			int iid = Integer.parseInt(issuer_id.getText());
			String catg = cat.getSelectedItem().toString().toLowerCase();
			String ret = return_date.getText();
			String query = "insert into issued values(?,?,?,?)";
			String query1 = "select * from issued where issuer_id = ? and category = ?";
			String query2 = "update books set copies = ? where id = ?";
			try {
				PreparedStatement ps = cc.prepareStatement(query1);
				ps.setInt(1, iid);
				ps.setString(2, catg);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
					c++;
				if (catg.equals("teacher") && c == 3) {
					JOptionPane.showMessageDialog(null, "Maximum Issue Limit Reached");
					dispose();
				}
				else if(catg.equals("student") && c == 2) {
					JOptionPane.showMessageDialog(null, "Maximum Issue Limit Reached");
					dispose();
				}
				else {
					PreparedStatement ps1 = cc.prepareStatement(query);
					ps1.setString(1,catg);
					ps1.setInt(2, iid);
					ps1.setInt(3, bid);
					ps1.setString(4, ret);
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book Issued Succesfully");
					copies-=1;
					PreparedStatement ps2 = cc.prepareStatement(query2);
					ps2.setInt(1, copies);
					ps2.setInt(2, bid);
					ps2.executeUpdate();
					dispose();
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (ob == fill_btn) {
			int bid = Integer.parseInt(book_id.getText());
			String query = "select name,copies from books where id=?";
			try {
				PreparedStatement ps = cc.prepareStatement(query);
				ps.setInt(1,bid);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					book_name.setText(rs.getString("name"));
					copies = rs.getInt("copies");
				}
				int iid = Integer.parseInt(issuer_id.getText());
				String catg = cat.getSelectedItem().toString();
				if (catg.equalsIgnoreCase("teacher")) {
					String query1 = "select name from teacherdetails where id = ?";
					PreparedStatement ps1 =cc.prepareStatement(query1);
					ps1.setInt(1, iid);
					ResultSet rs1 = ps1.executeQuery();
					if(rs1.next()) {
						issuer_name.setText(rs1.getString("name"));
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid User");
				}
				else if (catg.equalsIgnoreCase("student")){

					String query1 = "select name from studentdetails where id = ?";
					PreparedStatement ps1 =cc.prepareStatement(query1);
					ps1.setInt(1, iid);
					ResultSet rs1 = ps1.executeQuery();
					if(rs1.next()) {
						issuer_name.setText(rs1.getString("name"));
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid User");
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
