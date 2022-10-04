package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "transports")
public class transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Transport Name is mandatory")
    private String transporname;

    public void setEmployee(com.example.demo.employee employee) {
        this.employee = employee;
    }

    public void setTransporname(String transporname) {
        this.transporname = transporname;
    }

    @Min(value = 10, message = "Capacity need to be bigger than 10")
    @Max(value = 1000,message = "Capacity need to be less than 1000")
    private double tonsCapacity = 11;
    @Min(value = 10, message = "Capacity need to be bigger than 10")
    @Max(value = 1000,message = "Capacity need to be less than 1000")
    private double volumeCapacity = 11;

    @NotBlank(message = "Plates are necessary")
    private String plates ="xxxxxxxxxx";
    private double latitude, longitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "employee_id", nullable = true)
    private employee employee;

    public long getId() {
        return id;
    }

    public com.example.demo.employee getEmployee() {
        return employee;
    }

    public String getTransporname() {
        return transporname;
    }

    public double getTonscapacity() {
        return tonsCapacity;
    }

    public double getVolumeCapacity() {
        return volumeCapacity;
    }

    public String getPlates() {
        return plates;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public transport(employee owner, String transporName) {
        this.employee = owner;
        transporname = transporName;
    }

    public transport(String transporName) {
        transporname = transporName;
    }

    public transport() {
    }

    @Override
    public String toString() {
        return transporname;
    }

}
