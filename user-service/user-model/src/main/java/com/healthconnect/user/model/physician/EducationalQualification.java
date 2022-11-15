package com.healthconnect.user.model.physician;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.healthconnect.user.model.common.DegreeMaster;
import com.healthconnect.user.model.common.MedicalCollegeMaster;
import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name = "EducationalQualification")
public class EducationalQualification extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name ="DegreeId")
	private DegreeMaster degree;

	@Column(name ="DegreeType")
	private String degreeName;
	
	@Column(name ="CompletionYear")
	private Integer completionYear;
	
	@Column(name ="CollegeName")
	private String collegeName;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CollegeMasterId")
	private MedicalCollegeMaster collegeMaster;
	
	@Column(name ="IsHighest", columnDefinition = "boolean default 0")
	private boolean highestQual;

    @ManyToOne
    @JoinColumn(name ="PhysicianId")
    @JsonBackReference
    private Physician physician;

    public DegreeMaster getDegree() {
        return degree;
    }

    public void setDegree(DegreeMaster degree) {
        this.degree = degree;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Integer getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(Integer completionYear) {
        this.completionYear = completionYear;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public MedicalCollegeMaster getCollegeMaster() {
        return collegeMaster;
    }

    public void setCollegeMaster(MedicalCollegeMaster collegeMaster) {
        this.collegeMaster = collegeMaster;
    }

    public boolean isHighestQual() {
        return highestQual;
    }

    public void setHighestQual(boolean highestQual) {
        this.highestQual = highestQual;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public EducationalQualificationDto toEducationalQualificationDto(){
        EducationalQualificationDto educationalQualificationDto = new EducationalQualificationDto();
        educationalQualificationDto.setCollegeId(this.collegeMaster.getRecordId());
        educationalQualificationDto.setCollegeName(this.collegeMaster.getName());
        educationalQualificationDto.setDegreeId(this.degree.getRecordId());
        educationalQualificationDto.setDegreeName(this.degreeName);
        educationalQualificationDto.setId(this.getRecordId());
        educationalQualificationDto.setYearOfCompletion(this.completionYear);
        educationalQualificationDto.setHighest(this.isHighestQual());
        return educationalQualificationDto;
    }
}
