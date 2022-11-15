package com.healthconnect.user.model.physician;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthconnect.user.model.common.PricingDto;
import com.healthconnect.user.model.enums.GenderType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhysicianDto extends BasePhysicianDto {


	@NotEmpty(message="{physician.firstname.empty.error}")
    private String firstName;
    
    @NotEmpty(message="{physician.lastname.empty.error}")
    private String lastName;
    
    private String dob;
    
    @NotEmpty(message ="{physician.mobile.empty.error}")
    private String mobile;

    private GenderType gender;
    
    private int totalExp;
        
    private long regdCouncilId;
    
    private PricingDto pricing;
    
    private String regdCouncilName;
    
    @NotEmpty(message ="{physician.regd.year.empty.error}")
    private String regdYear;
    
    @NotEmpty(message ="{physician.profiledesc.empty.error}")
    @Size(max = 3000,message ="{physician.profiledesc.sizeexceed.error}")
    private String profileDescription;
    
    @NotEmpty(message ="{physician.profiletitle.empty.error}")
    @Size(max= 60,message ="{physician.profiletitle.sizeexceed.error}")
    private String profileTitle;

    private String fellowships;
    
    private String userId;
    
    private String email;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
        return firstName;
    }

    public PricingDto getPricing() {
		return pricing;
	}

	public void setPricing(PricingDto pricing) {
		this.pricing = pricing;
	}

	public String getProfileTitle() {
		return profileTitle;
	}

	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    public long getRegdCouncilId() {
        return regdCouncilId;
    }

    public void setRegdCouncilId(long regdCouncilId) {
        this.regdCouncilId = regdCouncilId;
    }

    public String getRegdYear() {
        return regdYear;
    }

    public void setRegdYear(String regdYear) {
        this.regdYear = regdYear;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getRegdCouncilName() {
		return regdCouncilName;
	}

	public void setRegdCouncilName(String regdCouncilName) {
		this.regdCouncilName = regdCouncilName;
	}

    public String getFellowships() {
        return fellowships;
    }

    public void setFellowships(String fellowships) {
        this.fellowships = fellowships;
    }
    
    @Override
	public String toString() {
		return "PhysicianDto [userId=" + userId + ", firstName=" + firstName + ", email=" + email + "]";
	}
}

