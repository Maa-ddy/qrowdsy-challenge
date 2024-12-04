package com.qrowdsy.domain.service;

import java.util.List;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.LibraryId;

public interface LibraryService {
    LibraryId createLibrary(String name, String town, Address address, String phoneNumber, String emailAddress) throws DomainException;
    void updateLibrary(Library library) throws DomainException;
    Library findLibrary(LibraryId id) throws DomainException;
    List<Library> findAllLibraries() throws DomainException;
}
