package com.xgb.org.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xgb.org.common.StringUtils;
import com.xgb.org.domain.UserTemp;
import com.xgb.org.service.UserTempService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUtilUser {
	@Autowired
	UserTempService userService;
	
	private static String ID = StringUtils.getUUID();
	
	@Test
	public void save() {
		try {
			UserTemp bean = new UserTemp(ID,"小小","123456","描述信息");
			int result = userService.saveService(bean);
			System.out.println("******************** save result: " + result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getBean() {
		try {
			UserTemp bean = userService.getBeanByIdService("dea13dce804c4c0882c8d60f0b26c5c4");
			System.out.println("******************** get bean: " + bean);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getList() {
		try {
			List<UserTemp> list = userService.getListService(0, 10,"");
			int count = userService.getCountService("");
			System.out.println("******************** list: " + list);
			System.out.println("******************** count: " + count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void del() {
		try {
			int result = userService.deleteByIdService("468218145284402a981b89788248e522");
			System.out.println("******************** del result: " + result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
