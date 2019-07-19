package com.ubaid.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.app.json.JSONINT;

@Service
public class JSONServiceImp implements JSONService
{

	@Autowired
	JSONINT json;
	
	@Override
	public void addQueryTime(double time) throws Exception
	{
		json.addQueryTime(time);
	}

	@Override
	public void addIngestTime(double time) throws Exception
	{
		json.addIngestTime(time);
	}

	@Override
	public double averageQueryTime() throws Exception
	{
		return json.averageQueryTime();
	}

	@Override
	public double averageIngestTime() throws Exception
	{
		return json.averageIngestTime();
	}

}
