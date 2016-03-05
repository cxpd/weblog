package com.weblog.context;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 系统环境管理类
 * @author pengdan
 * @created Jul 28, 2012 9:46:04 AM
 */
public class Context implements ApplicationContextAware
{

    private static ApplicationContext appContext;

    /**
     * 向Context中添加Spring的ApplicationContext 从而使得Context可以访问Spring的Bean Factory
     * <BR>
     * 这个方法由系统自动调用，请勿手工调用.
     * 
     * <BR>
     * setApplicationContext,getBean(Class),getBean(String),getBeanByID(String)
     * 四个方法在使用时，需要依赖Spring包
     * 
     * @param context
     */
    public void setApplicationContext(ApplicationContext context)
            throws BeansException
    {
        appContext = context;
    }

    /**
     * 从一个完整的类名中取出去掉包前缀的纯类名
     * 如：
     * 若传入的是com.yuhua.stat.context.Context
     * 则返回Context
     * @param className
     * @return
     */
    private static String getBareClassName(String className)
    {
        int pos = className.lastIndexOf('.');
        if (pos < 0)
            return className;
        return className.substring(pos + 1);
    }

    /**
     * 按类名取Spring中配置的Bean
     * <BR>对传入的类名参数，本方法取出其实际类名（不含包名），
     * <BR>然后把这个类名作为Spring中的bean id，进行对象查找
     * <BR>所以注意：可以用本方法调用的Bean在Spring配置时，id必须与类名完全相同
     * <BR>如：
     * <BR><bean id="BaseInfoController" class="com.yuhua.baseinfo.controller.BaseInfoController" />
     * <BR>调用方式为：
     * <BR>Context.getBean("BaseInfoController");
     * <BR>或者
     * <BR>Context.getBean("com.yuhua.baseinfo.controller.BaseInfoController");
     * @param className 类名
     * @return
     */
    public static Object getBean(String className)
    {
        Object obj = appContext.getBean(getBareClassName(className));
        return obj;
    }

    /**
     * 按类取Spring中配置的Bean
     * <BR>对传入的Class参数，本方法取出其实际类名（不含包名）
     * <BR>然后把这个类名作为Spring中的bean id，进行对象查找
     * <BR>所以注意：可以用本方法调用的Bean在Spring配置时，id必须与类名完全相同
     * <BR>如：
     * <BR><bean id="BaseInfoController" class="com.yuhua.baseinfo.controller.BaseInfoController" />
     * <BR>调用方式为：
     * <BR>Context.getBean(BaseInfoController.class);
     * @param clazz 类
     * @return
     */
    public static Object getBean(Class clazz)
    {
        return getBean(clazz.getName());
    }

    /**
     * 按ID取Spring中配置的Bean
     * <BR>如：
     * <BR><bean id="BaseInfoController" class="com.yuhua.baseinfo.controller.BaseInfoController" />
     * <BR>调用方式为：
     * <BR>Context.getBeanByID("BaseInfoController");
     * @param id
     * @return
     */
    public static Object getBeanByID(String id)
    {
        return appContext.getBean(id);
    }

    /**
     * 根据数据源名得到连接，若找不到该数据源则返回空
     * 
     * @param strDataSource
     * @return Connection 若找不到该数据源则返回空
     * @throws Exception
     */
    public static Connection getConnection(String strDataSource)
            throws Exception
    {
        try
        {
            DataSource ds = getDataSource(strDataSource);
            if (ds != null)
                return ds.getConnection();
            else
                return null;
        }
        catch (SQLException e)
        {
            throw new Exception("[Context.getConnection]", e);
        }
    }

    /**
     * 内部方法 按数据源名取数据源
     * 
     * @param strDataSource
     *            数据源名
     * @return
     * @throws Exception
     */
    private static DataSource getDataSource(String strDataSource)
            throws Exception
    {
        try
        {
        	javax.naming.Context initContext = new javax.naming.InitialContext();
            return (javax.sql.DataSource) initContext.lookup(strDataSource);
        }
        catch (NamingException e)
        {
            throw new Exception("[Context.getDataSource]", e);
        }
    }

}
