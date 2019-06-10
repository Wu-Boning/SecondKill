package com.wbn.sk.vo;

import java.util.Date;

import com.wbn.sk.domain.Goods;

public class GoodsVo extends Goods{

	private Double seckillPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date stratDate) {
		this.startDate = stratDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getSeckillPrice() {
		return seckillPrice;
	}
	public void setSeckillPrice(Double seckillPrice) {
		this.seckillPrice = seckillPrice;
	}
	@Override
	public String toString() {
		return "GoodsVo [seckillPrice=" + seckillPrice + ", stockCount=" + stockCount + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	
	
}
