package com.weblog.context;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

public class Context
{

	private static ApplicationContext appContext;

	public static void setApplicationContext(ApplicationContext context)
    {
        appContext = context;
    }

    private static String getBareClassName(String className)
    {
        int pos = className.lastIndexOf('.');
        if (pos < 0)
            return className;
        return className.substring(pos + 1);
    }

    public static Object getBean(String className)
    {
        Object obj = appContext.getBean(getBareClassName(className));
        return obj;
    }

    public static Object getBean(Class clazz)
    {
        return getBean(clazz.getName());
    }

    public static Object getBeanByID(String id)
    {
        return appContext.getBean(id);
    }

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
