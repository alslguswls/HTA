package dao.ms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import vo.ms.ChatVo;

public class ChatDao {
	public int insert(ChatVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "insert into chat values(chat_seq.nextval,?,?,?,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBnum());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getStr());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public int statusUpdate(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBConnection.getConn();
			String sql = "update chat set status=1 where bnum=? and status=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	public ArrayList<ChatVo> list(int bnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConn();
			String sql = "select * from chat where bnum=? order by chat_no";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			ArrayList<ChatVo> list = new ArrayList<>();
			while(rs.next()) {
				int chat_no = rs.getInt("chat_no");
				String id = rs.getString("id");
				String str = rs.getString("str");
				int status = rs.getInt("status");
				list.add(new ChatVo(chat_no, bnum, id, str, status));
			}
			statusUpdate(bnum);
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
}
