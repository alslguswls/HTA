package dao.wh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.tribes.util.ExceptionUtils;

import com.sun.org.apache.bcel.internal.generic.Select;

import board.dao.boardDao;
import db.DBConnection;
import sun.security.jca.GetInstance.Instance;
import vo.wh.MembersVo;
/*
2018. 8. 26	윤우현		사용자 로그인 처리 여부 수정(전체 데이터 서치 방법 폐기. id, pw 존재 여부만 확인)
 */

public class MembersDao {
	
	// 사용자 로그인 처리(DB에 id, pwd 존재 여부 확인) 
	public MembersVo loginCheck(MembersVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=db.DBConnection.getConn();
			String sql="select * from users where id = ? and pwd = ? and lev != '9' ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				Integer lev=rs.getInt("lev");
				Long coin=rs.getLong("coin"); 
				
				MembersVo loginVo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				return loginVo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			db.DBConnection.closeConn(rs, pstmt, con);
		}	
	}
	
	// 사용자 등록
	public int insert(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=db.DBConnection.getConn();
			String sql="insert into users (id,pwd,email,phone,addr) values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,vo.getId());
			pstmt.setString(2,vo.getPwd());
			pstmt.setString(3,vo.getEmail());
			pstmt.setString(4,vo.getPhone());
			pstmt.setString(5,vo.getAddr());
			
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			db.DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	// 사용자 실제 삭제(이기능은 현재 사용안함)
	public int delete(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=db.DBConnection.getConn();
			String sql="delete from users where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			db.DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	// 사용자 삭제처리(lev 컬럼을 '9'로 처리')
	public int del(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=db.DBConnection.getConn();
			String sql="update users set lev='9' where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			db.DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	// 사용자 상세정보 
	public MembersVo getinfo(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=db.DBConnection.getConn();
			String sql="select * from users where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				Integer lev=rs.getInt("lev");
				Long coin=rs.getLong("coin");
				
				MembersVo vo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				return vo;
			} 
			
			return null;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs ,pstmt,con);
		}
	}
	
	// 사용자 수정
	public int update(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="update users set pwd=?,email=?, phone=?, addr=?, coin=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getPwd());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPhone());
			pstmt.setString(4,vo.getAddr());
			pstmt.setLong(5,vo.getCoin());
			pstmt.setString(6, vo.getId());
			int n=pstmt.executeUpdate();
			return n;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null,pstmt,con);
		}
	}
		
	// 페이징 처리를 위한 사용자 수 구하기 
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select count(1) cnt from users where lev != '9' "; // lev가 '9'(삭제처리)인 것은 카운팅 안 함
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0; // 만약 없으면 0을 리턴
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	// 사용자 리스트 불러오기
	public ArrayList<MembersVo> list(int startRow, int endRow){
		String sql="select * from " + 
				"    (" + 
				"        select AA.*,rownum rnum from " + 
				"        (" + 
				"            select * from users where lev != '9' order by id asc" +  // lev가 '9'(삭제처리)인 것은 안나오게 함
				"        ) " + 
				"    AA) " + 
				"where rnum>=? and rnum<=?";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list = new ArrayList<>();
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				Integer lev=rs.getInt("lev");
				Long coin=rs.getLong("coin");
				
				MembersVo vo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	// 코인 업데이트
	public int coinUpdate(String id2, Long coin2) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="update users set coin=coin + ? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, coin2);
			pstmt.setString(2, id2);
			int n=pstmt.executeUpdate();
			return n;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null,pstmt,con);
		}
	}

	// 회원관리 게시판 검색기능에서 호출됨
//	private static MembersDao instance=new MembersDao();
//	
//	public static MembersDao instance() {
//		return instance();
//	}
	
	// 싱글패턴 생성자생성
	//private static MembersDao instance = new MembersDao();
	
	// 디폴트생성자
	//private MembersDao() {	}
		
	// 생성자 호출
//	public static MembersDao getInstance() {
//		return instance;
//	}
		
	// 회원관리 게시판 id, email 검색기능 
	public ArrayList<MembersVo> search(String searchSel, String searchText, int startRow, int endRow){
		String sql="select * from " + 
				"    (" + 
				"        select AA.*,rownum rnum from " + 
				"        (" + 
				"            select * from users where "+ searchSel + " like '%" + searchText + "%' and lev != '9' order by id asc" +  
				"        ) " + 
				"    AA) " + 
				"where rnum>=? and rnum<=?";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list2 = new ArrayList<>();
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				Integer lev=rs.getInt("lev");
				Long coin=rs.getLong("coin");
				
				MembersVo vo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				list2.add(vo);
			}
			return list2;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	// 회원관리 게시판 검색된 사용자 수 구하기 
		public int searchCount(String searchSel, String searchText) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=DBConnection.getConn();
				String sql="select count(1) cnt from users where "+ searchSel + " like '%" + searchText + "%' and lev != '9' "; // lev가 '9'(삭제처리)인 것은 카운팅 안 함
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt("cnt");
				}else {
					return 0; // 만약 없으면 0을 리턴
				}
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
				DBConnection.closeConn(rs, pstmt, con);
			}
		}
}




