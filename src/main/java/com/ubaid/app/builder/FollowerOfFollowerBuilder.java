package com.ubaid.app.builder;

import java.util.List;
import java.util.Map;

import org.neo4j.ogm.model.Result;

import com.ubaid.entity.User;

public interface FollowerOfFollowerBuilder
{
	public Map<User, List<User>> build(Result result) throws RuntimeException;
}
