package ma.studentmanagement.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.studentmanagement.com.model.Student;

public class StudentDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "ntic";
	private static final String INSERT_STUDENTS_SQL = "INSERT INTO students "
			+ "(first_name, last_name, student_id, school, study_option, " + "registration_year) VALUES "
			+ "(?, ?, ?, ?, ?, ?);";
	private static final String SELECT_STUDENT_BY_ID = "select id,first_name, "
			+ "last_name, student_id, school, study_option, registration_year " + "from students where id =?";
	private static final String SELECT_STUDENT_BY_NAME = "select id, first_name, last_name, student_id, school, "
			+ "study_option, registration_year from students where last_name like ?";
	private static final String SELECT_ALL_STUDENTS = "select * from students";
	private static final String SELECT_ONE_STUDENT = "select * from students " + "where last_name= ?";
	private static final String DELETE_STUDENTS_SQL = "delete from students " + "where id = ?;";
	private static final String UPDATE_STUDENTS_SQL = "update students set "
			+ "first_name = ?, last_name= ?, student_id= ?, school=?, "
			+ "study_option=?, registration_year=? where id = ?";

	public StudentDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public void insertStudent(Student student) throws SQLException {
		System.out.println(INSERT_STUDENTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getStudentId());
			preparedStatement.setString(4, student.getSchool());
			preparedStatement.setString(5, student.getStudyOption());
			preparedStatement.setString(6, student.getRegistrationYear());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Student selectStudent(int id) {
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String student_id = rs.getString("student_id");
				String school = rs.getString("school");
				String study_option = rs.getString("study_option");
				String registration_year = rs.getString("registration_year");
				student = new Student(id, first_name, last_name, student_id, school, study_option, registration_year);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	public List<Student> selectAllStudents() {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student> students = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String student_id = rs.getString("student_id");
				String school = rs.getString("school");
				String study_option = rs.getString("study_option");
				String registration_year = rs.getString("registration_year");
				students.add(
						new Student(id, first_name, last_name, student_id, school, study_option, registration_year));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}

	public Student findStudentByName(String last_name) throws SQLException {
		Student student = new Student();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_STUDENT);) {
			preparedStatement.setString(1, last_name);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String first_name = rs.getString("first_name");
				String last_nameS = rs.getString("last_name");
				String student_id = rs.getString("student_id");
				String school = rs.getString("school");
				String study_option = rs.getString("study_option");
				String registration_year = rs.getString("registration_year");
				student.setStudentId(student_id);
				student.setFirstName(first_name);
				student.setLastName(last_nameS);
				student.setSchool(school);
				student.setStudyOption(study_option);
				student.setRegistrationYear(registration_year);
			}
		}

		return student;
	}

	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setString(3, student.getStudentId());
			statement.setString(4, student.getSchool());
			statement.setString(5, student.getStudyOption());
			statement.setString(6, student.getRegistrationYear());
			statement.setInt(7, student.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public List<Student> selectStudentName(String name) {
		List<Student> students = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_NAME);) {
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String student_id = rs.getString("student_id");
				String school = rs.getString("school");
				String study_option = rs.getString("study_option");
				String registration_year = rs.getString("registration_year");
				students.add(
						new Student(id, first_name, last_name, student_id, school, study_option, registration_year));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}

}
