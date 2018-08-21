package dao.wh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.tribes.util.ExceptionUtils;

import com.sun.org.apache.bcel.internal.generic.Select;

import db.DBConnection;
import vo.wh.MembersVo;

public class MembersDao {
	
	// 회원 조회하는 부분
	public ArrayList<MembersVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=db.DBConnection.getConn();
			String sql="select * from users";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<>();
			
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				int lev=rs.getInt("lev");
				Double coin=rs.getDouble("coin"); 
				MembersVo vo=new MembersVo(id, pwd, email, phone, addr, lev, coin);
				list.add(vo);
			}
			return list;
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			db.DBConnection.closeConn(rs, pstmt, con);
		}	
	}
	
	// 회원 가입 하는 부분
	public int insert(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=db.DBConnection.getConn();
			String sql="insert into users values(?,?,?,?,?,0,0)";
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
	
	// 회원 삭제 하는 부분
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
	
	// 회원 상세정보 조회하는 부분
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
				Double coin=rs.getDouble("coin");
				
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
	
	// 회원 정보 업데이트 하는 부분
	public int update(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="update users set pwd=?,email=?, phone=?, addr=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getPwd());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPhone());
			pstmt.setString(4,vo.getAddr());
			int n=pstmt.executeUpdate();
			return n;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null,pstmt,con);
		}
	}
}




