package com.qrowdsy.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LibraryEntity {
    private UUID id;
    private String name;
    private String town;
    private String postCode;
    private String phoneNumber;
    private String emailAddress;
    
    public LibraryEntity(String name, String town, String postCode, String phoneNumber, String emailAddress) {
        this.name = name;
        this.town = town;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}
