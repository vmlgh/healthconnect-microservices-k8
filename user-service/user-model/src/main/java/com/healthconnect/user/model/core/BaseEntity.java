package com.healthconnect.user.model.core;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RecordId")
	private long recordId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CreatedBy")
	//@JsonIgnore
	private User createdBy;

	@Column(name="CreatedOn")
	private LocalDateTime createdOn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LastModifiedBy")
   // @JsonIgnore
    private User lastModifiedBy;

	@Column(name="LastModifiedOn")
	private LocalDateTime lastModifiedOn;

	//TODO make it as active
	@Column(name="DeleteFlag", columnDefinition = "boolean default 0")
	private boolean deleted;
	
	@Transient
	private boolean markForDelete;

	public boolean isMarkForDelete() {
		return markForDelete;
	}

	public void setMarkForDelete(boolean markForDelete) {
		this.markForDelete = markForDelete;
	}
		
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	
	public boolean equals(BaseEntity o){
		return this.getClass().equals(o.getClass()) && this.getRecordId() == o.getRecordId();
	}

	public LocalDateTime getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(LocalDateTime lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
}	