/**
 * 
 */
package com.orbc.syn.menumgmt.tokens.cache;

import java.util.HashMap;
import java.util.Map;

import com.orbc.syn.menumgmt.vo.TokenInfo;

/**
 * @author ntalari
 *
 */
public class CacheManager {
	
	public static Map<String, TokenInfo> cacheData=new HashMap<>();
	
	public static boolean addToCache(String accessToken,TokenInfo tokenInfo) {
		 return cacheData.put(accessToken, tokenInfo)==null;
	}
	
	public static boolean removeFromCache(String accessToken) {
		 return cacheData.remove(accessToken) != null;
	}
	
	public static TokenInfo getFromCache(String accessToken) {
		 return cacheData.get(accessToken);
	}

}
