package com.qrowdsy.domain.repository;


import java.util.List;

import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;

public interface LibraryRepository {
    LibraryId create(String name, String town, Address address, String phoneNumber, String emailAddress) throws RepositoryException;
    Library find(LibraryId id) throws RepositoryException;
    LibraryId update(Library library) throws RepositoryException;
    void delete(LibraryId id) throws RepositoryException;

    void assignBookToLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException;
    void unassignBookFromLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException;
    void updateBooksQuantity(LibraryId libraryId, BookId bookId, Integer quantity) throws RepositoryException;
    boolean bookExistsInLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException;
    Integer getBookQuantityInLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException;
    List<String> getGenresInLibrary(LibraryId libraryId) throws RepositoryException;
}
