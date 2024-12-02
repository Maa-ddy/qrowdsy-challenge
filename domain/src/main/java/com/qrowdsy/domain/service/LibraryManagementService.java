package com.qrowdsy.domain.service;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;

public interface LibraryManagementService {
    void addBooksToLibrary(LibraryId libraryId, BookId bookId, Integer quantity) throws DomainException;
    void leaseBookFromLibrary(LibraryId libraryId, BookId bookId) throws DomainException;
    void returnBookToLibrary(LibraryId libraryId, BookId bookId) throws DomainException;
}
