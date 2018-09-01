package dao.ms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import vo.ms.UsersVo;

public class UsersDao {
	public UsersVo select(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from users where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String pwd = rs.getString("pwd");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				int lev = rs.getInt("lev");
				int coin = rs.getInt("coin");
				return new UsersVo(id, pwd, email, phone, addr, lev, coin);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
		return null;
	}
	public int update(UsersVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "update users set pwd=?,email=?,phone=?,addr=?,lev=?,coin=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddr());
			pstmt.setInt(5, vo.getLev());
			pstmt.setInt(6, vo.getCoin());
			pstmt.setString(7, vo.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public int coinUpdate(String id, int coin) {
		System.out.println(coin);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "update users set coin=coin+(?) where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, coin);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}
