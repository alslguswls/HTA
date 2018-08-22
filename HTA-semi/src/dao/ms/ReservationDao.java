package dao.ms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import vo.ms.ReservationVo;

public class ReservationDao {
	public int insert(ReservationVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "insert into reservation values(reservation_seq.nextval,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBnum());
			pstmt.setString(2, vo.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public boolean select(ReservationVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from reservation where bnum=? and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBnum());
			pstmt.setString(2, vo.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
		return false;
	}
}
