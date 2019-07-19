package com.ubaid.app;

import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ubaid.app.config.Config;
import com.ubaid.app.simulator.Simulator;

public class App
{
	public static void main(String [] args)
	{
		
		//setting any logger disable
		LogManager.getLogManager().reset();
		Logger rootLogger = LogManager.getLogManager().getLogger("");
		rootLogger.setLevel(Level.SEVERE);
		for (Handler h : rootLogger.getHandlers())
		    h.setLevel(Level.SEVERE);
		
		
		//getting context
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(Config.class);
		
		Simulator simulator = context.getBean(Simulator.class, "simulator");

		
		Scanner input = new Scanner(System.in);
		int vertices = 0;
		int edges = 0;

		
		try
		{
			System.out.println("Please Mention Vertices for Graph: ");
			vertices = input.nextInt();
			System.out.println("Please Mention Edges for Graph");
			edges = input.nextInt();
			simulator.simulate(vertices, edges);
			
		}
		catch(Exception exp)
		{
			System.out.println("[ERROR]: Some Unknown Errors occured");
		}
		
		input.close();
		context.close();
		System.exit(0);
	}
	
}
