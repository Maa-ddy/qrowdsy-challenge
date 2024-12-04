package com.qrowdsy.domain.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.repository.BookRepository;

public class FilteredBooksIterator implements Iterator<Book>, Iterable<Book> {

    private final BookRepository bookRepository;
    
    private int offset;
    private int limit;
    private List<Book> slice;
    private int sliceIdx;
    private final String criteria;

    public FilteredBooksIterator(BookRepository bookRepository, String criteria) {
        this.bookRepository = bookRepository;
        this.criteria = criteria;
        sliceIdx = 0;
        offset = 0;
        limit = 10;
        slice = new ArrayList<>();
    }

    public FilteredBooksIterator offset(int offset) {
        this.offset = offset;
        sliceIdx = 0;
        slice = new ArrayList<>();
        return this;
    }

    public FilteredBooksIterator limit(int limit) {
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
        try {
            slice = bookRepository.filterBooks(criteria, offset, limit);
        } catch (RepositoryException e) {
            slice = new ArrayList<>();
        }
        sliceIdx = 0;
        offset += limit;
    }

    @Override
    public Book next() {
        refreshSlice();
        return slice.get(sliceIdx++);
    }

    public List<Book> slice(int startIdx, int endIdx) throws RepositoryException {
        return bookRepository.filterBooks(criteria, startIdx, endIdx - startIdx + 1);
    }

    @Override
    public Iterator<Book> iterator() {
        return this;
    }
    
}
