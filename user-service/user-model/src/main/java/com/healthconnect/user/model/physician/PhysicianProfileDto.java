package com.healthconnect.user.model.physician;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthconnect.user.model.common.PersonalProfile;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhysicianProfileDto {

    private PhysicianProfessionalProfile physicianProfessionalProfile;
    private PersonalProfile personalProfile;

    private boolean personalProfileExist;
    private boolean educationalProfileExist;
    private boolean workProfileExist;

    public PhysicianProfessionalProfile getPhysicianProfessionalProfile() {
        return physicianProfessionalProfile;
    }

    public void setPhysicianProfessionalProfile(PhysicianProfessionalProfile physicianProfessionalProfile) {
        this.physicianProfessionalProfile = physicianProfessionalProfile;
    }

    public PersonalProfile getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(PersonalProfile personalProfile) {
        this.personalProfile = personalProfile;
    }

    public boolean isPersonalProfileExist() {
        return personalProfileExist;
    }

    public void setPersonalProfileExist(boolean personalProfileExist) {
        this.personalProfileExist = personalProfileExist;
    }

    public boolean isEducationalProfileExist() {
        return educationalProfileExist;
    }

    public void setEducationalProfileExist(boolean educationalProfileExist) {
        this.educationalProfileExist = educationalProfileExist;
    }

    public boolean isWorkProfileExist() {
        return workProfileExist;
    }

    public void setWorkProfileExist(boolean workProfileExist) {
        this.workProfileExist = workProfileExist;
    }
}
