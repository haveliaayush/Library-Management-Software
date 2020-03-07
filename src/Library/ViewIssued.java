package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewIssued {

	final JDialog dialog = new JDialog();
	int cnt,c,r;
	String [] columns;
	String [][] data;
	public ViewIssued(int iid, String catg) {
		String s = "select * from issued where issuer_id = ? and category = ? ";
		columns = new String[]{"Book ID","IssuedTo ID","Return Date"};
		Connection cc = MyConnection.connect();
		try{
			PreparedStatement ps = cc.prepareStatement(s);
			ps.setInt(1, iid);
			ps.setString(2, catg.toLowerCase());
			ResultSet rs = ps.executeQuery();
			rs.last();
			cnt = rs.getRow();
			rs.beforeFirst();
			data = new String[cnt][3];
			while(rs.next()){
				data[r][c] = String.valueOf(rs.getInt("book_id"));
				++c;
				data[r][c] = String.valueOf(iid);
				++c;
				data[r][c] = rs.getString("ret_date");
				c = 0;
				++r;
			}
			JTable table = new JTable(data,columns);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane jsp = new JScrollPane(table);
			table.setEnabled(false);
			
			dialog.getContentPane().setLayout(null);
			dialog.setModal(true);
			dialog.setSize(287,384);
		
			jsp.setBounds(10,11,227,300);
			dialog.getContentPane().add(jsp);
			dialog.setVisible(true);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}

}
