package com.weblog.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.weblog.context.Context;

public class ContextAware implements ApplicationContextAware {
	private ApplicationContext mycontext;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		mycontext = arg0;
	}
	public void setToContext()
	{
		Context.setApplicationContext(mycontext);
	}
}
