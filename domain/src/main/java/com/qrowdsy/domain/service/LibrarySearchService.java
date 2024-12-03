package com.qrowdsy.domain.service;

import java.util.List;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.LibraryBooksIterator;
import com.qrowdsy.domain.model.id.LibraryId;

public interface LibrarySearchService {
    LibraryBooksIterator getLibraryBooks(LibraryId libraryId) throws DomainException;
    List<String> getGenresInLibrary(LibraryId libraryId) throws DomainException;
}
