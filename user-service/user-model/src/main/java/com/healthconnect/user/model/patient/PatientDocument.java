package com.healthconnect.user.model.patient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.healthconnect.user.model.core.UserDocument;
import com.healthconnect.user.model.enums.PatientMedicalRecordFileType;

@Entity
@Table(name = "PatientDocument")
public class PatientDocument extends UserDocument {

    @Enumerated(EnumType.STRING)
    @Column(name = "RecordFileType")
    private PatientMedicalRecordFileType fileType;

    @ManyToOne
    @JoinColumn(name ="PatientMedicalRecordId", referencedColumnName = "RecordId")
    private PatientMedicalRecord patientMedicalRecord;

    @Transient
    private MultipartFile file;

    public PatientMedicalRecordFileType getFileType() {
        return fileType;
    }

    public void setFileType(PatientMedicalRecordFileType fileType) {
        this.fileType = fileType;
    }

    public PatientMedicalRecord getPatientMedicalRecord() {
        return patientMedicalRecord;
    }

    public void setPatientMedicalRecord(PatientMedicalRecord patientMedicalRecord) {
        this.patientMedicalRecord = patientMedicalRecord;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

