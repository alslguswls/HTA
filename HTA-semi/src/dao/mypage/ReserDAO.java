package dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import board.vo.boardVo;
import db.DBConnection;
import vo.ms.BoardVo;
import vo.ms.ChatVo;
import vo.ms.MpriceVo;
import vo.ms.ReservationVo;
import vo.ms.ResultVo;
import vo.mypage.DealTableVO;
import vo.mypage.ReservationTitleVO;
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
	
	public ArrayList<ReservationTitleVO> reserAll(int startRow, int endRow, String id){
		//예약한 것들 전부 호출
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.*, rownum rnum from(select a.*, b.title from reservation a, board b where a.bnum=b.bnum and b.id=? order by a.bnum desc) aa) where rnum>=? and rnum<=?";
		ResultSet re = null;
		
		try {
			ArrayList<ReservationTitleVO> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setInt(2, startRow);
			pre.setInt(3, endRow);
			
			re=pre.executeQuery();
			while (re.next()) {
				//String id2 = re.getString("id");
				//int vnum = re.getInt("vnum");
				int bnum = re.getInt("bnum");
				String title = re.getString("title");
				ReservationTitleVO vo = new ReservationTitleVO(bnum, title);
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
		//예약받은 개수 호출
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
	
	public ArrayList<DealTableVO> dealJoin(String id, int startRow, int endRow ){
		//경매낙찰받은 것들 호출
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.*, rownum rnum2 from (select a.*, b.title from result a, board b where a.bnum=b.bnum and b.id=? order by a.bnum desc) aa) where rnum2>=? and rnum2<=?";
		ResultSet re = null;
		try {
			ArrayList<DealTableVO> list = new ArrayList<>();
			
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setInt(2, startRow);
			pre.setInt(3, endRow);
			re=pre.executeQuery();
			while (re.next()) {
				int rnum=re.getInt("rnum");
			
				int bnum = re.getInt("bnum");
				
				String dealid = re.getString("id");
			
				int price = re.getInt("price");
		
				Date endtime = re.getDate("endtime");
			
				String title = re.getString("title");
				//ResultVo vo = new ResultVo(rnum, bnum, dealid, price, endtime);
				//list.add(rnum);
				//list.add(bnum);
				//list.add(dealid);
				//list.add(price);
				//list.add(endtime);
				DealTableVO vo = new DealTableVO(rnum, bnum, dealid, price, endtime, title);
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
		//낙찰받은 개수 호출
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select NVL(count(rnum),0) cnt from result  where id=?";
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
	
	public ArrayList<BoardVo> chatTable(String id, int startRow, int endRow ){
		//경매창 생성한거 전부 호출하는 dao
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.* , rownum rnum from (select * from board where id=? order by bnum desc) aa) where rnum>=? and rnum<=?";
		ResultSet re = null;
		try {
			ArrayList<BoardVo> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setInt(2, startRow);
			pre.setInt(3, endRow);
			re=pre.executeQuery();
			while (re.next()) {
				
				int bnum = re.getInt("bnum");
				String chatid = re.getString("id");
				int cate = re.getInt("cate");
				String title = re.getString("title");
				String content = re.getString("content");
				String orgfilename = re.getString("orgfilename");
				String savefilename = re.getString("savefilename");
				String starttime = re.getString("starttime");
				int startprice = re.getInt("startprice");
				int hit = re.getInt("hit");
				int regv = re.getInt("regv");
				int status = re.getInt("status");
				Date regdate = re.getDate("regdate");
				
				BoardVo vo = new BoardVo(bnum, chatid,  cate, title, content,  orgfilename, 
						savefilename, starttime, startprice, hit, regv, status, regdate);
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
	public ArrayList<String> titleTable(String id){
		//경매창 생성한거 전부 호출하는 dao
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select a.*, b.title from result a, board b where a.bnum=b.bnum and b.id=? order by a.bnum desc";
		ResultSet re = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, id);
		
			re=pre.executeQuery();
			if (re.next()) {
				
				String title = re.getString("title");
				
				
				list.add(title);
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
		//경매 생성 개수 세는 다오
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select NVL(count(bnum),0) cnt from board where id=?";
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
	
	public int callBnum(int num) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from board where bnum=?";
		ResultSet re = null;
		
		try {
			con = DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, num);
			re=pre.executeQuery();
			int realbnum=re.getInt("bnum");
			return realbnum;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(re, pre, con);
		}
		
	}
}
