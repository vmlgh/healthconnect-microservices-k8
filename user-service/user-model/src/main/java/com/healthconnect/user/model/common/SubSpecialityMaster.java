package com.healthconnect.user.model.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.physician.Physician;

@Entity
@Table(name ="SubSpecialityMaster")
public class SubSpecialityMaster extends BaseEntity {

    @Column(name ="Name", unique = true)
    private String name;

    @Column(name ="Description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private SpecialityMaster specialityMaster;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subSpecialities")
    @JsonBackReference
    private List<Physician> physicians;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SpecialityMaster getSpecialityMaster() {
        return specialityMaster;
    }

    public void setSpecialityMaster(SpecialityMaster specialityMaster) {
        this.specialityMaster = specialityMaster;
    }


    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }
}

