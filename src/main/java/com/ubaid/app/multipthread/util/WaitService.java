package com.ubaid.app.multipthread.util;

import org.springframework.stereotype.Service;

@Service
public class WaitService implements Runnable
{
	
	private String message;
	private boolean loop;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public void run()
	{
		loop = true;
		
		while(loop)
		{
			System.out.println("[INFO]: " + message + "");
			
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				loop = false;
			}
		}
	}
	
	public void stop()
	{
		loop = false;
	}
	
}
