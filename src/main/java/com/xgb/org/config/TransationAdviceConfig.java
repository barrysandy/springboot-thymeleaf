package com.xgb.org.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * SpringBoot 事务配置AOP切面类
 * <p>Title: 事务配置AOP切面类</p>
 * <p>Description: </p>
 *	第一种方式使用注解：
 *		第一步在启动类添加注解开启事务支持（@EnableTransactionManagement），
 *  	第二步在实现接口添加事务注解（@Transactional()[类级别配置表示对其下所有方法都进行配置事务]，@Transactional(readOnly=true)[方法级别配置表示本方法进行配置事务]）
 *  
 *  第二种方式使用AOP全局事务配置切面（参考切面类com.xgb.org.config.TransationAdviceConfig）
 *  	参考网页：https://segmentfault.com/q/1010000015095590/a-1020000015097236
 *  	
 * @author XuGongBin
 * @author XuGongBin
 * @version 1.0.0
 * @date 2018/7/16 16:20
 */
@Aspect
@Configuration
public class TransationAdviceConfig {

	/**
	 * 事务配置的切点
	 */
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.xgb.org.service..*.*(..))";
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public TransactionInterceptor txAdvice() {
		DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);

        return new TransactionInterceptor(transactionManager, source);
	}
	
	
	@Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
