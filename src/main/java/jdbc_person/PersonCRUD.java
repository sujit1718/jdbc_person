package jdbc_person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonCRUD {

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persondb","root","root");
		return connection;
	}
	
	public int signUpPerson(Person person) throws Exception
	{
		String sql = "insert into person values(?,?,?,?,?)";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    preparedStatement.setInt(1, person.getId());
	    preparedStatement.setString(2, person.getName());
	    preparedStatement.setLong(3, person.getPhone());
	    preparedStatement.setString(4, person.getEmail());
	    preparedStatement.setString(5, person.getPassword());
	    
	    int result = preparedStatement.executeUpdate();
	    connection.close();
	    return result;
		
	}
	public String loginPerson(String email) throws Exception {
		String sql = "select password from person where email=?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet  = preparedStatement.executeQuery();
		String password = null;
		while(resultSet.next()) {
			password = resultSet.getString("password");
		}
		connection.close();
		return password;
	}
	public Person displayInfo(String email) throws Exception {
		Connection connection = getConnection();
		String sql = "select * from person where email=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		Person person = new Person();
		while(resultSet.next()) {
			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setPhone(resultSet.getLong("phone"));
			person.setEmail(resultSet.getString("email"));
			person.setPassword(resultSet.getString("password"));
		}
		connection.close();
		return person;
	}
	public int deleteInfo(String email) throws Exception {
		String sql = "delete from person where email=?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
	}
	public int updateInfo(String email, String password) throws Exception {
		Connection connection = getConnection();
		String sql = "UPDATE PERSON SET PASSWORD = ? WHERE EMAIL = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
	}
	
}
