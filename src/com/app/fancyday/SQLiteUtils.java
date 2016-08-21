package com.app.fancyday;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * SQLite数据库业务处理
 * @author ZacharyJoke
 *
 */
public class SQLiteUtils {
	public static final String DATABASE_NAME = "fancydays.db";
	List<GetDataType> map = new ArrayList<GetDataType>();
//	public static final String DATETIME = "datetime";
//	public static final String CONTENT = "content";

	public static DatabaseHelper createDBHelper(Context context) {
		// 创建一个DatabaseHelper对象
		DatabaseHelper dbHelper = new DatabaseHelper(context,DATABASE_NAME,null,1);
		return dbHelper;
	}

	public void query(DatabaseHelper dbHelper, GetDataType dataType) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from dataType", null);
		while (cursor.moveToNext()) {
			dataType.setIv_portrait(cursor.getInt(0)); // 获取第一列的值,第一列的索引从0开始
			dataType.setName(cursor.getString(1));// 获取第二列的值
			dataType.setSignature(cursor.getString(2));
			dataType.setTv_date(cursor.getString(3));
			
			map.add(new GetDataType(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3)));

		}
		cursor.close();
		db.close();

	}

	public void insert(DatabaseHelper dbHelper, GetDataType dataType) {
		// 生成ContentValues对象
		ContentValues values = new ContentValues();
		// 想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
		values.put("iv_portrait", "R.drawable.p1");
		values.put("name", dataType.getName());
		values.put("signature", dataType.getSignature());
		values.put("tv_date", dataType.getTv_date());

//		 values.put("iv_portrait", "R.drawable.p1");
//		 values.put("name","记录你的第一个纪念日￡");
//		 values.put("signature","2017-07-31");
//		 values.put("tv_date","31天");

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 调用insert方法，就可以将数据插入到数据库当中
		db.insert("dataType", null, values);
		values.clear();
		
		db.close();
	}
	
	public void delete(DatabaseHelper dbHelper,GetDataType mGetDataType){

		 
		 SQLiteDatabase db = dbHelper.getWritableDatabase();
		 // 删除表的所有数据
		 // db.delete("users",null,null);
		 // 从表中删除指定的一条数据
		 db.execSQL("DELETE FROM " + "signature" + " WHERE name="+mGetDataType.getSignature());
		 db.close();
	}
}





// 更新操作就相当于执行SQL语句当中的update语句
// UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
// public void update(DatabaseHelper dbHelper) {
//
// SQLiteDatabase db = dbHelper.getWritableDatabase();
// ContentValues values = new ContentValues();
// values.put("content", "dataType");
// //第一个参数是要更新的表名
// //第二个参数是一个ContentValeus对象
// //第三个参数是where子句
// db.update("user", values, "id=?", new String[]{"1"});
// db.close();
// }
//
 
// }
