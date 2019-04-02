import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QueueOfDoctor {

	public static void main(String[] args) {
		Scanner  sc=new Scanner(System.in);
		System.out.println("Enter Id");
		int id=sc.nextInt();
		String jdbcUrl="jdbc:mysql://localhost:3306/ex2?useSSL=false";
		String jdbcUser="root";
		String jdbcPassword="4333";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword);
			Statement statement = connection.createStatement();
			String allCustomersQuery = "select p.patient_id, patient_name ,appointment_time from Patients p join appointment a \r\n" + 
					"on doctor_id = "+id+" order by appointment_time;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			while(resultSet.next()){
				System.out.println(resultSet.getInt("patient_id")+" "+
						resultSet.getString("patient_name")+" "+resultSet.getString("appointment_time"));
			}
			resultSet.close();		
			statement.close();		
			connection.close();	
		}
		catch (SQLException sqle) {System.out.println("Eror1");}
		catch (ClassNotFoundException e) {System.out.println("Eror2");}
	}
}
