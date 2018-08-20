package q.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import q.db.DBConnection;
import q.vo.BoardVo;

public class BoardDao {
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(max(num),0) maxnum from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxnum");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(count(num),0) cnt from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	public int getSearchCount(String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(count(num),0) cnt from board where "+ search +" like '%"+keyword+"%'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	public int insert(BoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DBConnection.getConn();
			int boardNum=getMaxNum()+1;
			String sql="insert into board values(?,?,?,?,0,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,boardNum);
			pstmt.setString(2,vo.getWriter());
			pstmt.setString(3,vo.getTitle());
			pstmt.setString(4,vo.getContent());
			pstmt.setInt(5, vo.getPwd());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(pstmt);
			DBConnection.closeConn(pstmt1);
			DBConnection.closeConn(con);
		}
	}
	public ArrayList<BoardVo> list(int startRow,int endRow, String search, String keyword){
		String sql;
		if(search!=null) {
			sql = "select * from( select AA.*, rownum rnum from ( select * from board where "+search+ " like '%"+keyword+"%'" +" order by num desc) AA) where rnum>=? and rnum<=?";
		}else {
			sql = "select * from( select AA.*, rownum rnum from ( select * from board order by num desc) AA) where rnum>=? and rnum<=?";
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<BoardVo> list = new ArrayList<>();
			while(rs.next()){
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				int pwd = rs.getInt("pwd");
				BoardVo vo = new BoardVo(num, writer, title, content, hit, pwd);
				list.add(vo);
			}
			return list;
		}catch (SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	public BoardVo detail(int num) {
		String sql1 = "update board set hit=hit+1 where num=?";
		String sql="select * from board where num=?";
		Connection con=null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, num);
			pstmt1.executeUpdate();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String writer=rs.getString("writer");
				String title=rs.getString("title");
				String content=rs.getString("content");
				int hit = rs.getInt("hit");
				int pwd = rs.getInt("pwd");
				BoardVo vo=new BoardVo(num, writer, title, content, hit, pwd);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	public int delete(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="delete from board where num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(pstmt);
			DBConnection.closeConn(con);
		}
	}
}
