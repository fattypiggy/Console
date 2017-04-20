package com.pwcprac.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwcprac.dto.ResultData;
import com.pwcprac.entity.User;
import com.pwcprac.feign.UserFeignClient;

@CrossOrigin(origins="*")
@RestController
public class ConsoleService {
	@Autowired
	UserFeignClient userFeignClient;
	
	//is account already exist
	@GetMapping(value = "/{account}/verification",produces = "application/json; charset=utf-8")
	public ResultData<String> isAccountExist(@PathVariable("account")String account) {
		ResultData<String> resultData = new ResultData<>();
		resultData = userFeignClient.verifyExist(account);
		return resultData;
	}
	
	//verify password and account
	@PostMapping(value = "/verification",produces = "application/json; charset=utf-8")
	public ResultData<User> login(@RequestBody User user) {
		ResultData<User> result = userFeignClient.verify(user);
		return result;
	}
	
	@PostMapping(value = "/signup",produces = "application/json; charset=utf-8")
	public ResultData<String> signUp(@RequestBody User user) {
		ResultData<String> result = userFeignClient.signUp(user);
		return result;
	}
	
	@PostMapping(value = "/delete",produces = "application/json; charset=utf-8")
	public ResultData<User> delete(@RequestBody User user){
		ResultData<User> result = userFeignClient.delete(user);
		return result;
	}
	
	@GetMapping(value = "/getAll",produces = "application/json; charset=utf-8")
	public List<User> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<User> resultData = userFeignClient.getAll(page, size);
		return resultData;
	}
	
	public String isExistFallback(String account){
		return "...I don't know...";
	}
}
