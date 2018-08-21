package dao.ms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import vo.ms.BoardVo;

public class BoardDao {
	public BoardVo detail(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from board where bnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id");
				int cate = rs.getInt("cate");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String orgfilename = rs.getString("orgfilename");
				String savefilename = rs.getString("savefilename");
				Date starttime = rs.getDate("starttime");
				int startprice = rs.getInt("startprice");
				int hit = rs.getInt("hit");
				int regv = rs.getInt("regv");
				int status = rs.getInt("status");
				return new BoardVo(bnum, id, cate, title, content, orgfilename, savefilename, starttime, startprice, hit, regv, status);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
		return null;
	}
}
