package com.ubaid.app.config;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"com.ubaid.app"})
@EnableAspectJAutoProxy
public class Config
{
	@Bean("locale")
	public Locale getPakLocale()
	{
		return new Locale("en-PAK");
	}
	
	@Bean("random")
	@Scope("prototype")
	public Random random()
	{
		return new Random();
	}
	
	@Bean("currentDir")
	public String currentDir()
	{
		return System.getProperty("user.dir");
	}
	
	@Bean("jsonFile")
	public File getJSONFile()
	{		
		File folder = new File(currentDir() + "/json");
		folder.mkdir();
		File jsonFile = new File(currentDir() + "/json/stat.json");
		try
		{
			if(jsonFile.exists())
			{
				jsonFile.delete();
			}
			jsonFile.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return jsonFile;
	}
	
}
