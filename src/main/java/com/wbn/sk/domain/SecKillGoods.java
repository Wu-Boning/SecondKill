package com.wbn.sk.domain;

import java.util.Date;

public class SecKillGoods {
	
	private Long id;
	private Long goodsId;
	private Integer stockCount;
	private Date stratDate;
	private Date endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long idLong) {
		this.id = idLong;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodSID) {
		this.goodsId = goodSID;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStratDate() {
		return stratDate;
	}
	public void setStratDate(Date stratDate) {
		this.stratDate = stratDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "SecKillGoods [idLong=" + id + ", goodSID=" + goodsId + ", stockCount=" + stockCount + ", stratDate="
				+ stratDate + ", endDate=" + endDate + "]";
	}
	
	

}
