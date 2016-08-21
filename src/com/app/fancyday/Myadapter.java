package com.app.fancyday;

import java.util.ArrayList;
import java.util.List;

import com.example.fancydaysforandroid.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Myadapter继承BaseAdapter，重写getview和以及使用viewholder优化listview性能
 * @author ZacharyJoke
 *
 */
public class Myadapter extends BaseAdapter {
	private List<GetDataType> mData;
	private Context mcontext;
	private int mid;
	// List<GetDataType> map = new ArrayList<GetDataType>();
	private SQLiteUtils sqliteutils;
	private DatabaseHelper dbHelper;
	private ViewHolder viewHolder;

	// private ImageView iv_portrait;
	// private TextView txt_name;
	// private TextView signature;
	// private TextView tv_date;
	public Myadapter(Context context, List data) {
		this.mcontext = context;
		this.mData = data;
	}

	// 获取列表列的数量
	@Override
	public int getCount() {
		return mData.size();
	}

	// 根据postion获取item的数据
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	// 获取position对应的id
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=View.inflate(mcontext, R.layout.tv_layout, null);
		//获取item对应的数据对象
		GetDataType getdata=mData.get(position);
		
		//初始化view
		ImageView iv_portrait = (ImageView) view
				.findViewById(R.id.iv_portrait);
		TextView txt_name = (TextView) view
				.findViewById(R.id.txt_name);
		TextView signature = (TextView) view
				.findViewById(R.id.txt_signature);
		TextView tv_date = (TextView) view
				.findViewById(R.id.txt_tv_date);
		//邦定数据到view
		iv_portrait.setImageResource(R.drawable.p1);
		txt_name.setText(getdata.getName());
		signature.setText(getdata.getSignature());
		tv_date.setText(getdata.getTv_date());
		return view;
	}
	// 创建item的视图
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		View view;
//		viewHolder = new ViewHolder();
//		if (convertView == null) {
//			view = View.inflate(mcontext, R.layout.tv_layout, null);
//			viewHolder.iv_portrait = (ImageView) view
//					.findViewById(R.id.iv_portrait);
//			viewHolder.txt_name = (TextView) view.findViewById(R.id.txt_name);
//			viewHolder.signature = (TextView) view
//					.findViewById(R.id.txt_signature);
//			viewHolder.tv_date = (TextView) view.findViewById(R.id.txt_tv_date);
//			
//			view.setTag(viewHolder);
//			//获取item对应的数据对象
//			
//		} else {
//			view = convertView;
//			viewHolder = (ViewHolder) view.getTag();
//		}
//		for(int i=0;i<getCount();i++){
//			GetDataType getdata=mData.get(i);
//			viewHolder.iv_portrait.setImageResource(R.drawable.p1);
//			viewHolder.txt_name.setText(getdata.getName());
//			viewHolder.signature.setText(getdata.getSignature());
//			viewHolder.tv_date.setText(getdata.getTv_date());
//		}
//		return view;
//	}

	class ViewHolder {

		ImageView iv_portrait;
		TextView txt_name;
		TextView signature;
		TextView tv_date;

	}
}
