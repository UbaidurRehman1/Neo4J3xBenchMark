package com.ubaid.app.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;



@Repository
public class JSONImp implements JSONINT
{
	@Autowired
	@Qualifier("jsonFile")
	File file;
	
	JSONParser parser;
	
	public JSONImp() throws Exception
	{
		parser = new JSONParser();
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void doFlush() throws Exception
	{
		JSONObject upper = new JSONObject();
		JSONArray ingest = new JSONArray();
		JSONArray query = new JSONArray();
		upper.put(INGEST_TIME, ingest);
		upper.put(QUERY_TIME, query);
		flush(upper);			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addQueryTime(double time) throws Exception
	{
		JSONArray ingestArray = getQueryArray();
		ingestArray.add(time);
		JSONObject obj = getObject();
		obj.put(QUERY_TIME, ingestArray);
		flush(obj);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addIngestTime(double time) throws Exception
	{
		JSONArray ingestArray = getIngestArray();
		ingestArray.add(time);
		JSONObject obj = getObject();
		obj.put(INGEST_TIME, ingestArray);
		flush(obj);		
	}

	@Override
	public double averageQueryTime() throws Exception
	{
		JSONArray arr = getQueryArray();

		return calculate(arr);
	}


	private double calculate(JSONArray arr) {
		double sum = 0;
		
		for(int i = 0; i < arr.size(); i++)
		{
			sum = sum + (Double) arr.get(i);
		}
		
		return sum / arr.size();
	}
	
	@Override
	public double averageIngestTime() throws Exception
	{
		return calculate(getIngestArray());
	}
	
	public JSONArray getQueryArray() throws Exception
	{
		JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
		JSONArray query = (JSONArray) obj.get(QUERY_TIME);
		return query;
	}

	public JSONArray getIngestArray() throws Exception
	{
		JSONObject obj = getObject();
		JSONArray ingest = (JSONArray) obj.get(INGEST_TIME);
		return ingest;
	}

	
	
	public JSONObject getObject() throws Exception
	{
		JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
		return obj;
	}
	
	public void flush(JSONObject obj) throws Exception
	{
		FileWriter writer = new FileWriter(file);
		writer.write(obj.toJSONString());
		writer.flush();
		writer.close();
	}

}
