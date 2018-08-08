package com.xgb.org.service;

import java.util.List;

import com.xgb.org.domain.UserTemp;

public interface UserTempService {

	int saveService(UserTemp bean) throws Exception;
	
	int updateService(UserTemp bean) throws Exception;
	
	int deleteByIdService(String id) throws Exception;
	
	UserTemp getBeanByIdService(String id) throws Exception;
	
	UserTemp getBeanByNameAndPassword(String name,String password) throws Exception;
	
	List<UserTemp> getListService(int index,int pageSize,String search) throws Exception;
	
	int getCountService(String search) throws Exception;
}
