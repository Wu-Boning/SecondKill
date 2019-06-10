package com.wbn.sk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbn.sk.dao.GoodsDao;
import com.wbn.sk.domain.Goods;
import com.wbn.sk.domain.SecKillGoods;
/**
 * 	商品服务器
 * @author WuBN
 *
 */
import com.wbn.sk.vo.GoodsVo;
@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;
	
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}

	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	/**
	 * 	减少库存
	 * @param goods
	 */
	public void reduceStock(GoodsVo goods) {
		SecKillGoods g = new SecKillGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
		
	}
}
