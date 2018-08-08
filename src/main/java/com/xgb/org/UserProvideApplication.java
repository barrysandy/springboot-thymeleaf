package com.xgb.org;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages="com.xgb.org.dao")
@EnableTransactionManagement
public class UserProvideApplication {
	

	public static void main(String[] args) {
		
		SpringApplication.run(UserProvideApplication.class, args);
		
	}
}
