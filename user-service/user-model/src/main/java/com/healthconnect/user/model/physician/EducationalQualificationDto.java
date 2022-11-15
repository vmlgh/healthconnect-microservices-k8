package com.healthconnect.user.model.physician;

public class EducationalQualificationDto {

    private long id;
    private long degreeId;
    private String degreeName;
    private long collegeId;
    private String collegeName;
    private int yearOfCompletion;
    private boolean highest;
    //private boolean delete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(long degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public int getYearOfCompletion() {
        return yearOfCompletion;
    }

    public void setYearOfCompletion(int yearOfCompletion) {
        this.yearOfCompletion = yearOfCompletion;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(long collegeId) {
        this.collegeId = collegeId;
    }

    public boolean isHighest() {
        return highest;
    }

    public void setHighest(boolean highest) {
        this.highest = highest;
    }

	
	/*
	 * public boolean isDelete() { return delete; }
	 * 
	 * public void setDelete(boolean delete) { this.delete = delete; }
	 */
	 
}

