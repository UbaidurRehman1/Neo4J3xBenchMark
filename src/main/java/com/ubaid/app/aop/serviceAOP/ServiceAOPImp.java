package com.ubaid.app.aop.serviceAOP;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ubaid.app.service.JSONService;
import com.ubaid.app.service.LogLoopService;
import com.ubaid.entity.User;

@Aspect
@Component
public class ServiceAOPImp extends ServiceAOP
{

	
	private long start;
	private long end;
	
	@Autowired
	private LogLoopService logLoopService;
	
	@Autowired
	private JSONService json;
	
	@Around("ingestUsersService()")
	public Object igestTimeLogging(ProceedingJoinPoint joinPoint)
	{
		Object result = null;
		
		result = logging(joinPoint, " Adding");
		
		return result;
	}

	private Object logging(ProceedingJoinPoint joinPoint, String proc) {
		Object result;
		System.out.println("[INFO]:" +  proc + " Users In the database");
		System.out.println("[INFO]: Starting Time-->" + getCurrentTime());
		logLoopService.startMessageLoop("Please Wait--- It will not take a long");
		Object[] args = joinPoint.getArgs();
		
		int size = 0;
		
		try
		{
			User[] users = (User[]) args[0];
			size = users.length;
			
		}
		catch(ClassCastException exp)
		{
			size = 1;
		}

		start = System.currentTimeMillis();
		try
		{
			result = joinPoint.proceed();
			end = System.currentTimeMillis();
			logLoopService.stopMessageLoop();
			long duration = end - start;
			System.out.println("[INFO]: Ending Time-->" + getCurrentTime());			

			if(proc.contains("Adding"))
			{
				System.out.printf("[INFO]: The total time spent for " + proc
						+ " %d Users in the database is %.6f seconds\n", size, (double) duration/ (double) 1000);				
			}
			else if(proc.contains("Querying"))
			{
				System.out.printf("[INFO]: The total time spent for " + proc
						+ " in the database is %.6f seconds\n", (double) duration/ (double) 1000);				
				
			}
			
			DecimalFormat myFormatter = new DecimalFormat("###.######");
			String output = myFormatter.format((double) duration/ (double) 1000);
			
			//writeInJSONFile
			if(proc.contains("Adding"))
			{
				json.addIngestTime(Double.parseDouble(output));
			}
			else if(proc.contains("Querying"))
			{
				json.addQueryTime(Double.parseDouble(output));
			}
			
			
		}
		catch(Throwable exp)
		{
			exp.printStackTrace();
			System.out.println("[Error]: " + exp.getMessage());
			System.out.println("[Error]: " + exp.getCause());

			logLoopService.stopMessageLoop();
			result = exp.getMessage();
		}
		return result;
	}

	private Timestamp getCurrentTime()
	{
		Date date = new Date();
		long time = date.getTime();
		return new Timestamp(time);
	}
	
	@Around("getFollowersOfFollowers()")
	public Object queryAboutFollowerOfFollowers(ProceedingJoinPoint joinPoint)
	{
		Object result = null;
		User user = (User) joinPoint.getArgs()[0];
		result = logging(joinPoint, " Querying Followers of Followers of " + user.getName() + " ");		
		System.out.println("[INFO]: Followers of Followers of given " +  user.getName());

		return result;
	}
	
	@Before("deleteAllService()")
	public void beforeDeleting()
	{
		System.out.println("[Info]: Deleting all nodes in the graph");
	}
	
	@After("deleteAllService()")
	public void afterDeleteing()
	{
		System.out.println("[Info]: All nodes in the database deleted");
	}
	

	@AfterReturning(pointcut = "ffou()", returning = "users")
	public void followers_followers_service(Map<User, List<User>> users)
	{
		try
		{
			Consumer<Entry<User, List<User>>> consumer = c ->
			{
				System.out.printf("[INFO]: %25s %-5s  %s\n", c.getKey().getName(), "----->", c.getValue());
			};

			users.entrySet().parallelStream().forEach(consumer);
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			System.out.println("[ERROR]: " + exp.getMessage());
		}
		
	}
}
