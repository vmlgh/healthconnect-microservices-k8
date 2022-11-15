package com.healthconnect.user.model.core;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthconnect.user.model.common.Notification;
import com.healthconnect.user.model.enums.GenderType;
import com.healthconnect.user.model.enums.UserStatus;
import com.healthconnect.user.model.enums.UserType;

@Entity
@Table(name ="Users")
public class User extends BaseEntity {

    @Column(name = "Title", length = 4)
    private String title;

    @Column(name = "ShortTitleDescription", length = 50)
    private String titleDescription;

    @Column(name ="FirstName")
    private String firstName;

    @Column(name ="LastName")
    private String lastName;

	@Column(name = "Username")
    private String username;

    @Column(name = "UserId", unique = true)
    private String userId;
	
	@Column(name ="Email")
	private String email;
	
	@Column(name ="Password")
	private String password;

	@Column(name ="ProfileStatus")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "UserType")
	private UserType userType;

    @Column(name ="Gender")
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Column(name ="MobileNumber")
    private String mobileNumber;

	@Column(name = "EmailVerified", columnDefinition = "boolean default 0")
    private boolean emailVerified;

    @Column(name = "MobileVerified", columnDefinition = "boolean default 0")
    private boolean mobileVerified;

	@Column(name = "Token", length = 500)
    private String token;

	@Column(name = "Attempt", columnDefinition = "int default 0")
    private int attempt;

	@Column(name = "ProfileImageUrl")
    private String profileImageUrl;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "UserUserRoles",
	joinColumns =  @JoinColumn(name ="UserId"),inverseJoinColumns= @JoinColumn(name="RoleId"))
	@JsonIgnore
	private Set<Role> roles;

	@OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    public String getFirstName() {
        return firstName;
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

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }
}

