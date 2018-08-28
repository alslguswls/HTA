package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;


import board.vo.boardVo;
import db.DBConnection;
import lib.lib;

public class boardDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	int n;
	// 싱글패턴 생성자생성
	private static boardDao instance = new boardDao();

	// 디펄트생성자
	private boardDao() {
	}

	// 생성자 호출
	public static boardDao getInstance() {
		return instance;
	}

	// 게시글 인서트
	public int insert(boardVo vo) {
		String id = vo.getId();
		int cate = vo.getCate();
		String title = vo.getTitle();
		String content = vo.getContent();
		String orgfilename = vo.getOrgfilename();
		String savefilename = vo.getSavefilename();
		String starttime = vo.getStarttime();
		int startprice = vo.getStartprice();
		sql = "insert into board values(board_seq.nextval,?,?,?,?,?,?,to_date(?,'yyyy/mm/dd hh24:mi:ss'),?,0,0,0,sysdate)";
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, cate);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, orgfilename);
			pstmt.setString(6, savefilename);
			pstmt.setString(7, starttime);
			pstmt.setInt(8, startprice);
			n = pstmt.executeUpdate();
			return n;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	//게시글 리스트 불러오기 카테고리별
	public ArrayList<boardVo> list(int startRow, int endRow, int cate,String order, String where) {
		String sql = "select X.bnum, X.id, X.title, X.hit,X.status, X.regdate from ( select rownum as xno, A.bnum , A.id, A.title, A.hit, A.status, A.regdate from ( select bnum, id, cate, title, hit, status, regdate  from board order by "+order+") A where rownum <= ? and A.cate=?"+where+") X where X.xno >= ?";
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,endRow );
			pstmt.setInt(2, cate);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();
			ArrayList<boardVo> list = new ArrayList<>();
			while (rs.next()) {
				int bnum = rs.getInt("bnum");
				String id=rs.getString("id");
				String title= rs.getString("title");
				int hit = rs.getInt("hit");
				int status = rs.getInt("status");
				Date regdate = rs.getDate("regdate");
				boardVo vo = new boardVo(bnum,id,cate,title,null,null,null,null,0,hit,0,status,regdate);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	// 전체 글의 갯수 구하기
	public int getCount(int cate) {
		try {
			con = DBConnection.getConn();
			String sql = "select NVL(count(bnum),0) cnt from board where cate=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			} else {
				return 0;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}

	public int update(boardVo vo) {
		
		return 0;
	}

}



