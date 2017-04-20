package com.pwcprac.feign;


import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pwcprac.dto.AuthResult;
import com.pwcprac.dto.ResultData;
import com.pwcprac.entity.User;
import com.pwcprac.feign.fallback.UserClientFallback;

@FeignClient(name = "user-service", fallback = UserClientFallback.class)
public interface UserFeignClient {
	@RequestMapping(value = "/user/hello")
	public String hello();
	
	@RequestMapping(method = RequestMethod.POST,value = "/user/verification")
	public ResultData<User> verify(@RequestBody User user);
	
	@RequestMapping(method = RequestMethod.POST,value = "/user/saving")
	public ResultData<String> signUp(User user);
	
	@RequestMapping(value = "/user/{account}/verification",method = RequestMethod.GET)
	public ResultData<String> verifyExist(@PathVariable("account")String account);
	
	@RequestMapping(method = RequestMethod.GET,value = "/oauth2/{app_id}/request_token_url")
	public AuthResult getLoginUrl(@PathVariable("app_id")String appId,@RequestParam("app_key")String appKey,@RequestParam("callback_url")String callbackURL);
	
	@RequestMapping(method = RequestMethod.POST,value = "/oauth2/user_authorization_url")
	public String userAuthorizationUrl(@RequestParam("user_authorization_url_code")String userAuthorizationUrlCode,@RequestParam("login_url_code")String loginUrlCode);
	
	@RequestMapping(method = RequestMethod.POST,value = "/user/delete" )
	public ResultData<User> delete(@RequestBody User user);
	
	@RequestMapping(method = RequestMethod.GET,value = "/user/getAll" )
	public List<User> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "10") Integer size);
}
