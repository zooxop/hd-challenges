package com.chmun.chart.domain.hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {

    @Id
    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "director_name", nullable = false)
    private String directorName;

    public Hospital() {

    }

    public Hospital(Long hospitalId, String name, String organizationId, String directorName) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.organizationId = organizationId;
        this.directorName = directorName;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public String getName() {
        return name;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getDirectorName() {
        return directorName;
    }
}
