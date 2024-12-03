package com.qrowdsy.infrastructure.persistence.entity;

import java.util.UUID;

import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.LibraryId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="library")
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String name;
    private String town;
    private String postCode;
    private String phoneNumber;
    private String emailAddress;

    public LibraryEntity() {}
    
    private LibraryEntity(UUID id, String name, String town, String postCode, String phoneNumber, String emailAddress) {
        this.name = name;
        this.town = town;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public LibraryEntity(String name, String town, String postCode, String phoneNumber, String emailAddress) {
        this(null, name, town, postCode, phoneNumber, emailAddress);
    }

    public static LibraryEntity from(Library library) {
        return new LibraryEntity(
            library.id().rawId(),
            library.name(),
            library.address().town(),
            library.address().postCode(),
            library.phoneNumber(),
            library.emailAddress()
        );
    }

    public Library toModel() {
        return Library.of(
            LibraryId.of(id), 
            name, 
            new Address(town, postCode), 
            phoneNumber, 
            emailAddress
        );
    }

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
