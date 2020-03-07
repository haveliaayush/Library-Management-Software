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

public class Return extends JFrame implements ActionListener {
	private JTextField book_id;
	private JTextField est_date;
	private JTextField issuer_ud;
	private JTextField ret_date;
	private JButton fill_btn;
	private JButton return_btn;
	private JComboBox cat;
	Connection cc = MyConnection.connect();

	public Return() {
		// TODO Auto-generated constructor stub
		getContentPane().setLayout(null);
		
		JLabel lblReturnBook = new JLabel("Return Book");
		lblReturnBook.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblReturnBook.setBounds(128, 11, 175, 26);
		getContentPane().add(lblReturnBook);
		
		JLabel label = new JLabel("Book ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(20, 57, 67, 28);
		getContentPane().add(label);
		
		JLabel lblExpReturnDate = new JLabel("Exp. Return Date");
		lblExpReturnDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpReturnDate.setBounds(20, 106, 129, 28);
		getContentPane().add(lblExpReturnDate);
		
		JLabel label_3 = new JLabel("Return Date");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(20, 158, 87, 28);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Issuer ID");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(20, 217, 67, 28);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Category");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(20, 268, 67, 28);
		getContentPane().add(label_5);
		
		book_id = new JTextField();
		book_id.setFont(new Font("Tahoma", Font.BOLD, 14));
		book_id.setColumns(10);
		book_id.setBounds(147, 58, 241, 28);
		getContentPane().add(book_id);
		
		est_date = new JTextField();
		est_date.setFont(new Font("Tahoma", Font.BOLD, 14));
		est_date.setEditable(false);
		est_date.setColumns(10);
		est_date.setBackground(Color.WHITE);
		est_date.setBounds(147, 107, 241, 28);
		getContentPane().add(est_date);
		
		issuer_ud = new JTextField();
		issuer_ud.setFont(new Font("Tahoma", Font.BOLD, 14));
		issuer_ud.setColumns(10);
		issuer_ud.setBounds(147, 218, 241, 28);
		getContentPane().add(issuer_ud);
		
		cat = new JComboBox();
		cat.setBackground(Color.WHITE);
		cat.setBounds(147, 270, 241, 28);
		getContentPane().add(cat);
		cat.addItem("Select Category");
		cat.addItem("Teacher");
		cat.addItem("Student");
		
		fill_btn = new JButton("Fill Details");
		fill_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		fill_btn.setBounds(57, 339, 124, 23);
		getContentPane().add(fill_btn);
		
		return_btn = new JButton("Return Book");
		return_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		return_btn.setBounds(264, 339, 124, 23);
		getContentPane().add(return_btn);
		
		ret_date = new JTextField(LocalDate.now().toString());
		ret_date.setFont(new Font("Tahoma", Font.BOLD, 14));
		ret_date.setEditable(false);
		ret_date.setColumns(10);
		ret_date.setBackground(Color.WHITE);
		ret_date.setBounds(147, 159, 241, 28);
		getContentPane().add(ret_date);
		
		return_btn.addActionListener(this);
		fill_btn.addActionListener(this);
		
		setSize(433, 434);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob= e.getSource();
		if (ob == fill_btn) {
			int bid = Integer.parseInt(book_id.getText());
			int iid = Integer.parseInt(issuer_ud.getText());
			String catg = cat.getSelectedItem().toString().toLowerCase();
			String query  = "select * from issued where book_id = ? and issuer_id = ? and category = ? ";
			try {
				PreparedStatement ps = cc.prepareStatement(query);
				ps.setInt(1, bid);
				ps.setInt(2, iid);
				ps.setString(3, catg);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					est_date.setText(rs.getString("ret_date"));
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (ob == return_btn) {
			int bid = Integer.parseInt(book_id.getText());
			int iid = Integer.parseInt(issuer_ud.getText());
			String catg = cat.getSelectedItem().toString().toLowerCase();
			String query = "delete from issued where book_id = ? and issuer_id = ? and category = ?";
			String query1 = "update books set copies=? where id = ?";
			String query2 = "select copies from books where id = ?";
			try {
				PreparedStatement ps  = cc.prepareStatement(query);
				ps.setInt(1, bid);
				ps.setInt(2, iid);
				ps.setString(3, catg);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Returned Successfully");
				PreparedStatement ps2 = cc.prepareStatement(query2);
				ps2.setInt(1, bid);
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) {
					PreparedStatement ps1 = cc.prepareStatement(query1);
					ps1.setInt(1,rs2.getInt("copies")+1);
					ps1.setInt(2, bid);
					ps1.executeUpdate();
				}
				dispose();
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}