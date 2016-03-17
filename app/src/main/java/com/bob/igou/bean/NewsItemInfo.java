package com.bob.igou.bean;

import java.util.ArrayList;

//商品详情
public class NewsItemInfo{
	public String title;
	public String str;
	public int price;
	public ArrayList<String> pic;
	public int oldPrice;
	public int bought;
	public NewsItemInfo(String title, String str, int price,
			ArrayList<String> pic, int oldPrice,int bought) {
		super();
		this.title = title;
		this.str = str;
		this.price = price;
		this.pic = pic;
		this.oldPrice = oldPrice;
		this.bought = bought;
	}
	@Override
	public String toString() {
		return "NewsItemInfo [title=" + title + ", str=" + str + ", price="
				+ price + ", pic=" + pic + ", oldPrice=" + oldPrice
				+ ", bought=" + bought + "]";
	}
	
	
}
