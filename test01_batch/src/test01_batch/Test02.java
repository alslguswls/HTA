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
				System.out.println("task����!");
				try {
					FileOutputStream fos=new FileOutputStream("c:\\java\\" +
					   System.currentTimeMillis() +".txt");
					fos.write(100);
					fos.close();
					System.out.println("���ϻ����Ϸ�!");
				}catch(IOException ie) {
					System.out.println(ie.getMessage());
				}
			}
		};
		//���������� true:���θ޼ҵ尡 ������ ����,false:���θ޼ҵ尡 ������ ����
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
		System.out.println("�����췯 ����");
	}
}








