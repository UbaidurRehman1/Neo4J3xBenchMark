package com.ubaid.app.json;

public interface JSONINT
{
	public final static String INGEST_TIME = "INGEST_TIME";
	public final static String QUERY_TIME = "QUERY_TIME";
		
	public void addQueryTime(double time) throws Exception;
	public void addIngestTime(double time) throws Exception;
	public double averageQueryTime() throws Exception;
	public double averageIngestTime() throws Exception;
}
