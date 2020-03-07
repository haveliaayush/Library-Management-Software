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

public class ViewBook {

	final JDialog dialog = new JDialog();
	int cnt,c,r;
	String [] columns;
	String [][] data;
	public ViewBook() {
		String s = "select * from books";
		columns = new String[]{"Id","Name","Author","Copies"};
		Connection cc = MyConnection.connect();
		try{
			PreparedStatement ps = cc.prepareStatement(s);
			ResultSet rs = ps.executeQuery();
			rs.last();
			cnt = rs.getRow();
			rs.beforeFirst();
			data = new String[cnt][4];
			while(rs.next()){
				data[r][c] = String.valueOf(rs.getInt("id"));
				++c;
				data[r][c] = rs.getString("name");
				++c;
				data[r][c] = rs.getString("author");
				++c;
				data[r][c] = rs.getString("copies");
				c = 0;
				++r;
			}
			JTable table = new JTable(data,columns);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane jsp = new JScrollPane(table);
			table.setEnabled(false);
			
			JButton back = new JButton("Close");
			dialog.getContentPane().setLayout(null);
			dialog.setModal(true);
			dialog.setSize(386,400);
			back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialog.dispose();
				}
			});
			back.setBounds(10,10,80,30);
			jsp.setBounds(10,60,300,300);
			dialog.getContentPane().add(back);
			dialog.getContentPane().add(jsp);
			dialog.setVisible(true);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}

}
