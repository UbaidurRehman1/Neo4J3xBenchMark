package com.ubaid.app.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neo4j.ogm.model.Result;
import org.springframework.stereotype.Component;

import com.ubaid.app.dao.GraphDAOV2;
import com.ubaid.entity.User;

@Component
public class FollowerOfFollowerBuilderImp implements FollowerOfFollowerBuilder
{
	@Override
	public Map<User, List<User>> build(Result result) throws RuntimeException
	{
		Iterable<Map<String, Object>> iterable = result.queryResults();
		//get all followers
		Set<User> uniqueFollowers = new HashSet<User>();

		for(Map<String, Object> map : iterable)
		{
			uniqueFollowers.add((User) map.get(GraphDAOV2.FOLLOWER));
		}

		Map<User, List<User>> r = new HashMap<User, List<User>>();
		
		for(User user : uniqueFollowers)
		{
			List<User> users = new LinkedList<User>();

			for(Map<String, Object> map : iterable)
			{
				if(((User) map.get(GraphDAOV2.FOLLOWER)).equals(user))
				{
					users.add((User) map.get(GraphDAOV2.FOLLOWER_OF_FOLLOWER));
				}
				
			}
			
			r.put(user, users);
		}
				
		return r;
	}
}
