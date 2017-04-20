package com.pwcprac.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

@Component
public class AuthCache {
	//key : sessionId value : loginUrlCode
	private Map<String, Integer> map;
	Timer timer = new Timer();
	long timeout = 10000; // milliseconds
	public void addEntryWithTimeouts(String sessionId,Integer loginUrlCode){
		map.put(sessionId, loginUrlCode);
		timer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	            actionAfterTimeout(sessionId);
	        }
	    }, timeout);
	}
	public AuthCache(){
		map = new HashMap<String, Integer>();
	}
	
	public Map<String, Integer> getAuthCache() {
		return map;
	}

	void actionAfterTimeout(String key) {
	    map.remove(key);
	}
}
