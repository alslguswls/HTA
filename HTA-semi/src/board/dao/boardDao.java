package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.vo.boardVo;
import db.DBConnection;


public class boardDao {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	int n;
	//싱글패턴 생성자생성
	private static boardDao instance=new boardDao();
	//디펄트생성자
	private boardDao() {}
	
	//생성자 호출 
	public static boardDao getInstance() {
		return instance;
	}
	//게시글 인서트
	public int insert(boardVo vo) {
		String id=vo.getId();
		int cate=vo.getCate();
		String title=vo.getTitle();
		String content=vo.getContent();
		String orgfilename=vo.getOrgfilename();
		String savefilename=vo.getSavefilename();
		Date starttime=vo.getStarttime();
		int startprice=vo.getStartprice();
		sql="insert into board values(board_seq.nextval,?,?,?,?,?,?,?,?,0,0,0)";
		try {
			con=DBConnection.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, cate);
			pstmt.setString(3,title);
			pstmt.setString(4, content);
			pstmt.setString(5, orgfilename);
			pstmt.setString(6, savefilename);
			pstmt.setDate(7, starttime);
			pstmt.setInt(8, startprice);
			n=pstmt.executeUpdate();
			return n;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	public ArrayList<boardVo> list(String cate){
		int cate_no=Integer.parseInt(cate);
		ArrayList<boardVo> list=new ArrayList<boardVo>();
		sql="";
		try {
			con=DBConnection.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cate_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

}
