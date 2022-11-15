package com.healthconnect.user.model.patient;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name = "PatientMedicalRecord")
public class PatientMedicalRecord extends BaseEntity {

    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "EventDate")
    private LocalDate eventDate;
    @ManyToOne
    @JoinColumn(name ="PatientId", referencedColumnName = "RecordId")
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="patientMedicalRecord")
    private List<PatientDocument> patientDocuments;

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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<PatientDocument> getPatientDocuments() {
        return patientDocuments;
    }

    public void setPatientDocuments(List<PatientDocument> patientDocuments) {
        this.patientDocuments = patientDocuments;
    }

    public PatientMedicalRecordDto toPatientMedicalRecordDto() {
        PatientMedicalRecordDto dto = new PatientMedicalRecordDto();
        List<PatientMedicalRecordFileDto> fileMetadata= new ArrayList<>();
        dto.setDescription(this.getDescription());
        dto.setEventDate(this.eventDate.atStartOfDay().atZone(ZoneId.systemDefault()).getSecond());
        dto.setTitle(this.title);
        dto.setDate(this.eventDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.patientDocuments.forEach(patientDocument -> {
            PatientMedicalRecordFileDto patientMedicalRecordFileDto = new PatientMedicalRecordFileDto(patientDocument.getFileType(), patientDocument.getPublicImageUrl());
            fileMetadata.add(patientMedicalRecordFileDto);
        });
        dto.setId(this.getRecordId());
        dto.setFileMetadata(fileMetadata);
        return dto;
    }
}
