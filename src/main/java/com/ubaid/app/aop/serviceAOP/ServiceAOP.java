package com.ubaid.app.aop.serviceAOP;

import org.aspectj.lang.annotation.Pointcut;

public abstract class ServiceAOP
{
	
	@Pointcut("execution(* com.ubaid.app.dao.GraphDAOV2.addAll(*)) throws RuntimeException")
	protected void ingestUsersService() {}

	@Pointcut("execution(* com.ubaid.app.dao.GraphDAOV2.deleteAll(*)) throws RuntimeException")
	protected void deleteAllService() {}
	
	@Pointcut("execution(* com.ubaid.app.dao.RandomlyRalatedUserDAO.makeRendomlyRelatedUsers(int, int))")
	protected void makeRandomlyUser() {}
	
	@Pointcut("execution(* com.ubaid.app.dao.SocialGraphDAOV2.getFollowersOfItsFollowers(*)) throws RuntimeException")
	protected void getFollowersOfFollowers() {}
	
	@Pointcut("execution(* com.ubaid.app.service.GraphServiceV2Imp.getFollowersOfItsFollowers(*)) throws RuntimeException")
	protected void ffou() {}
}
