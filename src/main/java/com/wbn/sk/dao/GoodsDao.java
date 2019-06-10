package com.wbn.sk.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wbn.sk.domain.Goods;
import com.wbn.sk.domain.SecKillGoods;
import com.wbn.sk.vo.GoodsVo;

@Mapper
public interface GoodsDao {
	
	@Select("select g.*, mg.stock_count, mg.start_date, mg.end_date, mg.seckill_price from `seckill_goods` mg left join `goods` g on mg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();

	@Select("select g.*, mg.stock_count, mg.start_date, mg.end_date, mg.seckill_price from `seckill_goods` mg left join `goods` g on mg.goods_id = g.id "
			+ "where g.id = #{id}")
	public GoodsVo getGoodsVoByGoodsId(@Param("id")long goodsId);

	@Update("update `seckill_goods` set stock_count = stock_count-1 where goods_id = #{goodsId}")
	public void reduceStock(SecKillGoods g);

}
