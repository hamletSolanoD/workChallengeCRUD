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
    @Min(value = 10, message = "Capacity need to be bigger than 10")
    @Max(value = 1000, message = "Capacity need to be less than 1000")
    private double tonsCapacity = 11;
    @Min(value = 10, message = "Capacity need to be bigger than 10")
    @Max(value = 1000, message = "Capacity need to be less than 1000")
    private double volumeCapacity = 11;
    @NotBlank(message = "Plates are necessary")
    private String plates;
    private double latitude, longitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "employee_id", nullable = true)
    private employee employee;
    public transport(employee owner, String transporName,String plates) {
        this.employee = owner;
        this.plates = plates;
        transporname = transporName;
    }

    public transport(String transporName,String plates) {
        transporname = transporName;
        this.plates= plates;

    }

    public transport() {
    }
    @Override
    public String toString() {
        return transporname;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransporname() {
        return transporname;
    }

    public void setTransporname(String transporname) {
        this.transporname = transporname;
    }

    public double getTonsCapacity() {
        return tonsCapacity;
    }

    public void setTonsCapacity(double tonsCapacity) {
        this.tonsCapacity = tonsCapacity;
    }

    public double getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(double volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public double getLatitude() {
        return latitude;
    }
    public String getCoordenates(){
        return getLatitude()+";"+getLongitude();
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public com.example.demo.employee getEmployee() {
        return employee;
    }

    public void setEmployee(com.example.demo.employee employee) {

        this.employee = employee;
    }
}
