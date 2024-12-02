package com.qrowdsy.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.BookRepository;

public class LibraryBooksIterator implements Iterator<Book> {

    private final BookRepository bookRepository;

    private int offset;
    private int limit;
    private final LibraryId libraryId;

    private List<Book> slice;
    private int sliceIdx;

    public LibraryBooksIterator(LibraryId libraryId, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.libraryId = libraryId;
        offset = 0;
        limit = 10;
        slice = new ArrayList<>();
    }

    public LibraryBooksIterator offset(int offset) {
        this.offset = offset;
        return this;
    }

    public LibraryBooksIterator limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public boolean hasNext() {
        refreshSlice();
        return sliceIdx < slice.size();
    }

    private final void refreshSlice() {
        if (sliceIdx < slice.size()) return;
        offset += limit;
        try {
            slice = bookRepository.findBooksByLibrary(libraryId, offset, limit);
        } catch (RepositoryException e) {
            slice = new ArrayList<>();
        }
        sliceIdx = 0;
    }

    @Override
    public Book next() {
        refreshSlice();
        return slice.get(sliceIdx++);
    }

    public List<Book> slice(int startIdx, int endIdx) throws RepositoryException {
        return bookRepository.findBooksByLibrary(libraryId, startIdx, endIdx);
    }
    
}
