package com.healthconnect.user.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthconnect.user.model.common.PersonalProfile;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppLoginResponse{

    private String token;
    private PersonalProfile personalProfile;
    private String landingPage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PersonalProfile getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(PersonalProfile personalProfile) {
        this.personalProfile = personalProfile;
    }

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}
}

