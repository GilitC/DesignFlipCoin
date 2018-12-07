package Control.Logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import Model.Consts;
import Model.Recommendation;


public class RecommendationLogic {
	private static RecommendationLogic _instance;

	private RecommendationLogic() {
	}

	public static RecommendationLogic getInstance() {
		if (_instance == null)
			_instance = new RecommendationLogic();
		return _instance;
	}

	/**
	 * fetches all Recommendations from DB file.
	 * @return ArrayList of Recommendations.
	 */
	public ArrayList<Recommendation> getRecommendation() {
		ArrayList<Recommendation> results = new ArrayList<Recommendation>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_RECOMMENDATION);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Recommendation(rs.getInt(i++), rs.getDate(i++), rs.getDouble(i++), rs.getDouble(i++),
							rs.getString(i++), rs.getString(i++)));
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
	 * Adding a new Recommendation with the parameters received from the form.
	 * return true if the insertion was successful, else - return false
     * @return 
	 */
	public boolean addRecommendation(Date dateCreated, double chanceChosen, double amountTaxRecommended,
			String publicAddress, String userSignature) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_ADD_RECOMMENDATION)) {
				
				int i = 1;
				if (dateCreated != null)
					stmt.setDate(i++, new java.sql.Date(dateCreated.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				
				stmt.setDouble(i++, chanceChosen); // can't be null
				stmt.setDouble(i++, amountTaxRecommended); // can't be null

				stmt.setString(i++, publicAddress); // can't be null
				stmt.setString(i++, userSignature); // can't be null
				
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
	 * Editing an existing Recommendation with the parameters received from the form.
	 * return true if the update was successful, else - return false
     * @return 
	 */
	public boolean editRecommendation(int recommedID, Date dateCreated, double chanceChosen, double amountTaxRecommended,
			String publicAddress, String userSignature) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_RECOMMENDATION)) {
				int i = 1;

				stmt.setInt(i++, recommedID); // can't be null
				
				if (dateCreated != null)
					stmt.setDate(i++, new java.sql.Date(dateCreated.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				
				stmt.setDouble(i++, chanceChosen); // can't be null
				stmt.setDouble(i++, amountTaxRecommended); // can't be null

				stmt.setString(i++, publicAddress); // can't be null
				stmt.setString(i++, userSignature); // can't be null
				
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
