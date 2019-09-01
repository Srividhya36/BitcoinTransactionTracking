import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Timer;
import java.util.TimerTask;
public class clearDB extends TimerTask{
    public void run()
    {
    	try
    	{
    	Class.forName("com.mysql.jdbc.Driver"); 
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka","root","");  
    	PreparedStatement stmt=con.prepareStatement("DELETE FROM kafkadata ORDER BY timestamp ASC limit 100;"); 
    	stmt.executeUpdate();
    	con.close();
    	}
    	catch(Exception e){ System.out.println(e);}  
	}

}
