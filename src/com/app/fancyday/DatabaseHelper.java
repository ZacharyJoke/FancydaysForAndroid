package com.app.fancyday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 建立数据库连接和create a database
 * @author ZacharyJoke
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper{

//	private static final int VERSION = 1;
	private Context mContext;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mContext=context;
		
	}
	
//	public DatabaseHelper(Context context,String name,int version){
//		this(context,name,null,version);
//	}
//	
//	public DatabaseHelper(Context context,String name){
//		this(context,name,VERSION);
//	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//使用execSQL函数执行SQL语句
		db.execSQL("create table dataType(" +
				"iv_portrait varchar(50)," +
				"name varchar(100)," +
				"signature varchar(50)," +
				"tv_date varchar(50))");
		Toast.makeText(mContext, "创建数据库成功", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists dataType");
		onCreate(db);
	}

}
