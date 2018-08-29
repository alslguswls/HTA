package dao.ms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConnection;
import vo.ms.ResultVo;

public class ResultDao{
	public int insert(ResultVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "insert into result values(result_seq.nextval,?,?,?,to_date(?,'yyyy-MM-dd hh24:MI:ss'))";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBnum());
			pstmt.setString(2, vo.getId());
			pstmt.setInt(3, vo.getPrice());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(4, sdf.format(vo.getEndtime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public ResultVo select(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from result where bnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int rnum = rs.getInt("rnum");
				String id = rs.getString("id");
				int price = rs.getInt("price");
				Date endtime = rs.getDate("endtime");
				return new ResultVo(rnum, bnum, id, price, endtime);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
		return null;
	}
}
