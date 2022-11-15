package com.healthconnect.user.model.core;

import javax.persistence.*;

/**
 * The Class UserRepository.
 */
@MappedSuperclass
public abstract class UserDocument extends BaseEntity {

	@Column(name = "EncryptedAwsFileName")
	private String encryptedAwsFileName;
	@Column(name = "OriginalFileName")
	private String originalFilename;
	@Column(name = "AwsFileName")
	private String awsFilename;
	@Column(name = "BucketUrl")
	private String bucketUrl;
	@Column(name = "PublicImageUrl")
	private String publicImageUrl;
	@Column(name = "IsPublic", columnDefinition = "boolean default 0")
	private boolean isPublic;
	@Column(name ="Version")
	private String version;

	public String getAwsFilename() {
		return awsFilename;
	}

	public void setAwsFilename(String awsFilename) {
		this.awsFilename = awsFilename;
	}

	public String getBucketUrl() {
		return bucketUrl;
	}

	public void setBucketUrl(String bucketUrl) {
		this.bucketUrl = bucketUrl;
	}

	public String getPublicImageUrl() {
		return publicImageUrl;
	}

	public void setPublicImageUrl(String publicImageUrl) {
		this.publicImageUrl = publicImageUrl;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean aPublic) {
		isPublic = aPublic;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncryptedAwsFileName() {
		return encryptedAwsFileName;
	}

	public void setEncryptedAwsFileName(String encryptedAwsFileName) {
		this.encryptedAwsFileName = encryptedAwsFileName;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
}

