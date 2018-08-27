package dao.ms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import board.vo.boardVo;
import db.DBConnection;


public class BoardDao {
	public boardVo detail(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select id,cate,title,content,orgfilename,savefilename,to_char(starttime, 'YYYY-MM-DD HH24:MI:SS') as starttime,startprice,hit,regv,status,regdate from (select id,cate,title,content,orgfilename,savefilename,to_date(starttime, 'YYYY-MM-DD HH24:MI') as starttime,startprice,hit,regv,status,regdate from board where bnum=?)";
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
				String starttime = rs.getString("starttime");
				int startprice = rs.getInt("startprice");
				int hit = rs.getInt("hit");
				int regv = rs.getInt("regv");
				int status = rs.getInt("status");
				Date regdate = rs.getDate("regdate");
				return new boardVo(bnum, id, cate, title, content, orgfilename, savefilename, starttime,null, startprice, hit, regv, status, regdate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
		return null;
	}
	public int hitup(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "update board set hit=hit+1 where bnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public int resvup(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "update board set regv=regv+1 where bnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}
