package test01_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import test.db.DBConnection;

/*
 * JDBC배치
 * - 많은 SQL문을 한번에 실행(DML에 적용)
 * - 대용량 업데이트시 속도 향상
 */
class MyJdbc{
	public MyJdbc() {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			con.setAutoCommit(false);
			String sql="insert into batchtest values(?,?,?)";
			pstmt=con.prepareStatement(sql);
			Random rnd=new Random();
			for(int i=1;i<=100000;i++) {
				long uid=System.currentTimeMillis();
				String name="홍길동_" + i;
				int age=rnd.nextInt(80)+20;
				pstmt.setLong(1,uid);
				pstmt.setString(2,name);
				pstmt.setInt(3,age);
				//pstmt.executeUpdate();
				pstmt.addBatch();//수행할 sql문을 배치에 담기
				if(i%10000==0) {
					pstmt.executeBatch();//배치에 저장된 sql문을 한꺼번에 수행
					pstmt.clearBatch();//배치초기화
					con.commit();
				}
			}
			pstmt.executeBatch();//혹시 남아있을지 모르는 배치 수행
			pstmt.clearBatch();
			con.commit();
			System.out.println("작업완료");
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
}
public class Test03 {
	public static void main(String[] args) {
		new MyJdbc();
	}
}









