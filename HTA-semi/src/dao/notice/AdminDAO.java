package dao.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import db.DBConnection;
import vo.notice.NoticeVO;



public class AdminDAO {
	private static AdminDAO instance= new AdminDAO();
	private AdminDAO() {
		
	}
	
	public static AdminDAO getInstance() {
		return instance;
	}
	//공지사항 등록
	public int insert(NoticeVO vo) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="insert into noti values(noti_seq.nextval,?,?,sysdate)";
		try {
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1, vo.getTitle());
			pre.setString(2, vo.getContent());
			int b = pre.executeUpdate();
			return b;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, pre, con);
		}
	}
	//특정 공지사항 불러오기
	public NoticeVO invite(int num) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from noti where noti_no=?";
		ResultSet re = null;
		try {
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, num);
			re=pre.executeQuery();
			if (re.next()) {
				String title = re.getString("title");
				String content = re.getString("content");
				Date regdate = re.getDate("regdate");
				NoticeVO vo = new NoticeVO(num,title,content,regdate);
				return vo;
			}
			return null;
			}catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		}
		finally {
			DBConnection.closeConn(re, pre, con);
		}
		
	}
	//공지사항을 수정하는 dao
	public int update(NoticeVO vo) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="update noti set title=?, content=? where noti_no=?";
		try {
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setString(1,vo.getTitle() );
			pre.setString(2,vo.getContent() );
			pre.setInt(3, vo.getNoti_no());
			int result = pre.executeUpdate();
			return result;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		}
		finally {
			DBConnection.closeConn(null, pre, con);
		}
		
	}
	//공지사항 삭제 dao
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql="delete from noti where noti_no=?";
		try {
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, num);
			int re = pre.executeUpdate();
			return re;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, pre, con);
		}
	}
	//모든 공지사항을 불러오는 dao
	public ArrayList<NoticeVO> all(int startRow, int endRow){
		Connection con = null;
		PreparedStatement pre = null;
		String sql="select * from (select aa.*, rownum rnum from(select * from noti order by noti_no desc) aa) where rnum>=? and rnum<=?";
		ResultSet re = null;
		
		try {
			ArrayList<NoticeVO> list = new ArrayList<>();
			con=DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, startRow);
			pre.setInt(2, endRow);
			re=pre.executeQuery();
			while (re.next()) {
				int noti_no = re.getInt("noti_no");
				String title = re.getString("title");
				String content = re.getString("content");
				Date regdate = re.getDate("regdate");
				NoticeVO vo = new NoticeVO(noti_no,title,content,regdate);
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
	//공지사항의 번호들을 전부 불러오는 dao
	public int getCount() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		String sql="select NVL(count(noti_no),0) cnt from noti";
		try {
			con = DBConnection.getConn();
			pre=con.prepareStatement(sql);
			re=pre.executeQuery();
			if (re.next()) {
				return re.getInt("cnt");
			} else {
				return 0;
			}
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	//특정하게 검색된 공지사항들을 불러들이는 dao
	public ArrayList<NoticeVO> search(String search, String keyword,int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		String sql="select * from (select aa.*, rownum rnum from (select * from noti where "+ search + " like '%" + keyword+ "%' order by noti_no desc) aa) where rnum>=? and rnum<=?";
		try {
			con= DBConnection.getConn();
			pre=con.prepareStatement(sql);
			pre.setInt(1, startRow);
			pre.setInt(2, endRow);
			ArrayList<NoticeVO> list2 = new ArrayList<>();
			re=pre.executeQuery();
			while (re.next()) {
				int noti_no = re.getInt("noti_no");
				String title = re.getString("title");
				String content = re.getString("content");
				Date regdate = re.getDate("regdate");
				NoticeVO vo = new NoticeVO(noti_no,title,content,regdate);
				list2.add(vo);
				return list2;
			}
			return null;
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return null;
		} finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	//특정하게 검색된 공지사항들의 개수를 불러들이는 dao
	public int getCount2(String search , String keyword) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet re = null;
		String sql="select NVL(count(noti_no),0) cnt from noti where "+ search + " like '%" + keyword+"%'";
		try {
			con = DBConnection.getConn();
			pre=con.prepareStatement(sql);
			re=pre.executeQuery();
			if (re.next()) {
				return re.getInt("cnt");
			} else {
				return 0;
			}
		} catch(SQLException sq) {
			System.out.println(sq.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(re, pre, con);
		}
	}
	
}
