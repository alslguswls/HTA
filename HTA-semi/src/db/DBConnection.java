package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static Connection getConn() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@dmszone.com:40021:xe";
			con=DriverManager.getConnection(url, "semi","semi1004");
		} catch (ClassNotFoundException ce) {
			System.out.println(ce.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return con;
	}

	public static void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static void closeConn(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static void closeConn(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static void closeConn(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static void closeConn(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
