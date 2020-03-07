package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchById {

	final JDialog dialog = new JDialog();
	int cnt,c,r;
	String [] columns;
	String [][] data;
	public SearchById() {
		int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Book ID"));
		columns = new String[]{"Book ID","Name","Author","Publisher","Cost","Copies Left"};
		String s = "select * from books where id = ?";
		Connection cc= MyConnection.connect();
		try{
			PreparedStatement ps = cc.prepareStatement(s);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			data = new String[1][6];
			while(rs.next()){
			data[0][c] = String.valueOf(rs.getInt("id"));
			++c;
			data[0][c] = rs.getString("name");
			++c;
			data[0][c] = rs.getString("author");
			++c;
			data[0][c] = rs.getString("publisher");
			++c;
			data[0][c] = String.valueOf(rs.getDouble("cost"));
			++c;
			data[0][c] = String.valueOf(rs.getInt("copies"));
			}
			JTable table = new JTable(data,columns);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane jsp = new JScrollPane(table);
			table.setEnabled(false);
			
			JButton back = new JButton("Close");
			dialog.setLayout(null);
			dialog.setModal(true);
			dialog.setSize(500,400);
			back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialog.dispose();
					
				}
			});
			back.setBounds(10,10,80,30);
			jsp.setBounds(10,60,400,300);
			dialog.add(back);
			dialog.add(jsp);
			dialog.setVisible(true);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
	
}
