package com.ubaid.app;

import java.net.MalformedURLException;
import java.net.URL;

import org.neo4j.ogm.session.SessionFactory;

import com.ubaid.app.classLoader.ClassLoader1;

public class GraphImp
{
	public static void main(String [] args)
	{
		try
		{
			URL url = new URL("C:\\Users\\UbaidurRehman\\.m2\\repository\\org\\neo4j\\neo4j-consistency-check\\2.3.2\\neo4j-consistency-check-2.3.2.jar");
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		
//		ClassLoader1 loader1 = new ClassLoader1();
	}
}