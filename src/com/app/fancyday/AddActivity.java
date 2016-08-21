package com.app.fancyday;

import java.util.ArrayList;
import java.util.List;

import com.example.fancydaysforandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
/**
 * 添加事项的处理
 * @author ZacharyJoke
 *
 */
public class AddActivity extends Activity implements OnClickListener {

	
	private GetDataType mydata;
	private SQLiteUtils sqliteutils;
	private DatabaseHelper dbHelper;
	
	private int iv_portrait;
	private EditText name;
	private EditText signature;
	private EditText tv_date;
	
	private ImageButton IB_Ok;
	private ImageButton IB_Back;
	List<GetDataType> info = new ArrayList<GetDataType>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置窗口没有标题栏
		setContentView(R.layout.activity_add);
		
		sqliteutils=new SQLiteUtils();
		dbHelper =sqliteutils.createDBHelper(this);
		
		name =(EditText)findViewById(R.id.add_title);
		signature =(EditText)findViewById(R.id.add_date);
		tv_date =(EditText)findViewById(R.id.add_days);
		
		IB_Ok=(ImageButton) findViewById(R.id.IB_ok);
		IB_Back=(ImageButton) findViewById(R.id.IB_back);
		
		
		
		IB_Ok.setOnClickListener(this);
		IB_Back.setOnClickListener(this);

		
		
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public GetDataType getdataType() {
		
		mydata = new GetDataType(R.drawable.p1,
				name.getText().toString(),
				signature.getText().toString(),
				tv_date.getText().toString());
		
		Toast.makeText(AddActivity.this, mydata.getName(), 
				Toast.LENGTH_SHORT).show();
		
		return mydata;
	}

	@Override
	public void onClick(View v) {
		if(v.equals(IB_Back)){
		       Intent intent =new Intent(this,MainActivity.class);
		       startActivity(intent);
		       finish();
			}else if(v.equals(IB_Ok)){
			
				dbHelper.getWritableDatabase();
				GetDataType dataType = getdataType();
//				String length =dataType.getSignature().toString();
//				if(length.length()<10){
//					Toast.makeText(AddActivity.this, "插入失败，请检查日期", Toast.LENGTH_SHORT)
//					.show();
//				}else{
				sqliteutils.insert(dbHelper, dataType);
				Toast.makeText(AddActivity.this, "插入"+getdataType().getName()+"成功", Toast.LENGTH_SHORT)
				.show();
				
				Intent intent =new Intent(this,MainActivity.class);
			       startActivity(intent);
			       finish();
			       }
//				Toast.makeText(AddActivity.this, mydata.getName(), 
//						Toast.LENGTH_SHORT).show();
//				adapter = new Myadapter(this, getdatas());
//				mylistview.setAdapter(adapter);
//			}
	}
	
}
