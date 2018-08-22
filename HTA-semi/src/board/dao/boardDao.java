package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.boardVo;
import db.DBConnection;

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
		Date starttime = vo.getStarttime();
		int startprice = vo.getStartprice();
		sql = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,?,0,0,0)";
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, cate);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, orgfilename);
			pstmt.setString(6, savefilename);
			pstmt.setDate(7, starttime);
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
	public ArrayList<boardVo> list(int startRow, int endRow, int cate) {
		String sql = "SELECT * FROM " + "(  " + "	   SELECT AA.*,ROWNUM RNUM FROM " + "	   ( "
				+ "	     SELECT * FROM board " + "	     ORDER BY REF DESC,STEP ASC " + "	   )AA " + ")"
				+ " WHERE RNUM>=?AND RNUM<=? AND CATE = ?";
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, cate);
			rs = pstmt.executeQuery();
			ArrayList<boardVo> list = new ArrayList<>();
			while (rs.next()) {
				int bnum = rs.getInt("bnum");
				String id=rs.getString("id");
				String title= rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				int status = rs.getInt("status");
				boardVo vo = new boardVo(bnum,id,cate,title,content,null,null,null,0,hit,0,status);
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

}