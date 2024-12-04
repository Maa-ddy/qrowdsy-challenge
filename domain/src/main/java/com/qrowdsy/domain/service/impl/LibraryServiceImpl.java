package com.qrowdsy.domain.service.impl;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.LibraryService;
import static com.qrowdsy.domain.service.Utils.getOrDefault;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public LibraryId createLibrary(String name, String town, Address address, String phoneNumber, String emailAddress)
            throws DomainException {
        return libraryRepository.create(name, town, address, phoneNumber, emailAddress);
    }

    @Override
    public void updateLibrary(Library library) throws DomainException {
        var oldLibrary = libraryRepository.find(library.id());
        var newLibrary = Library.of(
            library.id(), 
            getOrDefault(library.name(), oldLibrary.name()), 
            getOrDefault(library.address(), oldLibrary.address()), 
            getOrDefault(library.phoneNumber(), oldLibrary.phoneNumber()), 
            getOrDefault(library.emailAddress(), oldLibrary.emailAddress())
        );
        libraryRepository.update(newLibrary);
    }

    @Override
    public Library findLibrary(LibraryId id) throws DomainException {
        return this.libraryRepository.find(id);
    }

    public List<Library> findAllLibraries() throws DomainException {
        return this.libraryRepository.findAll();
    }
    
}
