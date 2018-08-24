package test.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.db.DbConnection;
import test.vo.BoardVO;

public class BoardDAO {
	// 가장큰 글번호 얻어오기
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		try {
		con=DbConnection.getConn();
		String sql="select NVL(max(num),0) MAXNUM from guestboard";
		//nvl이란 특정값이 null이면 원하는 값으로 바꿔줄때 씀
		pre=con.prepareStatement(sql);
		re=pre.executeQuery();
		if (re.next()) {
			return re.getInt("maxnum");
		} else {
			return 0;
		}
		}catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DbConnection.closeConn(re, pre, con);
		}
	} 
	
	public int getCount() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		try {
		con=DbConnection.getConn();
		String sql="select NVL(count(num),0) cnt from guestboard";
		//nvl이란 특정값이 null이면 원하는 값으로 바꿔줄때 씀
		pre=con.prepareStatement(sql);
		re=pre.executeQuery();
		if (re.next()) {
			return re.getInt("cnt");
		} else {
			return 0;
		}
		}catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DbConnection.closeConn(re, pre, con);
		}
	}
	
	public int insert(BoardVO vo) {
		Connection con=null;
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		try {
			con=DbConnection.getConn();
			int boardNum = getMaxNum()+1;
			//여기부터 부모들에 대한 정보
			int num = vo.getNum();
			int ref=vo.getRef();
			int lev=vo.getLev();
			int step=vo.getStep();
			if(num==0) {//새글인 경우
				ref=boardNum;
			} else {//답글인 경우
				String sql="update guestboard set step=step+1 where ref=? and step>?";
							
				pre2=con.prepareStatement(sql);
				pre2.setInt(1, ref);
				pre2.setInt(2, step);
				pre2.executeUpdate();
				lev+=1;
				step+=1;
			}
		String sql="insert into guestboard values(?,?,?,?,?,?,?)";
		pre=con.prepareStatement(sql);
		pre.setInt(1, boardNum);
		pre.setString(2, vo.getWriter());
		pre.setString(3, vo.getTitle());
		pre.setString(4, vo.getContent());
		pre.setInt(5, ref);
		pre.setInt(6, lev);
		pre.setInt(7, step);
		int n = pre.executeUpdate();
		return n;
		} catch(SQLException sql) {
			System.out.println(sql.getMessage());
			return -1;
		} finally {
			DbConnection.closeConn(pre);
			DbConnection.closeConn(pre2);
			DbConnection.closeConn(con);
		}
		
	
	}
	public ArrayList<BoardVO> list(int startRow, int endRow){
		String sql="select * from " + 
				"(select aa.* , rownum rnum from(select * from guestboard order by ref desc, step asc) " + 
				"aa) " + 
				" where rnum>=? and rnum<=?";
		
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		ArrayList<BoardVO> list=new ArrayList<>();
		try {
			con=DbConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, startRow);
			pre.setInt(2, endRow);
			re=pre.executeQuery();
			while(re.next()) {
				int num = re.getInt("num");
				String writer=re.getString("writer");
				String title=re.getString("title");
				String content=re.getString("content");
				int ref = re.getInt("ref");
				int lev = re.getInt("lev");
				int step = re.getInt("step");
				BoardVO vo = new BoardVO(num, writer, title,content,ref,lev,step);
				list.add(vo);
			}
			return list;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		} finally {
			DbConnection.closeConn(re, pre, con);
		}
	}
	
	public BoardVO detail(int num) {
		String sql="select * from guestboard where num=?";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		try {
			con= DbConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, num);
			re=pre.executeQuery();
			if(re.next()) {
				String writer = re.getString("writer");
				String title = re.getString("title");
				String content = re.getString("content");
				int ref=re.getInt("ref");
				int lev=re.getInt("lev");
				int step=re.getInt("step");
				BoardVO vo = new BoardVO(num, writer, title, content, ref, lev, step);
				return vo;
			}
			return null;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		} finally {
			DbConnection.closeConn(re, pre, con);
		}
	}
}
