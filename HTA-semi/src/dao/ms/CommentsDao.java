package dao.ms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import vo.ms.CommentsVo;

public class CommentsDao {
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select nvl(max(cnum),0) maxnum from comments";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxnum");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
		return -1;
	}
	
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select nvl(count(cnum),0) cnt from comments";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
		return -1;
	}
	public int insert(CommentsVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			con = DBConnection.getConn();
			int newNum = getMaxNum()+1;
			int cnum = vo.getCnum();
			int ref = vo.getRef();
			int lev = vo.getLev();
			int step = vo.getStep();
			if(cnum == 0) {
				ref = newNum;
			}else {
				String sql = "update comments set step=step+1 where ref=? and step>?";
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setInt(1, ref);
				pstmt1.setInt(2, step);
				pstmt1.executeUpdate();
				lev += 1;
				step += 1;
			}
			String sql = "insert into comments values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newNum);
			pstmt.setInt(2, vo.getBnum());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getComments());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, lev);
			pstmt.setInt(7, step);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(pstmt1);
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public ArrayList<CommentsVo> list(int num, int startRow,int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select *" + 
					"from (select AA.*, rownum rnum" + 
					"    from (select *" + 
					"        from comments" + 
					"        where bnum=?" + 
					"        order by ref desc,step) AA)" + 
					"where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<CommentsVo> list = new ArrayList<>();
			while(rs.next()) {
				int cnum = rs.getInt("cnum");
				int bnum = rs.getInt("bnum");
				String id = rs.getString("id");
				String comments = rs.getString("comments");
				int ref = rs.getInt("ref");
				int lev = rs.getInt("lev");
				int step = rs.getInt("step");
				list.add(new CommentsVo(cnum, bnum, id, comments, ref, lev, step));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}
