package com.pwcprac.feign.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pwcprac.dto.AuthResult;
import com.pwcprac.dto.ResultData;
import com.pwcprac.entity.User;
import com.pwcprac.feign.UserFeignClient;
@Component
public class UserClientFallback implements UserFeignClient{

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public AuthResult getLoginUrl(String appId, String appKey, String callbackURL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userAuthorizationUrl(String userAuthorizationUrlCode, String loginUrlCode) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResultData<User> verify(User user) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResultData<String> signUp(User user) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResultData<String> verifyExist(String account) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public ResultData<User> delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<User> getAll(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

}
