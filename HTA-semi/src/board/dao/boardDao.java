package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import board.vo.boardVo;
import dao.ms.BoardDao;
import db.DBConnection;
import vo.ms.BoardVo;

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
		int bnum = getMaxNum() + 1;
		String id = vo.getId();
		int cate = vo.getCate();
		String title = vo.getTitle();
		String content = vo.getContent();
		String orgfilename = vo.getOrgfilename();
		String savefilename = vo.getSavefilename();
		String starttime = vo.getStarttime();
		int startprice = vo.getStartprice();
		sql = "insert into board values(?,?,?,?,?,?,?,to_date(?,'yyyy/mm/dd hh24:mi:ss'),?,0,0,0,sysdate)";
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			pstmt.setString(2, id);
			pstmt.setInt(3, cate);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			pstmt.setString(6, orgfilename);
			pstmt.setString(7, savefilename);
			pstmt.setString(8, starttime);
			pstmt.setInt(9, startprice);
			n = pstmt.executeUpdate();
			if(n>0) {
				BoardDao dao = new BoardDao();
				BoardVo bvo = dao.detail(bnum);
				String time = bvo.getStarttime();
				String[] day = time.substring(0,10).split("-");
				int year = Integer.parseInt(day[0]);
				int month = Integer.parseInt(day[1]);
				int date = Integer.parseInt(day[2]);
				int hourOfDay = Integer.parseInt(time.substring(11,13));
				int minute = Integer.parseInt(time.substring(14,16));
				TimerTask task = new TimerTask() {
					@Override
					public void run() { 
						if(bvo.getStatus() == 0) {
							dao.statusup(bnum, 1);
							TimerTask task = new TimerTask() {
								@Override
								public void run() { 
									dao.statusup(bnum, 2);
								}
							};
							Calendar cal = Calendar.getInstance();
							cal.set(year, month-1, date, hourOfDay, minute, 0);
							Timer timer = new Timer(true);
							timer.schedule(task, new Date(cal.getTimeInMillis()+(10*60*1000)));
						}
					}
				};
				Calendar cal = Calendar.getInstance();
				cal.set(year, month-1, date, hourOfDay, minute, 0);
				Timer timer = new Timer(true);
				timer.schedule(task, new Date(cal.getTimeInMillis()));
			}
			return n;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	//게시글 리스트 불러오기 카테고리별
	public ArrayList<boardVo> list(int startRow, int endRow, int cate,String order, String where) {
		String sql = "select X.* from ( select rownum as xno, A.* from ( select *  from board order by "+order+") A where rownum <= ? and A.cate=?"+where+") X where X.xno >= ?";
		try {
			con = DBConnection.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,endRow );
			pstmt.setInt(2, cate);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();
			ArrayList<boardVo> list = new ArrayList<>();
			while (rs.next()) {
				int bnum = rs.getInt("bnum");
				String id=rs.getString("id");
				String title= rs.getString("title");
				int hit = rs.getInt("hit");
				int status = rs.getInt("status");
				Date regdate = rs.getDate("regdate");
				boardVo vo = new boardVo(bnum,id,cate,title,null,null,null,null,0,hit,0,status,regdate);
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
	public int getCount(int cate, String where) {
		try {
			con = DBConnection.getConn();
			String sql = "select NVL(count(bnum),0) cnt from board where cate=?"+where;
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
	
	// 현재 게시글 마지막 번호
	public int getMaxNum() {
		try {
			con = DBConnection.getConn();
			String sql = "select NVL(max(bnum),0) maxnum from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("maxnum");
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
	//게시글 업데이트 status 2번일 경우 경매 종료 후 수정 불가능 
	public int update(boardVo vo) {
		try {
			con = DBConnection.getConn();
			String sql = "update board set cate=?, title=?, content=?, orgfilename=?, savefilename=?,  starttime=to_date(?,'yyyyMMdd hh24:mi:ss'), startprice=? where bnum=? and status!=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCate());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getOrgfilename());
			pstmt.setString(5, vo.getSavefilename());
			pstmt.setString(6, vo.getStarttime());
			pstmt.setInt(7, vo.getStartprice());
			pstmt.setInt(8, vo.getBnum());
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(rs, pstmt, con);
		}
	}
	//글삭제 상태값 9번으로 변경
	public int delete(int bnum) {
		try {
			con = DBConnection.getConn();
			String sql = "update board set status=9 where bnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}



