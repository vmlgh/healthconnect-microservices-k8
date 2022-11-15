package com.healthconnect.user.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthconnect.user.common.ApiException;
import com.healthconnect.user.model.common.AccessDto;
import com.healthconnect.user.model.core.Role;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.RoleType;
import com.healthconnect.user.model.enums.UserStatus;
import com.healthconnect.user.model.enums.UserType;
import com.healthconnect.user.model.user.AppLoginResponse;
import com.healthconnect.user.model.user.UserSignUpResponse;
import com.healthconnect.user.model.util.CryptoUtility;
import com.healthconnect.user.model.util.HealthConnectUtility;
import com.healthconnect.user.model.util.StringUtil;
import com.healthconnect.user.repository.RoleRepository;

@Transactional
@Service
public class UserServiceImpl extends BaseUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/*
	 * @Autowired private PlatformRepository platformRepository;
	 */
    
    @Autowired
    protected RoleRepository roleRepository;

    public static final long LOGIN_MAX_ATTEMPT = 15;
    
    @Override
    public UserSignUpResponse signUp(AccessDto accessDto) {
    	boolean isEmail = StringUtil.checkValidEmail(accessDto.getEmail());
    	User user = getUserByEmailOrMobileNumber(accessDto ,isEmail);
        validateIfSignUpAllowed(user);
        if(!isEmail) {
            //otpService.verifyOtp(accessDto.getEmail(), accessDto.getOtp(), OtpType.SignUp);
        	throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Please provide a valid email");
        }
        user = new User();
        if(isEmail) 
            user.setEmail(accessDto.getEmail());
        else 
        	user.setMobileNumber(accessDto.getEmail());
        user.setUsername(accessDto.getEmail());
        user.setPassword(BCrypt.hashpw(accessDto.getPassword(), BCrypt.gensalt()));
        user.setUserType(accessDto.getUserType());
        user.setEmailVerified(false);
        user.setProfileImageUrl(environment.getProperty("default.profile.image.url"));
        user.setMobileVerified(!isEmail);
        user.setStatus(UserStatus.PROFILE_INCOMPLETE);
        user.setRoles(getRoleByService(accessDto.getUserType()));
        user.setCreatedOn(LocalDateTime.now());
        user.setAttempt(0);
        user = userRepository.save(user);
        user.setUserId(HealthConnectUtility.generateUserId(user.getRecordId(), getCharByService(user.getUserType())));

		/*
		 * if(isEmail) { jobService.addOutboxJob(new OutboxJob(OutboxJobType.WELCOME,
		 * user, getEmailVerificationLink(user.getUserId()))); }
		 */

        UserSignUpResponse response = generateSignUpResponse(user, isEmail);
        response.setMobileVerified(!isEmail);
        //response.setProfileMasterData(generateProfileLaunchResponse(user.getUserType()));
        return response;
    }

    @Override
    public String verifyUserEmail(String encryptedId) {
        logger.info("Decrypting user id " + encryptedId);
        String userId = CryptoUtility.aesDecrypt(encryptedId);
        if(userId == null) {
            return "Malformed URL. Please try again";
        }
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            return "System does not recognise you.";
        }
        if(user.isEmailVerified()) {
            return "Email already verified. Please proceed with login.";
        }else {
            user.setEmailVerified(true);
            userRepository.save(user);
            return "Email verified successfully";
            //return "Email verified successfully.";
        }
    }
    
    protected String getCharByService(UserType serviceType){
        String result = "ME";
        switch (serviceType) {
            case DOCTOR:
                result = serviceType.getValue().toUpperCase().substring(0,2);
                break;
            case PATIENT:
                result = serviceType.getValue().toUpperCase().substring(0,2);
                break;
        }
        return result;
    }
    
    protected Set<Role> getRoleByService(UserType userType){
        Set<Role> roles = new HashSet<>();
        switch (userType) {
            case DOCTOR:
                Role role = roleRepository.findByNameAndDeleted(RoleType.DOCTOR, false);
                roles.add(role);
                break;
            case PATIENT:
                Role rolePatient = roleRepository.findByNameAndDeleted(RoleType.PATIENT, false);
                roles.add(rolePatient);
                break;
        }
        Role role = roleRepository.findByNameAndDeleted(RoleType.USER, false);
        roles.add(role);
        return roles;
    }
    
    @Override
    public AppLoginResponse login(AccessDto loginDto) {
		boolean isEmail = StringUtil.checkValidEmail(loginDto.getEmail());
		User user = getUserByEmailOrMobileNumber(loginDto, isEmail);
		if (user == null) {
			throw new ApiException(HttpStatus.UNAUTHORIZED.value(), "User does not exist. Sign up first");
		}
		boolean passwordMatch = BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
		AppLoginResponse loginResponse;
		if (passwordMatch && user.getAttempt() <= LOGIN_MAX_ATTEMPT) {
			if (user.isDeleted() || user.getStatus() == UserStatus.BLOCKED) {
				throw new ApiException(HttpStatus.UNAUTHORIZED.value(),
						"Account is blocked. Please try after some time.");
			}
			/*
			 * if (isEmail && !user.isEmailVerified()) { throw new
			 * ApiException(HttpStatus.BAD_REQUEST.value(),
			 * "Please verify your email before login."); }
			 */
			loginResponse = generateUserLoginResponse(user);
			user.setToken(loginResponse.getToken());
			user.setAttempt(0);
			//setDefaultOnUpdate(user, user); //what is this for?
			userRepository.save(user);
		} else {
            user.setAttempt(user.getAttempt() + 1);
			userRepository.save(user);
			if (user.getAttempt() > LOGIN_MAX_ATTEMPT) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Account blocked. Maximum login retry reached.");
			}
			throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
		}
		return loginResponse;
    }

	@Override
	public User getById(Long id) {
		User user = userRepository.findById(id).get();
        if(user == null) {
        	throw new ApiException(HttpStatus.BAD_REQUEST, "User not found.");
        }
		return user;
	}
    
    
}
