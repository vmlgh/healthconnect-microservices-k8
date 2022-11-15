package com.healthconnect.user.model.patient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientMedicalRecordDto {

    private String title;
    private String description;
    private long eventDate;
    private String date;
    private long id;
    private List<PatientMedicalRecordFileDto> fileMetadata;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public List<PatientMedicalRecordFileDto> getFileMetadata() {
        return fileMetadata;
    }

    public void setFileMetadata(List<PatientMedicalRecordFileDto> fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
