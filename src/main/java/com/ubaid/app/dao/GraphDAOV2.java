package com.ubaid.app.dao;


import org.neo4j.ogm.model.Result;

import com.ubaid.entity.User;

/**
 * 
 * GraphDAO is deprecated
 * it has method allAll(User[]) to add all users and return true if add all
 * and throws exception if there is any exception
 * 
 * @author UbaidurRehman
 *
 */
public interface GraphDAOV2
{
	
	public static final String FOLLOWER = "follower";
	public static final String FOLLOWER_OF_FOLLOWER = "followerOfFollower";

	/**
	 * 
	 * @param users
	 * 
	 * this method add all users in the database 
	 * @throws RuntimeException
	 */
	public void addAll(User[] users) throws RuntimeException;
	
	/**
	 * this method will delete all nodes and their relations
	 * in the database
	 * @throws RuntimeException
	 */
	public void deleteAll() throws RuntimeException;
	
	public Result getFollowersOfItsFollowers(User user) throws RuntimeException;
}
