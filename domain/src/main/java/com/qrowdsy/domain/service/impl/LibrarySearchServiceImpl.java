package com.qrowdsy.domain.service.impl;

import java.util.List;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.LibraryBooksIterator;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.BookRepository;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.LibraryBooksSearchCriteria;
import com.qrowdsy.domain.service.LibrarySearchService;

public class LibrarySearchServiceImpl implements LibrarySearchService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    public LibrarySearchServiceImpl(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public LibraryBooksIterator getLibraryBooks(LibraryId libraryId) {
        return new LibraryBooksIterator(libraryId, bookRepository);
    }

    @Override
    public List<String> getGenresInLibrary(LibraryId libraryId) throws DomainException {
        return libraryRepository.getGenresInLibrary(libraryId);
    }

    @Override
    public LibraryBooksIterator filterLibraryBooks(LibraryId libraryId, LibraryBooksSearchCriteria criteria) {
        return filterLibraryBooks(libraryId, criteria);
    }
    
}
