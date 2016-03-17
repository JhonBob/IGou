package com.bob.igou.bean;

public class GoodsData {
	public GoodsData(String goodsName) {
		// TODO Auto-generated constructor stub
		this.goodsName = goodsName;
	}
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "GoodsData [goodsName=" + goodsName + "]";
	}

	String goodsName;
}
