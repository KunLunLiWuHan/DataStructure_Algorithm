package com.xiaolun.hashtab;

//表示一个雇员
public class Emp {
	public int id;
	public String name;
	public Emp next; //next 默认为 null

	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}