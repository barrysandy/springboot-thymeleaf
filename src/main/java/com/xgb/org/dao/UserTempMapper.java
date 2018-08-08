package com.xgb.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xgb.org.domain.UserTemp;

@Repository
public interface UserTempMapper {

	int save(UserTemp bean);
	
	int update(UserTemp bean);
	
	int deleteById(@Param("id") String id);
	
//	@Select("select id,name,password,descM from t_user_temp where id = #{id}")
	UserTemp getBeanById(@Param("id") String id);
	
	@Select("select id,name,password,descM from t_user_temp where name = #{name} and password = #{password}")
	UserTemp getBeanByNameAndPassword(@Param("name") String name,@Param("password") String password);
	
	List<UserTemp> getList(@Param("search") String search);
	
	int getCount(@Param("search") String search);
}
