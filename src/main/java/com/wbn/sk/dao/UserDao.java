package com.wbn.sk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wbn.sk.domain.User;

@Mapper
public interface UserDao {
	
	@Select("select * from secondkill_user where id = #{id}")
	public User getByID(@Param("id")long id);

}
