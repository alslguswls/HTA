package test01_batch;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 배치프로그램
 * - 어떤 특정일(job)들을 모아두고 특정 조건이 만족되면 이 일들이 한꺼번에 처리하는 프로그램을
 *   의미한다. 이런 배치프로그램이 처리하는 일들은 우선순위가 떨어지는 작업들이기 때문에
 *   순위가 높은 다른 프로그램에 영향을 주어서는 안된다. 예를 들어 통계를 구하는 일이나 다른
 *   시스템간의 데이터동기화 작업이 될수 있다. 일반적으로 이런 일들은 새벽시간에 동작되어야
 *   하며 자바에서는 Timer와 TimerTask라는 클래스를 제공하여 배치프로그램을 만든다.
 */
public class Test01 {	
	public static void main(String[] args) {
		TimerTask task=new TimerTask() {			
			@Override
			public void run() { //배치프로그램으로 동작되어야 할 작업
				System.out.println(new Date());
			}
		};
		Timer timer=new Timer(true);
		Calendar cal=Calendar.getInstance();
		cal.set(2018,7,17,9,27,0);
		//cal에 설정된 시간에 동작되며 그 이후 2초에 한번씩 동작됨
		timer.schedule(task,new Date(cal.getTimeInMillis()),2000);
		try {
			Thread.sleep(100000);
		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
		timer.cancel();//스케쥴러 취소
	}
}


















