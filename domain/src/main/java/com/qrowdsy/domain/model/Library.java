package com.qrowdsy.domain.model;

import com.qrowdsy.domain.model.id.LibraryId;

public class Library {

    private final LibraryId id;
    private final String name;
    private final Address address;
    private final String phoneNumber;
    private final String emailAddress;

    private Library(LibraryId id, String name, Address address, String phoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Library(String name, Address address, String phoneNumber, String emailAddress) {
        this(new LibraryId(), name, address, phoneNumber, emailAddress);
    }

    public static Library of(LibraryId id, String name, Address address, String phoneNumber, String emailAddress) {
        return new Library(id, name, address, phoneNumber, emailAddress);
    }

    public LibraryId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Address address() {
        return address;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String emailAddress() {
        return emailAddress;
    }
    
}
