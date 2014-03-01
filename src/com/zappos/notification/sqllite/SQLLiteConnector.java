package com.zappos.notification.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLLiteConnector {

	public static void main(String args[])
	{
		System.out.println(new SQLLiteConnector().getIds().toString());
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public void createDatabase() {
		Connection conn = getConnection();
		Statement stat;
		try {
			stat = conn.createStatement();
			stat.executeUpdate("create  table if not exists styleIds (emailId, styleId);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void insert(String emailId, String styleId) {
		Connection conn = getConnection();
		try {
			PreparedStatement prep = conn
					.prepareStatement("insert into styleIds values (?, ?);");
			prep.setString(1, emailId);
			prep.setString(2, styleId);
			conn.setAutoCommit(false);
			prep.execute();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<String> select( String styleId) {
		Connection conn = getConnection();
		try {
			PreparedStatement prep = conn
					.prepareStatement("select emailId from styleIds where styleId = ?;");
			prep.setString(1, styleId);
			ResultSet rs = prep.executeQuery();
			List<String> ret = new ArrayList<String>();
			while (rs.next()) {
				ret.add(rs.getString("emailId"));
			}
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<String> getIds() {
		Connection conn = getConnection();
		try {
			PreparedStatement prep = conn
					.prepareStatement("select styleId from styleIds;");
			ResultSet rs = prep.executeQuery();
			List<String> ret = new ArrayList<String>();
			while (rs.next()) {
				ret.add(rs.getString("styleId"));
			}
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean delete(String styleId) {
		Connection conn = getConnection();
		try {
			PreparedStatement prep = conn
					.prepareStatement("delete  from styleIds where styleId = ?;");
			prep.setString(1, styleId);
			prep.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<String> selectEmailIds(String id) {
		Connection conn = getConnection();
		try {
			
			PreparedStatement prep = conn
					.prepareStatement("select emailId from styleIds where styleId=?;");
			prep.setString(1, id);
			ResultSet rs = prep.executeQuery();
			List<String> ret = new ArrayList<String>();
			while (rs.next()) {
				ret.add(rs.getString("emailId"));
			}
			return  ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

}
