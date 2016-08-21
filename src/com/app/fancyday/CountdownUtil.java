package com.app.fancyday;

import java.text.SimpleDateFormat;

import java.util.Date;

import android.net.ParseException;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * 时间处理
 * @author ZacharyJoke
 *
 */
public abstract class CountdownUtil {
	private static final int GET_DATE = 0;
	private String flag;
	private Thread mThread;
	private boolean isDestory = false;
	private Date nowDate1;
	private Date endDate;

	/**
	 * 两个时间之间的差
	 */
	public void getLeftData(final String sDt) {// final int type
		
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mThread = new Thread() {
				public void run() {
					while (isDestory == false) {
						String nowDate = df.format(new java.util.Date());
						try {
							nowDate1 = df.parse(nowDate);
							
							endDate = df.parse(sDt);

							long dif = endDate.getTime() - nowDate1.getTime();// 计算时间差
							
								nowDate=difftime(dif, nowDate1, endDate);
								Message msg = new Message();
								msg.what = GET_DATE;
								msg.obj = nowDate;
								mHandler.sendMessage(msg);
								SystemClock.sleep(1000);
						} catch (ParseException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
		
			mThread.start();
		} catch (Exception e) {
		}
	}

	public String difftime(long dif,Date nowDate1,Date endDate){
		String nowDate =null;
		int year=endDate.getYear()+1900;
		int month=endDate.getMonth()+1;
		int day=endDate.getDate();
		
		if (dif > 0) {
			long diff=dif;
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days
					* (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60);
			long minutes = (diff - days
					* (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60))
					/ (1000 * 60);
			long second = (diff - days
					* (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60) - minutes
					* (1000 * 60)) / 1000;
			if (days <= 0) {
				days = 0;
			}
			if (hours <= 0) {
				hours = 0;
			}
			if (minutes <= 0) {
				minutes = 0;
			}
			if (second <= 0) {
				second = 0;
			}
			nowDate = "距离\n"+year+"."+month+"."+day+"\n还有\n"+getDate(days) + "天\n" + getDate(hours)
					+ "时" + getDate(minutes) + "分"
					+ getDate(second) + "秒";
			flag = "1";
		}else{
			long diff = nowDate1.getTime()- endDate.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days
					* (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60);
			long minutes = (diff - days
					* (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60))
					/ (1000 * 60);
			long second = (diff - days
					* (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60) - minutes
					* (1000 * 60)) / 1000;
			if (days <= 0) {
				days = 0;
			}
			if (hours <= 0) {
				hours = 0;
			}
			if (minutes <= 0) {
				minutes = 0;
			}
			if (second <= 0) {
				second = 0;
			}
			
			nowDate = "距离\n"+year+"."+month+"."+day+"\n已经\n"+getDate(days) + "天\n" + getDate(hours)
					+ "时" + getDate(minutes) + "分"
					+ getDate(second) + "秒";
			flag = "2";
		}
		return nowDate;
	}
	private String getDate(long num) {
		return (num >= 10 ? "" + num : "0" + num);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GET_DATE:
				String getDate = (String) msg.obj;
				getCountdown(getDate,flag.equals("1")?false:true);
				break;
			default:
				break;
			}
		}
	};
	public abstract void getCountdown(String date,boolean end);
    public void closeHandler(){
        isDestory=true;
    }
    
}
