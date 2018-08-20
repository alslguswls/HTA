package test01_batch;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 * ��ġ���α׷�
 * - � Ư����(job)���� ��Ƶΰ� Ư�� ������ �����Ǹ� �� �ϵ��� �Ѳ����� ó���ϴ� ���α׷���
 *   �ǹ��Ѵ�. �̷� ��ġ���α׷��� ó���ϴ� �ϵ��� �켱������ �������� �۾����̱� ������
 *   ������ ���� �ٸ� ���α׷��� ������ �־�� �ȵȴ�. ���� ��� ��踦 ���ϴ� ���̳� �ٸ�
 *   �ý��۰��� �����͵���ȭ �۾��� �ɼ� �ִ�. �Ϲ������� �̷� �ϵ��� �����ð��� ���۵Ǿ��
 *   �ϸ� �ڹٿ����� Timer�� TimerTask��� Ŭ������ �����Ͽ� ��ġ���α׷��� �����.
 */
public class Test01 {	
	public static void main(String[] args) {
		TimerTask task=new TimerTask() {			
			@Override
			public void run() { //��ġ���α׷����� ���۵Ǿ�� �� �۾�
				System.out.println(new Date());
			}
		};
		Timer timer=new Timer(true);
		Calendar cal=Calendar.getInstance();
		cal.set(2018,7,17,9,27,0);
		//cal�� ������ �ð��� ���۵Ǹ� �� ���� 2�ʿ� �ѹ��� ���۵�
		timer.schedule(task,new Date(cal.getTimeInMillis()),2000);
		try {
			Thread.sleep(100000);
		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
		timer.cancel();//�����췯 ���
	}
}


















