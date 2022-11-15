package com.healthconnect.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthconnect.user.model.common.AccessDto;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.user.AppLoginResponse;
import com.healthconnect.user.model.user.UserSignUpResponse;
import com.healthconnect.user.model.util.ApiResponse;

//@Api(value="User APIs", description="Operations pertaining to user")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserSignUpResponse> signUp(@RequestBody @Valid AccessDto loginDto){
        UserSignUpResponse response = userService.signUp(loginDto);
        return new ApiResponse(HttpStatus.OK, "Email verification link sent to your email.", response);
    }
    
	
    @PostMapping("/login") 
    public ApiResponse<AppLoginResponse> login(@RequestBody @Valid AccessDto loginDto) { 
    	AppLoginResponse response = userService.login(loginDto); 
    	return new ApiResponse(HttpStatus.OK, "success", response); 
    }
    
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("id") String id) {
        if(id == null) {
            return "Invalid URL. Please contact admin.";
        }
        id = id.replace(" ", "+");
        return userService.verifyUserEmail(id);
    }
    
    @GetMapping("/{id}")
    public ApiResponse<User> getById(@PathVariable String id) {
    	long userId = Long.parseLong(id);
    	return new ApiResponse(HttpStatus.OK, "success", userService.getById(userId));       
    }


}
