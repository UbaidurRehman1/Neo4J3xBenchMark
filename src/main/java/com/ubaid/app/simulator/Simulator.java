package com.ubaid.app.simulator;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ubaid.app.service.GraphServiceV2;
import com.ubaid.app.service.JSONService;
import com.ubaid.app.service.RandomlyRelatedUserService;
import com.ubaid.entity.User;

@Component
public class Simulator
{
	private static final int COUNT = 10;

	@Autowired
	private Random random;
	
	@Autowired
	private JSONService json;
	
	@Autowired
	private GraphServiceV2 gS;
	
	@Autowired
	private RandomlyRelatedUserService tmp1;
	
	public void simulate(int vertices, int edges)
	{
				
		for(int i = 0; i < COUNT; i++)
		{
			User[] users = tmp1.makeRandomlyRelatedUsers(vertices, edges);
			gS.deleteAll();
			gS.addAll(users);
			gS.getFollowersOfItsFollowers(users[random.nextInt(vertices)]);
		}

		try
		{
			
			System.out.printf("The average Ingesting Time for %d users and %d relations is %0.5f seconds",
					vertices, edges, json.averageIngestTime());

			System.out.printf("The average Querying for Followers of Followers"
					+ " of 1 random %s from %s users and %s relations is %0.5f seconds",
					vertices, edges, json.averageQueryTime());
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
}
