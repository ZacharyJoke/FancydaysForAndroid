package com.app.fancyday;

import java.io.Flushable;
import java.text.BreakIterator;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import com.example.fancydaysforandroid.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;

/**
 * MainActivity main
 * @author ZacharyJoke
 *
 */
public class MainActivity extends Activity implements OnClickListener {

	private TextView tv_time_remaining;
	private CountdownUtil countdown;
	private ListView mylistview;
	private ImageButton IB_New;
	private ImageButton IB_Setting;
	private EditText inputText;
	private Myadapter adapter;

	private DatabaseHelper dbHelper;
	private GetDataType dataType;
	SQLiteDatabase db;

	String index = "";// 
	String Set_date = "";
	List<GetDataType> list = new ArrayList<GetDataType>();
	List<GetDataType> info = new ArrayList<GetDataType>();

	List<GetDataType> map = new ArrayList<GetDataType>();
	private SQLiteUtils sqliteutils;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置窗口没有标题栏
		setContentView(R.layout.activity_main);
		
		
		sqliteutils=new SQLiteUtils();
		dbHelper =sqliteutils.createDBHelper(this);

		mylistview = (ListView) findViewById(R.id.listview);
		tv_time_remaining = (TextView) findViewById(R.id.tv_time);
		
		IB_New=(ImageButton) findViewById(R.id.IB_new);
		IB_Setting=(ImageButton) findViewById(R.id.IB_setting);

		IB_New.setOnClickListener(this);
		IB_Setting.setOnClickListener(this);
     
		list =getdatas();
		adapter = new Myadapter(this,list);
		
		mylistview.setAdapter(adapter);
		
		
		initCounttime();
		OnItemClickListener OnItemCListener=new OnItemClickListener();		
		OnItemLongClickListener onItemLongClickListener=new OnItemLongClickListener();
		mylistview.setOnItemClickListener((OnItemCListener));
		mylistview.setOnItemLongClickListener(onItemLongClickListener);
		
	}

	

		class OnItemLongClickListener implements android.widget.AdapterView.OnItemLongClickListener  {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				GetDataType mGetDataType = list.get(position);
	    		
	    		dbHelper.getReadableDatabase();	 
//	    		mGetDataType.getName();
	    		
//	    		index=mGetDataType.getName().toString();
				sqliteutils.delete(dbHelper, mGetDataType);
				Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT)
				.show();
				list.remove(position);  
				adapter.notifyDataSetChanged();
				
				return false;
			}
		}
    class OnItemClickListener implements android.widget.AdapterView.OnItemClickListener{
    	public void onItemClick(AdapterView<?> parent, View view,
    			int position, long id) {
    		// TODO Auto-generated method stub
    		GetDataType mGetDataType = list.get(position);
    		System.out.println(mGetDataType.getSignature());
    		Set_date = mGetDataType.getSignature();
//    		Intent showIntent =new Intent();
//    		startActivity(showIntent)
    		countdown.closeHandler();//关闭前一个时间进程
    		initCounttime();    		
    	}
    }

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

	private List<GetDataType> getdatas() {

		dataType = new GetDataType();
		SQLiteUtils sqlite = new SQLiteUtils();
		sqlite.query(dbHelper, dataType);
		map = sqlite.map;
		for (int i = 0; i < map.size(); i++) {
			int iv_portrait = map.get(i).getIv_portrait();
			String name = map.get(i).getName();
			String signature = map.get(i).getSignature();
			String tv_date = map.get(i).getTv_date();

			list.add(new GetDataType(iv_portrait, name, signature, tv_date));
		}
		dbHelper.close();
		return list;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		countdown.closeHandler();
		super.onDestroy();

	}

	@Override
	public void onClick(View v) {
		if (v.equals(IB_New)) {
			Intent intent = new Intent(this, AddActivity.class);
			startActivity(intent);
			finish();
		} else if (v.equals(IB_Setting)) {
			// dbHelper.getReadableDatabase();
			// sqliteutils.insert(dbHelper, dataType);
			Toast.makeText(MainActivity.this, "该功能待开发", Toast.LENGTH_SHORT)
					.show();
			// adapter = new Myadapter(this, getdatas());
			// mylistview.setAdapter(adapter);
		}

	}
}