package com.ubaid.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ubaid.entity.User;

@Repository
public class SocialGraphDAOV2 implements GraphDAOV2
{

	@Autowired
	Session session;
	private static final String CYPHER = "Match (t:User {name:$name})<-[rel:follow]-("
			+ FOLLOWER + 
			":User)<-[:follow]-("
			+ FOLLOWER_OF_FOLLOWER + 
			":User) return "
			+ FOLLOWER + 
			","
			+ FOLLOWER_OF_FOLLOWER + ";";

	
	
	@Override
	public void addAll(User[] users) throws RuntimeException
	{
		Transaction transaction = session.beginTransaction();
		for(User user : users)
		{
			session.save(user);
		}
		transaction.commit();
		transaction.close();
	}

	@Override
	public void deleteAll() throws RuntimeException
	{
		session.deleteAll(User.class);
	}

	@Override
	public Result getFollowersOfItsFollowers(User user) throws RuntimeException
	{
		Transaction transaction = session.beginTransaction();		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", user.getName());
		Result result = session.query(CYPHER, parameters);
		transaction.commit();
		transaction.close();
		return result;
	}

}
