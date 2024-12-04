package com.qrowdsy.domain.service.impl;

import com.qrowdsy.domain.event.BookLeasedEvent;
import com.qrowdsy.domain.event.Event;
import com.qrowdsy.domain.event.queue.EventQueue;
import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.LibraryManagementService;

public class LibraryManagementServiceImpl implements LibraryManagementService {

    private final LibraryRepository libraryRepository;
    private final EventQueue<Event> queue;

    public LibraryManagementServiceImpl(LibraryRepository libraryRepository, EventQueue<Event> queue) {
        this.libraryRepository = libraryRepository;
        this.queue = queue;
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
        if (oldQuantity <= 0) {
            throw new DomainException("There are no more books in the library");
        }
        libraryRepository.updateBooksQuantity(libraryId, bookId, oldQuantity - 1);
        queue.publish(new BookLeasedEvent(bookId));
    }

    @Override
    public void returnBookToLibrary(LibraryId libraryId, BookId bookId) throws DomainException {
        var oldQuantity = libraryRepository.getBookQuantityInLibrary(libraryId, bookId);
        libraryRepository.updateBooksQuantity(libraryId, bookId, oldQuantity + 1);
    }

    public void assignBookToLibrary(LibraryId libraryId, BookId bookId) throws DomainException {
        libraryRepository.assignBookToLibrary(libraryId, bookId);
    }

    public void unassignBookFromLibrary(LibraryId libraryId, BookId bookId) throws DomainException {
        libraryRepository.unassignBookFromLibrary(libraryId, bookId);
    }
    
}
