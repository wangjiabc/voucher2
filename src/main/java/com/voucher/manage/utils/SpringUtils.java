package com.voucher.manage.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * @author lz
 * @description 关于sping的一些工具
 * @date 2019/5/10
 */
@Component
public class SpringUtils implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        SpringUtils.beanFactory = factory;
    }

    /**
     * 实例方法
     * 使用的时候先通过getInstance方法获取实例
     *
     * @param beanId
     * @return
     */
    public static Object getBeanById(String beanId) {
        if (null != beanFactory) {
            return beanFactory.getBean(beanId);
        }
        return null;
    }

    /**
     * @Author lz
     * @Description: 根据条件回滚当前事务
     * @param: [condition]
     * @return: {void}
     * @Date: 2019/5/10 9:45
     **/
    public static final void setRollbackOnly(boolean condition) {
        if (condition) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    public static ServletContext getServletContext() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        return webApplicationContext.getServletContext();
    }

    public static String getProjectRealPath() {
        return getServletContext().getRealPath(File.separator);
    }

}
