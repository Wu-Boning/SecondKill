package com.wbn.sk.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.wbn.sk.domain.OrderInfo;
import com.wbn.sk.domain.SecKillOrder;

@Mapper
public interface OrderDao {

	@Select("select * from `seckill_order` where user_id = #{userId} and id = #{goodsId}")
	public SecKillOrder getSeckillOrderByUserIdAndGoodsId(@Param("userId")Long userId,
			@Param("goodsId")long goodsId);

	@Insert("insert into `order_info`(user_id,goods_id,goods_name, goods_count, goods_price,order_channel,"
			+ "status, create_date) values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount},#{goodsPrice},"
			+ "#{orderChannel}, #{status},#{createDate})")
	@SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before =false,
	statement = "select last_insert_id()")
	public long insert(OrderInfo orderInfo);

	@Insert("insert into `seckill_order`(user_id,goods_id,order_id) values(#{userId},#{goodsId},#{orderId})")	
	public void inserSeckillOrder(SecKillOrder secKillOrder);
}
