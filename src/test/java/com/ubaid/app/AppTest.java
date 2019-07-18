package com.ubaid.app;


import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ubaid.app.config.Config;
import com.ubaid.app.service.GraphServiceV2;
import com.ubaid.app.service.RandomlyRelatedUserService;
import com.ubaid.entity.User;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class AppTest
{
	
	protected RandomlyRelatedUserService userService;
	protected GraphServiceV2 graphService;
	AnnotationConfigApplicationContext context;
		
	@Order(-10)
	@Test
	void createContext()
	{
		try
		{
			context = 
					new AnnotationConfigApplicationContext(Config.class);				
		}
		catch(Exception exp)
		{
			fail("Test # 1 failed", exp);
		}
	}
	
	@Order(-9)
	@Test
	void createUserService()
	{
		try
		{
			userService = context.getBean("randomlyRelatedUserServiceImp", RandomlyRelatedUserService.class);			
		}
		catch (Exception e)
		{
			fail("Test # 2 failed", e);
		}
	}
	
	@Order(-8)
	@Test
	void createGraphService()
	{
		try
		{
			graphService = context.getBean("graphServiceV2Imp", GraphServiceV2.class);			
		}
		catch (Exception e)
		{
			fail("Test # 3 failed", e);
		}

		
	}
	
	@Order(-7)
	@Test
	void deleteAll()
	{
		try
		{
			graphService.deleteAll();
			
		}
		catch (Exception e)
		{
			fail("Test # 4 failed", e);
		}

		
	}
	
	@Order(-6)
	@Test
	void testGraph()
	{

		try
		{
			User[] users = userService.makeRandomlyRelatedUsers(30, 30);
			graphService.addAll(users);
			graphService.deleteAll();
			
		}
		catch (Exception e)
		{
			fail("Test # 5 failed ", e);
		}

	}
	
	

}
