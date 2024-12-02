package com.qrowdsy.domain.service;

public record LibraryBooksSearchCriteria(
    String name, 
    String author, 
    String genre, 
    String emailAddress, 
    String phoneNumber, 
    String town, 
    String postCode,
    String wildcard
) {
}
