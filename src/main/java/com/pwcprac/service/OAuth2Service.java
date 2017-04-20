package com.pwcprac.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwcprac.dto.AuthResult;
import com.pwcprac.feign.UserFeignClient;
import com.pwcprac.util.AuthCache;
/**
 * Console (Third Party APP)
 * port:7788
 * @author axia021
 *
 */
@RestController
@RequestMapping(value = "/oauth2")
public class OAuth2Service {
	//TODO get appkey
	private String APPKEY = "1234";
	//TODO generate callback url
	private String CALLBACKURL = "http://localhost:7788/oauth2/code_verification";
	@Autowired
	UserFeignClient authServiceClient;
	@Autowired
	private AuthCache authCache;
	private static final Logger logger = LogManager.getLogger(OAuth2Service.class);
	
	@GetMapping(value = "/{app_id}/login_page")
	public AuthResult getAuthLoginUrl(@PathVariable("app_id")String appId,HttpServletRequest req,HttpServletResponse res){
		AuthResult result = authServiceClient.getLoginUrl(appId, APPKEY, CALLBACKURL);
		if(result.isResult()){
			String url = result.getUrl();
			int index = url.indexOf("code");
			String code = url.substring(index+5);
			authCache.getAuthCache().put(req.getSession().getId(), Integer.parseInt(code));
			return result;
		}else{
			res.setStatus(404);
			result.setDetail("illegal app_id");
			return result;
		}
        
	}
	
	@GetMapping(value = "/code_verification")
	//@PostMapping(value = "/code_verification")
	public AuthResult loginResult(@RequestParam("code")String code,HttpServletRequest req){
		AuthResult result = new AuthResult();
		Map<String, Integer> authMap = authCache.getAuthCache();	
		if(!authMap.containsKey(req.getSession().getId())){
			result.setResult(false);
			result.setDetail("Time out.");
			return result;
		}
		Integer loginUrlCode = authMap.get(req.getSession().getId());
		String accessToken = authServiceClient.userAuthorizationUrl(code, loginUrlCode+"");
		logger.info("got the access token result : "+accessToken);
		authCache.getAuthCache().remove(req.getSession().getId());
		if(accessToken.equals("accessToken")){
			//TODO save accessToken
			result.setResult(true);
			return result;
		}else{
			result.setResult(false);
			return result;
		}
		
	}

}
