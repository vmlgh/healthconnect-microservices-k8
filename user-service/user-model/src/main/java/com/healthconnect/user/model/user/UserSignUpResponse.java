package com.healthconnect.user.model.user;

import com.healthconnect.user.model.enums.UserType;

public class UserSignUpResponse {

    private String email;
    private String phone;
    private String username;
    private long id;
    private boolean emailVerified;
    private UserType userType;
    private String userId;
    private String userStatus;
    private String token;
    private String landingPage;
    private boolean mobileVerified;
    
    //private BaseMasterDataDto  profileMasterData;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
    public boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	/*
	 * public BaseMasterDataDto getProfileMasterData() { return profileMasterData; }
	 * 
	 * public void setProfileMasterData(BaseMasterDataDto profileMasterData) {
	 * this.profileMasterData = profileMasterData; }
	 */
}

