package dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import db.DBConnection;
import vo.ms.ReservationVo;

public class ReserDAO {
		//개인페이지 관련 dao
			//예약 목록 출력
	public ArrayList<ReservationVo> reser(String id){
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
	}
}
