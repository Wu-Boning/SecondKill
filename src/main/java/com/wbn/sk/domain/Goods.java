package com.wbn.sk.domain;

public class Goods {
	
	private Long id;
	private String goodsName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;
	public Long getId() {
		return id;
	}
	public void setId(Long idLong) {
		this.id = idLong;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsTitle() {
		return goodsTitle;
	}
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsDetail() {
		return goodsDetail;
	}
	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(Integer goodStock) {
		this.goodsStock = goodStock;
	}
	@Override
	public String toString() {
		return "Goods [idLong=" + id + ", goodsName=" + goodsName + ", goodsTitle=" + goodsTitle + ", goodsImg="
				+ goodsImg + ", goodsDetail=" + goodsDetail + ", goodsPrice=" + goodsPrice + ", goodStock=" + goodsStock
				+ "]";
	}
	
	

}
