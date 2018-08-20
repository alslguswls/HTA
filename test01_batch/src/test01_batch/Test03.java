package test01_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import test.db.DBConnection;

/*
 * JDBC��ġ
 * - ���� SQL���� �ѹ��� ����(DML�� ����)
 * - ��뷮 ������Ʈ�� �ӵ� ���
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
				String name="ȫ�浿_" + i;
				int age=rnd.nextInt(80)+20;
				pstmt.setLong(1,uid);
				pstmt.setString(2,name);
				pstmt.setInt(3,age);
				//pstmt.executeUpdate();
				pstmt.addBatch();//������ sql���� ��ġ�� ���
				if(i%10000==0) {
					pstmt.executeBatch();//��ġ�� ����� sql���� �Ѳ����� ����
					pstmt.clearBatch();//��ġ�ʱ�ȭ
					con.commit();
				}
			}
			pstmt.executeBatch();//Ȥ�� ���������� �𸣴� ��ġ ����
			pstmt.clearBatch();
			con.commit();
			System.out.println("�۾��Ϸ�");
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









