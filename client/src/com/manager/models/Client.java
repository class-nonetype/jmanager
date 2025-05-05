package com.manager.models;

public class Client {
    private String id;
    private String rut;
    private String fullName;
    private String address;

    // Constructor
    public Client(String id, String rut, String fullName, String address) {
        this.id = id;
        this.rut = rut;
        this.fullName = fullName;
        this.address = address;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", rut='" + rut + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
