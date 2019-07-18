package com.ubaid.app.service;

import java.util.List;
import java.util.Map;

import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubaid.app.builder.FollowerOfFollowerBuilder;
import com.ubaid.app.dao.GraphDAOV2;
import com.ubaid.entity.User;

@Service
public class GraphServiceV2Imp implements GraphServiceV2
{
	
	@Autowired
	GraphDAOV2 graphDAOV2;
	
	@Autowired
	FollowerOfFollowerBuilder builder;


	@Override
	public void addAll(User[] users) throws RuntimeException
	{
		graphDAOV2.addAll(users);
	}

	@Override
	public void deleteAll() throws RuntimeException
	{
		graphDAOV2.deleteAll();
	}

	@Override
	public Map<User, List<User>> getFollowersOfItsFollowers(User user) throws RuntimeException
	{
		Result result = graphDAOV2.getFollowersOfItsFollowers(user);
		Map<User, List<User>> users = builder.build(result);
		return users;
	}

}
