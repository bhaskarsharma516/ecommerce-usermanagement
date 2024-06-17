package com.ecommerce.usermanagement.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public String  get(String key) {
	try {
		var res= redisTemplate.opsForValue().get(key);
		log.info("res inside"+res);
		return res;
	}catch(Exception e) {
		log.error("Exception- ",e);
		return null;
	}
	}
	
//	
//	public <T> T  get(String key, Class<T> entityClass) {
//	try {
//		var res= redisTemplate.opsForValue().get(key);
//		log.info("res inside"+res);
//		ObjectMapper mapper=new ObjectMapper();
//		return mapper.readValue(res.toString(), entityClass);
//	}catch(Exception e) {
//		log.error("Exception- ",e);
//		return null;
//	}
//	}
	
	
	public void  set(String key,Object o,Long time) {
		try {
			redisTemplate.opsForValue().set(key, o.toString(),time,TimeUnit.MINUTES);
			log.info("key "+key);
				
		}catch(Exception e) {
			log.error("Exception- ",e);
		}
		}

}
