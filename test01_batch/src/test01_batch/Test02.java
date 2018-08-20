package test01_batch;

import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test02 {
	public static void main(String[] args) {
		TimerTask task=new TimerTask() {		
			@Override
			public void run() {
				System.out.println("task수행!");
				try {
					FileOutputStream fos=new FileOutputStream("c:\\java\\" +
					   System.currentTimeMillis() +".txt");
					fos.write(100);
					fos.close();
					System.out.println("파일생성완료!");
				}catch(IOException ie) {
					System.out.println(ie.getMessage());
				}
			}
		};
		//생성자인자 true:메인메소드가 끝나면 종료,false:메인메소드가 끝나도 수행
		Timer timer=new Timer(true);
		Calendar cal=Calendar.getInstance();
		cal.set(2018,7,17,9,40,0);
		timer.schedule(task,new Date(cal.getTimeInMillis()),3000);
		try {
			Thread.sleep(100000);
		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
		timer.cancel();
		System.out.println("스케쥴러 종료");
	}
}








