package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import entity.Consts;
import entity.Employee;

public class UserLogic {
	private static UserLogic _instance;

	private UserLogic() {
	}

	public static UserLogic getInstance() {
		if (_instance == null)
			_instance = new UserLogic();
		return _instance;
	}

	/**
	 * fetches all employees from DB file.
	 * @return ArrayList of employees.
	 */
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> results = new ArrayList<Employee>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_EMPLOYEES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Employee(rs.getLong(rs.findColumn("id")), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	/*----------------------------------------- ADD / REMOVE / UPDATE EMPLOYEE METHODS --------------------------------------------*/

	/**
	 * Adding a new Employee with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addEmployee(String lastName, String firstName, String title, Date birthDate, Date hireDate,
			String address, String city, String country, String homePhone, String extension, String photo) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_EMPLOYEE)) {
				
				int i = 1;
				stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete the selected employee in form.
	 * return true if the deletion was successful, else - return false
	 * @param employeeID - the employee to delete from DB
     * @return 
	 */
	public boolean removeEmployee(long employeeID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_DEL_EMPLOYEE)) {
				
				stmt.setLong(1, employeeID);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Editing a exist employee with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean editEmployee(long employeeID, String lastName, String firstName, String title, Date birthDate,
			Date hireDate, String address, String city, String country, String homePhone, String extension,
			String photo) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_EMPLOYEE)) {
				int i = 1;

				stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				stmt.setLong(i++, employeeID);
				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
