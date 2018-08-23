package dao.wh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;

public class userlistDao {
	// 페이징 처리를 위한 전체글 갯수 리턴
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select NVL(count(num)),0) cnt from users";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0; // 만약 없으면 0을 리턴
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}	
	public ArrayList<MembersVo
	
	
}
