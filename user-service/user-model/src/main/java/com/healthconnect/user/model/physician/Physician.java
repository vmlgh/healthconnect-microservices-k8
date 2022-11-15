package com.healthconnect.user.model.physician;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.common.SubSpecialityMaster;
import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.core.User;

@Entity
@Table(name ="Physician")
public class Physician  extends BaseEntity {
	
	@Column(name ="Age")
	private int age;
	
	@Column(name ="Dob")
	private LocalDate dob;
	
	@Column(name = "RegdNumber")
	private String registrationNumber;

	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PhysicianAddress",
			joinColumns =  @JoinColumn(name ="PhysicianId"),inverseJoinColumns= @JoinColumn(name="AddressId"))
	private List<Address> addresses;*/
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name ="ProfileId")
	private PhysicianProfile physicianProfile;
	
	@OneToOne
	@JoinColumn(name ="SpecialityId")
	private SpecialityMaster speciality;

	@OneToOne
	@JoinColumn(name ="UserId", referencedColumnName = "userId")
	private User user;

	@OneToMany(mappedBy = "physician")
	private List<EducationalQualification> educationalQualifications;

	@Column(name = "Fellowship")
    private String fellowship;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PhysicianHospital",
            joinColumns =  @JoinColumn(name ="PhysicianId"),inverseJoinColumns= @JoinColumn(name="HospitalId"))
    private Set<Hospital> hospitals;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PhysicianSubSpeciality",
			joinColumns =  @JoinColumn(name ="PhysicianId"),inverseJoinColumns= @JoinColumn(name="SubSpecialityId"))
	private Set<SubSpecialityMaster> subSpecialities;

	/*
	 * @OneToMany(mappedBy = "physician") private List<WorkSchedule> workSchedules;
	 */

	public Integer getAge() {
		return age;
	}

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	/*public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}*/

	public PhysicianProfile getPhysicianProfile() {
		return physicianProfile;
	}

	public void setPhysicianProfile(PhysicianProfile physicianProfile) {
		this.physicianProfile = physicianProfile;
	}

	public SpecialityMaster getSpeciality() {
		return speciality;
	}

	public void setSpeciality(SpecialityMaster speciality) {
		this.speciality = speciality;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<EducationalQualification> getEducationalQualifications() {
		return educationalQualifications;
	}

	public void setEducationalQualifications(List<EducationalQualification> educationalQualifications) {
		this.educationalQualifications = educationalQualifications;
	}

    public String getFellowship() {
        return fellowship;
    }

    public void setFellowship(String fellowship) {
        this.fellowship = fellowship;
    }

    public Set<SubSpecialityMaster> getSubSpecialities() {
        return subSpecialities;
    }

    public void setSubSpecialities(Set<SubSpecialityMaster> subSpecialities) {
        this.subSpecialities = subSpecialities;
    }

	/*
	 * public List<WorkSchedule> getWorkSchedules() { return workSchedules; }
	 * 
	 * public void setWorkSchedules(List<WorkSchedule> workSchedules) {
	 * this.workSchedules = workSchedules; }
	 */

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public Set<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

}

