package com.dmitriy.fedo.springbootRestAPI;

import javax.persistence.*;

@Entity
@Table(name = "MSI-master")
public class ListOfUniversities {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", nullable = false)
    private Long id;


    @Column(name = "State")
    private String state;

    @Column(name = "Name")
    private String name;

    @Column(name = "Institution Type")
    private String type;

    @Column(name = "Phone Number")
    private String phone;

    @Column(name = "Website")
    private String website;

    public ListOfUniversities() {

    }

    public ListOfUniversities(String state, String name, String type, String phone, String site) {
        this.state = state;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public Long getId() {
        return id;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "ListOfUniversities{" +
                "state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}