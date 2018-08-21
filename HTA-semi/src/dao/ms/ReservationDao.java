package dao.ms;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
