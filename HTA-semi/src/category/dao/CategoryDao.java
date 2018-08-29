package category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.dao.boardDao;
import category.vo.CategoryVo;
import db.DBConnection;

public class CategoryDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	int n;
	// 싱글패턴 생성자생성
	private static CategoryDao instance = new CategoryDao();

	// 디펄트생성자
	private CategoryDao() {}

	// 생성자 호출
	public static CategoryDao getInstance() {
		return instance;
	}

	//category modify
	public int rename(CategoryVo vo) {
		try {
			con=DBConnection.getConn();
			sql="update category set name=? where cate=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getCate());
			n=pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return n;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	
		//category delete
		public int delete(int cate) {
			try {
				con=DBConnection.getConn();
				sql="delete from category where cate=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cate);
				n=pstmt.executeUpdate();
				return n;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return n;
			}finally {
				DBConnection.closeConn(null, pstmt, con);
			}
		}
	
	
	
	
	//insert category
	public int addCategory(String name) {
		try {
			con=DBConnection.getConn();
			sql="insert into category values(cate_seq.nextval,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			n=pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return n;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	//get list category
	public ArrayList<CategoryVo> list() {
		ArrayList<CategoryVo> list= new ArrayList<CategoryVo>();
		try {
			con=DBConnection.getConn();
			sql="select * from category";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CategoryVo vo=new CategoryVo(rs.getInt("cate"),rs.getString("name"));
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	
	//name check
	public int nameCK(String name) {
		try {
			con=DBConnection.getConn();
			sql="select * from category where name=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				n= rs.getInt("");
			}
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}
