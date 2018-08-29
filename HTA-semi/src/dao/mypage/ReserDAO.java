package dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import db.DBConnection;
import vo.ms.ChatVo;
import vo.ms.MpriceVo;
import vo.ms.ReservationVo;
import vo.notice.NoticeVO;

public class ReserDAO {
		//개인페이지 관련 dao
			//예약 목록 출력
	/*public ArrayList<ReservationVo> reser(String id){
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		String sql="select * from reservation where id=? order by vnum desc";
		try {
			ArrayList<ReservationVo> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			re=pre.executeQuery();
			while (re.next()) {
				String id2 = re.getString("id");
				int vnum = re.getInt("vnum");
				int bnum = re.getInt("bnum");
				ReservationVo vo = new ReservationVo(vnum, bnum, id2);
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(re, pre, con);
		}
	}*/
	
	public ArrayList<ReservationVo> reserAll(int startRow, int endRow, String id){
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.*, rownum rnum from(select * from reservation  where id=? order by vnum desc) aa) where rnum>=? and rnum<=?";
		ResultSet re = null;
		
		try {
			ArrayList<ReservationVo> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setInt(2, startRow);
			pre.setInt(3, endRow);
			
			re=pre.executeQuery();
			while (re.next()) {
				String id2 = re.getString("id");
				int vnum = re.getInt("vnum");
				int bnum = re.getInt("bnum");
				ReservationVo vo = new ReservationVo(vnum, bnum, id2);
				list.add(vo);
				
			}
			
			return list;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	
	public int getReserCount(String id) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select NVL(count(vnum),0) cnt from reservation  where id=? ";
		ResultSet re = null;
		try {
			con = DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			re=pre.executeQuery();
			if(re.next()){
				return re.getInt("cnt");
			} else {
				return 0 ;
			}
		} catch(SQLException sq){
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	
	public ArrayList<MpriceVo> dealJoin(String id, int startRow, int endRow ){
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.*, rownum rnum from (select * from mprice  where id=? order by mp_no desc) aa) where rnum>=? and rnum<=?";
		ResultSet re = null;
		try {
			ArrayList<MpriceVo> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setInt(2, startRow);
			pre.setInt(3, endRow);
			re=pre.executeQuery();
			while (re.next()) {
				int mp_no=re.getInt("mp_no");
				int bnum = re.getInt("bnum");
				String dealid = re.getString("id");
				int maxprice = re.getInt("maxprice");
				MpriceVo vo = new MpriceVo(mp_no,bnum,dealid,maxprice);
				list.add(vo);
			}
			return list;
			
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	public int getMyDealCount(String id) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select NVL(count(mp_no),0) cnt from mprice  where id=?";
		ResultSet re = null;
		try {
			con = DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			re=pre.executeQuery();
			if(re.next()){
				return re.getInt("cnt");
			} else {
				return 0 ;
			}
		} catch(SQLException sq){
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	
	public ArrayList<ChatVo> chatTable(String id, int startRow, int endRow ){
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.* , rownum rnum from (select * from chat where id=? order by chat_no desc) aa) where rnum>=? and rnum<=?";
		ResultSet re = null;
		try {
			ArrayList<ChatVo> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setInt(2, startRow);
			pre.setInt(3, endRow);
			re=pre.executeQuery();
			while (re.next()) {
				int chat_no=re.getInt("chat_no");
				int bnum = re.getInt("bnum");
				String chatid = re.getString("id");
				String str = re.getString("str");
				int status = re.getInt("status");
				ChatVo vo = new ChatVo(chat_no,bnum,chatid,str,status);
				list.add(vo);
			}
			return list;
			
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	
	public int getChatCount(String id) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select NVL(count(chat_no),0) cnt from chat where id=?";
		ResultSet re = null;
		try {
			con = DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			re=pre.executeQuery();
			if(re.next()) {
				return re.getInt("cnt");
			} else {
				return 0;
			}
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
}
