package com.ubaid.app.service;

public interface JSONService
{
	public void addQueryTime(double time) throws Exception;
	public void addIngestTime(double time) throws Exception;
	public double averageQueryTime() throws Exception;
	public double averageIngestTime() throws Exception;

}
