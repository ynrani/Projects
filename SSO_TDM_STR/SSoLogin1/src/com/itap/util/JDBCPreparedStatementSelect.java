package com.itap.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.itap.constant.ITAPConstants;
import com.itap.security.ApplicationContextProvider;

@Repository
public class JDBCPreparedStatementSelect {

	public String selectRecordsFromTable(String userId) throws SQLException {

		Connection dbConnection = null;
		ApplicationContextProvider acp = null;
		PreparedStatement preparedStatement = null;
		String userrole = "", usermnameandrole = "";

		String selectSQL = "SELECT USER_ID,USER_TYPE,USER_NAME FROM USER_DTL WHERE USER_ID =?";

		try {
			// messageSource.getMessage("label.env", null, null)

			// dbConnection1 = dataSource.getConnection();
			// dbConnection = getDBConnection(appPath);
			acp = new ApplicationContextProvider();

			DataSource dataSource = (DataSource) acp.getApplicationContext()
					.getBean(ITAPConstants.DS_NAME);
			dbConnection = dataSource.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, userId);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String username = rs.getString(ITAPConstants.UNAME);
				userrole = rs.getString(ITAPConstants.UTYPE);
				usermnameandrole = username + "-" + userrole;

				return usermnameandrole;
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

		return usermnameandrole;

	}

	public String insetNewUser(String userId) throws SQLException {

		Connection dbConnection = null;
		ApplicationContextProvider acp = null;
		PreparedStatement preparedStatement = null;
		int rs = 1;
		StringBuffer selectSQL = new StringBuffer(
				"INSERT USER_DTL (USER_ID,USER_TYPE,USER_ACCESS,ACTION_BY,ACTION_DT,ENABLED)");

		selectSQL.append("VALUES ('" + userId + "','ROLE_USER','R','" + userId
				+ "',CURDATE(),1)");

		try {
			acp = new ApplicationContextProvider();

			DataSource dataSource = (DataSource) acp.getApplicationContext()
					.getBean(ITAPConstants.DS_NAME);
			dbConnection = dataSource.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL + "");

			// execute select SQL statement
			rs = preparedStatement.executeUpdate();

			return "ROLE_USER";

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

		return "";

	}

	public static Connection getDBConnection(String appPath) {

		Connection dbConnection = null;
		Properties properties = new Properties();

		try {

			properties.load(new FileInputStream(appPath
					+ ITAPConstants.PROP_FILE));

			Class.forName(properties.getProperty(ITAPConstants.DRIVER));

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			dbConnection = DriverManager.getConnection(
					properties.getProperty(ITAPConstants.URL),
					properties.getProperty(ITAPConstants.USER),
					properties.getProperty(ITAPConstants.PASS));

			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}
