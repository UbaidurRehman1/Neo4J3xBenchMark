package com.ubaid.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ubaid.app.config.Config;
import com.ubaid.app.json.JSONINT;

@TestInstance(Lifecycle.PER_CLASS)
class JSONTest
{
	
	protected AnnotationConfigApplicationContext context; 
	
	@BeforeAll
	public void init()
	{
		context = new AnnotationConfigApplicationContext(Config.class);		
	}
	
	@Test
	public void test1()
	{
		try
		{
			JSONINT json = context.getBean(JSONINT.class, "JSONImp");

			json.addIngestTime(10.32);
			json.addIngestTime(13.42);
			json.addIngestTime(14.641);
			
			assertEquals((10.32 + 13.42 + 14.641) / 3, json.averageIngestTime());
			
		}
		catch(Exception e)
		{
			fail(e);
		}
	}
	
	@Test
	public void test2()
	{
		try
		{
			JSONINT json = context.getBean(JSONINT.class, "JSONImp");
			
			double D1 = 10.3254234;
			double D2 = 10.4329134;
			double D3 = 9.3283299;
			
			json.addQueryTime(D1);
			json.addQueryTime(D2);
			json.addQueryTime(D3);
			
			assertEquals((D1 + D2 + D3) / 3, json.averageQueryTime());
		}
		catch (Exception e)
		{
			fail(e);
		}
		
	}
	
}
