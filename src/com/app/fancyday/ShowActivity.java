package com.app.fancyday;

import com.example.fancydaysforandroid.R;

import android.app.Activity;
import android.view.Window;
import android.widget.TextView;

/**
 * 点击Item跳转至独立
 * @author ZacharyJoke
 *
 */
public class ShowActivity extends Activity {
	private TextView tv_time_remaining;
	private CountdownUtil countdown;
	private SQLiteUtils sqliteutils;
	private DatabaseHelper dbHelper;
	
	String Set_date = "";
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置窗口没有标题栏
		setContentView(R.layout.activity_show);
		
		sqliteutils=new SQLiteUtils();
		dbHelper =sqliteutils.createDBHelper(this);
		
		tv_time_remaining = (TextView) findViewById(R.id.tv_time2);
		
	};
	
private void initCounttime() {
		
		countdown = new CountdownUtil() {
             
			@Override
			public void getCountdown(String date, boolean end) {
				if (end) {
					tv_time_remaining.setText(date);
					
				} else {
					tv_time_remaining.setText(date);
				}			
			}
		};
		String sDt = Set_date + " 00:00:00";
		countdown.getLeftData(sDt);

	}
	
}
