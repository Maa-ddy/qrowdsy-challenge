package com.qrowdsy.domain.service.impl;

import java.util.Date;

import com.qrowdsy.domain.event.BookCreatedEvent;
import com.qrowdsy.domain.event.Event;
import com.qrowdsy.domain.event.queue.EventQueue;
import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.Book;
import com.qrowdsy.domain.model.FilteredBooksIterator;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.repository.BookRepository;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.BookService;
import static com.qrowdsy.domain.service.Utils.getOrDefault;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final EventQueue<Event> queue;

    public BookServiceImpl(BookRepository bookRepository, LibraryRepository libraryRepository, EventQueue<Event> queue) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.queue = queue;
    }

    @Override
    public BookId createBook(String name, String author, String genre, Date releaseDate) throws DomainException {
        var bookId = bookRepository.create(name, author, genre, releaseDate);
        queue.publish(new BookCreatedEvent(bookId));
        return bookId;
    }

    @Override
    public void updateBook(Book book) throws DomainException {
        var oldBook = bookRepository.find(book.id());
        var newBook = Book.of(
            book.id(),
            getOrDefault(book.name(), oldBook.name()),
            getOrDefault(book.author(), oldBook.author()),
            getOrDefault(book.genre(), oldBook.genre()),
            getOrDefault(book.releaseDate(), oldBook.releaseDate())
        );
        bookRepository.update(newBook);
    }

    @Override
    public void deleteBook(BookId id) throws DomainException {
        for (var library: bookRepository.findLibrariesHavingBook(id)) {
            libraryRepository.unassignBookFromLibrary(library.id(), id);
        }
        bookRepository.delete(id);
    }

    @Override
    public FilteredBooksIterator filterBooks(String criteria) throws DomainException {
        return new FilteredBooksIterator(bookRepository, criteria);
    }
    
}
