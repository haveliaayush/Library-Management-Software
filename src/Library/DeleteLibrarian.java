package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DeleteLibrarian {

	public DeleteLibrarian() {
		int id=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Librarian ID"));
		String s = "delete from librariandetails where id=?";		
		Connection cc = MyConnection.connect();
		try{
			PreparedStatement ps = cc.prepareStatement(s);
			ps.setInt(1,id);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Deleted Successfully");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

}
