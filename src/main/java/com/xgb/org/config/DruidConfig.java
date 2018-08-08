package com.xgb.org.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


/**
 * SpringBoot Druid数据源
 * <p>Title: Druid数据源配置类</p>
 * <p>Description: </p>
 *
 * @author XuGongBin
 * @version 1.0.0
 * @date 2018/7/16 16:20
 */
@Configuration
public class DruidConfig {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
 
	@Value("${spring.datasource.username}")
	private String username;
 
	@Value("${spring.datasource.password}")
	private String password;
 
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
 
	@Value("${spring.datasource.initialSize}")
	private int initialSize;
 
	@Value("${spring.datasource.minIdle}")
	private int minIdle;
 
	@Value("${spring.datasource.maxActive}")
	private int maxActive;
 
	@Value("${spring.datasource.maxWait}")
	private int maxWait;
 
	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;
 
	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;
 
	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;
 
	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;
 
	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;
 
	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;
 
	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;
 
	@Value("${spring.datasource.filters}")
	private String filters;
	
	@Value("${spring.datasource.logSlowSql}")
	private String logSlowSql;
	
	@Bean
	@Primary
	public DataSource dataSource(){
		//@Primary 注解作用是当程序选择dataSource时选择被注解的这个
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements); 
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasource;
	}
	
	  @Bean
	    public ServletRegistrationBean druidServlet() {
	        ServletRegistrationBean reg = new ServletRegistrationBean();
	        reg.setServlet(new StatViewServlet());
	        reg.addUrlMappings("/druid/*");
	        reg.addInitParameter("loginUsername", username);
	        reg.addInitParameter("loginPassword", password);
	        reg.addInitParameter("logSlowSql", logSlowSql);
	        return reg;
	    }
 
	    @Bean
	    public FilterRegistrationBean filterRegistrationBean() {
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	        filterRegistrationBean.setFilter(new WebStatFilter());
	        filterRegistrationBean.addUrlPatterns("/*");
	        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	        filterRegistrationBean.addInitParameter("profileEnable", "true");
	        return filterRegistrationBean;
	    }
}