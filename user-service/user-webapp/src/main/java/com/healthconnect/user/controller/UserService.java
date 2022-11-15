package com.healthconnect.user.controller;

import com.healthconnect.user.model.common.AccessDto;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.user.AppLoginResponse;
import com.healthconnect.user.model.user.UserSignUpResponse;

public interface UserService {

    UserSignUpResponse signUp(AccessDto loginDto);

    String verifyUserEmail(String encryptedId);
    
    AppLoginResponse login(AccessDto loginDto);
    
    User getById(Long id);

}

