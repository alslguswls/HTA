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
import lib.SHA256Util;
import sun.security.jca.GetInstance.Instance;
import vo.wh.MembersVo;
/*
2018-08-26	윤우현 	사용자 로그인 처리 여부 수정(전체 데이터 서치 방법 폐기. id, pw 존재 여부만 확인)
2018-09-01	윤우현 	사용자 패스워드 암호화 기능 추가(회원가입시, 로그인 처리시)
2018-09-01	윤우현 	사용자 패스워드 암호화 기능 추가(회원 정보 수정시)
 */

public class MembersDao {
	
	// 사용자 로그인 처리(DB에 id, pwd 존재 여부 확인) 
	public MembersVo loginCheck(MembersVo vo){
		// 1.입력된 패스워드 암호화(입력된 id로 회원정보를 조회해서 pwd_enc와 pwd를 구한다.)
		String id = vo.getId();	// 입력된 id
		String pwd = vo.getPwd();	// 입력된 패스워드
		MembersVo getinfovo = getinfo(id);	// getinfo 메소드 호출해서 해당id 사용자 정보를 getinfovo에 저장
		if (getinfovo == null) {	// 입력된 아이디가 없을경우(getinfovo에 값이 없을경우)
			return null;
		}
		String pwd_enc = getinfovo.getPwd_enc();	// DB에서 해당 id 사용자에 저장된 pwd_enc 암호화 키를 불러옴
	        String loginCheckPwd = SHA256Util.getEncrypt(pwd, pwd_enc);		// 위에 키를 이용해 입력된 패스워드를 암호화 해 봄
	        
	        // 2. DB에 입력되어 있는 패스워드와 로그인시에 입력되어 암호화한 패스워드를 비교
	        String encryptedPwd = getinfovo.getPwd();

	        // lev가 9가 아니고, DB에 암호화된 패스워드와 로그인시 입력된 패스워드가 같으면 loginVo에 id, lev 값을 전달
	        if (getinfovo.getLev() != 9 && encryptedPwd.equals(loginCheckPwd)) {
			MembersVo loginVo=new MembersVo();

			loginVo.setId(id);
			loginVo.setLev(getinfovo.getLev());
			
			return loginVo;
	        }
		
		return null;
	}
	
	// 사용자 등록
	public int insert(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=db.DBConnection.getConn();
			String sql="insert into users (id,pwd,email,phone,addr,regdate,pwd_enc) values(?,?,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,vo.getId());
			pstmt.setString(2,vo.getPwd());
			pstmt.setString(3,vo.getEmail());
			pstmt.setString(4,vo.getPhone());
			pstmt.setString(5,vo.getAddr());
			pstmt.setString(6, vo.getPwd_enc());
			
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
				String pwd_enc=rs.getString("pwd_enc");
				
				MembersVo vo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				vo.setPwd_enc(pwd_enc);
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
			String sql="update users set pwd=?,email=?, phone=?, addr=?, coin=?, pwd_enc = ? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getPwd());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPhone());
			pstmt.setString(4,vo.getAddr());
			pstmt.setLong(5,vo.getCoin());
			pstmt.setString(6, vo.getPwd_enc());
			pstmt.setString(7, vo.getId());
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
				"            select id, pwd, email, phone, addr, lev, coin, to_char(regdate, 'yyyy/mm/dd hh24:mi:ss') as regdate from users where lev != '9' order by id asc" +  // lev가 '9'(삭제처리)인 것은 안나오게 함
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
				String regdate=rs.getString("regdate");
				
				MembersVo vo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				vo.setRegdate(regdate);
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




