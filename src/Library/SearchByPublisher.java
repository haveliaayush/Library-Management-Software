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

public class SearchByPublisher {

	final JDialog dialog = new JDialog();
	int cnt,c,r;
	String [] columns;
	String [][] data;
	public SearchByPublisher() {
		String publisher = JOptionPane.showInputDialog(null, "Enter Publisher of Book");
		columns = new String[]{"Book ID","Name","Author","Publisher","Cost","Copies Left"};
		String s = "select * from books where publisher = ?";
		Connection cc= MyConnection.connect();
		try{
			PreparedStatement ps = cc.prepareStatement(s);
			ps.setString(1, publisher);
			ResultSet rs = ps.executeQuery();
			rs.last();
			cnt=rs.getRow();
			rs.beforeFirst();
			data = new String[cnt][6];
			while(rs.next()){
			data[r][c] = String.valueOf(rs.getInt("id"));
			++c;
			data[r][c] = rs.getString("name");
			++c;
			data[r][c] = rs.getString("author");
			++c;
			data[r][c] = rs.getString("publisher");
			++c;
			data[r][c] = String.valueOf(rs.getDouble("cost"));
			++c;
			data[r][c] = String.valueOf(rs.getInt("copies"));
			c=0;
			++r;
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
