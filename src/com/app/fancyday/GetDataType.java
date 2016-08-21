package com.app.fancyday;

import java.util.ArrayList;
import java.util.List;

/**
 * 事项bean
 * @author ZacharyJoke
 *
 */
public class GetDataType {
private int iv_portrait;
private String name;
private String signature;
private String tv_date;
private List<GetDataType> map = new ArrayList<GetDataType>();



public GetDataType(int iv_portrait, String name, String signature,
		String tv_date) {
	super();
	this.iv_portrait = iv_portrait;
	this.name = name;
	this.signature = signature;
	this.tv_date = tv_date;
}



public GetDataType() {
	super();
}



public int getIv_portrait() {
	return iv_portrait;
}
public void setIv_portrait(int iv_portrait) {
	this.iv_portrait = iv_portrait;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSignature() {
	return signature;
}
public String getTv_date() {
	return tv_date;
}
public void setSignature(String signature) {
	this.signature = signature;
}


public void setTv_date(String tv_date) {
	this.tv_date = tv_date;
}



public List<GetDataType> getMap() {
	return map;
}



public void setMap(List<GetDataType> map) {
	this.map = map;
	
}
public void showmap() {
	for(int i = 0;i<map.size();i++)
	System.out.println(map.get(i));
     
}


}
