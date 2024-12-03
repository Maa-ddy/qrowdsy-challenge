package com.qrowdsy.domain.service.impl;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.LibraryManagementService;

public class LibraryManagementServiceImpl implements LibraryManagementService {

    private final LibraryRepository libraryRepository;

    public LibraryManagementServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void addBooksToLibrary(LibraryId libraryId, BookId bookId, Integer quantity) throws DomainException {
        if (!libraryRepository.bookExistsInLibrary(libraryId, bookId)) {
            libraryRepository.assignBookToLibrary(libraryId, bookId);
        }
        libraryRepository.updateBooksQuantity(libraryId, bookId, quantity);
    }

    @Override
    public void leaseBookFromLibrary(LibraryId libraryId, BookId bookId) throws DomainException {
        var oldQuantity = libraryRepository.getBookQuantityInLibrary(libraryId, bookId);
        libraryRepository.updateBooksQuantity(libraryId, bookId, oldQuantity - 1);
    }

    @Override
    public void returnBookToLibrary(LibraryId libraryId, BookId bookId) throws DomainException {
        var oldQuantity = libraryRepository.getBookQuantityInLibrary(libraryId, bookId);
        libraryRepository.updateBooksQuantity(libraryId, bookId, oldQuantity + 1);
    }
    
}
