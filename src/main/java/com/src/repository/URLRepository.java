package com.src.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

@Repository
public class URLRepository {
	private final Jedis jedis;
	private final String idKey;
	private final String urlKey;
	private static final Logger LOGGER = LogManager.getLogger(URLRepository.class);
	
	public URLRepository() {
		this.jedis = new Jedis();
		this.idKey = "id";
		this.urlKey = "url:";
	}

	public URLRepository(Jedis jedis, String idKey, String urlKey) {
		super();
		this.jedis = jedis;
		this.idKey = idKey;
		this.urlKey = urlKey;
	}
	
	public Long incrementId() {
		long id = jedis.incr(idKey);
		return id-1;
	}
	
	public void saveURL(String key, String longURL) {
		jedis.hset(idKey, key, longURL);
		
	}

	public String getURL(Long id) throws Exception{
		String url = jedis.hget(urlKey, "url:"+id);
		if(url==null) {
			throw new Exception("URL at Key "+ id+ " does not exist");
		}
		return url;
	}
}
