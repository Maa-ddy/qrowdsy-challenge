package com.qrowdsy.infrastructure.persistence.repository;


import java.util.List;

import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.LibraryBooksIterator;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.LibraryBooksSearchCriteria;

public class LibraryRepositoryImpl implements LibraryRepository {

    @Override
    public LibraryId create(String name, String town, Address address, String phoneNumber, String emailAddress)
            throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Library find(LibraryId id) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public LibraryId update(Library library) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(LibraryId id) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void assignBookToLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignBookToLibrary'");
    }

    @Override
    public void unassignBookFromLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unassignBookFromLibrary'");
    }

    @Override
    public void updateBooksQuantity(LibraryId libraryId, BookId bookId, Integer quantity) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBooksQuantity'");
    }

    @Override
    public boolean bookExistsInLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bookExistsInLibrary'");
    }

    @Override
    public Integer getBookQuantityInLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookQuantityInLibrary'");
    }

    @Override
    public List<String> getGenresInLibrary(LibraryId libraryId) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGenresInLibrary'");
    }

    @Override
    public LibraryBooksIterator filterLibraryBooks(LibraryId libraryId, LibraryBooksSearchCriteria criteria)
            throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterLibraryBooks'");
    }
}
