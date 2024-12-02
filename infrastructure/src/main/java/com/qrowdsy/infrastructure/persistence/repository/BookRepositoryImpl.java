package com.qrowdsy.infrastructure.persistence.repository;

import java.util.Date;
import java.util.List;

import com.qrowdsy.domain.exception.NofFoundException;
import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.model.Book;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.BookRepository;
import com.qrowdsy.infrastructure.persistence.entity.BookEntity;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaBookRepository;

public class BookRepositoryImpl implements BookRepository {

    private final JpaBookRepository jpaBookRepository;

    public BookRepositoryImpl(JpaBookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public BookId create(String name, String author, String genre, Date releaseDate) throws RepositoryException {
        var bookEntity = jpaBookRepository.save(
            new BookEntity(name, author, genre, releaseDate)
        );
        return BookId.of(bookEntity.getId());
    }

    @Override
    public BookId update(Book book) throws RepositoryException {
        var bookEntity = jpaBookRepository.save(
            BookEntity.from(book)
        );
        return BookId.of(bookEntity.getId());
    }

    @Override
    public Book find(BookId id) throws RepositoryException {
        var bookEntity = jpaBookRepository.findById(id.rawId());
        if (bookEntity.isEmpty()) {
            throw new NofFoundException();
        }
        return bookEntity.get().toModel();
    }

    @Override
    public void delete(BookId id) throws RepositoryException {
        try {
            jpaBookRepository.deleteById(id.rawId());
        } catch (Exception e) {
            throw new NofFoundException();
        }
    }

    @Override
    public List<Library> findLibrariesHavingBook(BookId bookId) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findLibrariesHavingBook'");
    }

    @Override
    public List<Book> findBooksByLibrary(LibraryId libraryId, Integer offset, Integer limit)
            throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBooksByLibrary'");
    }
}
