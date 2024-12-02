package com.qrowdsy.domain.repository;

import java.util.Date;
import java.util.List;

import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.model.Book;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;

public interface BookRepository {
    BookId create(String name, String author, String genre, Date releaseDate) throws RepositoryException;
    BookId update(Book book) throws RepositoryException;
    Book find(BookId id) throws RepositoryException;
    void delete(BookId id) throws RepositoryException;

    List<Library> findLibrariesHavingBook(BookId bookId) throws RepositoryException;
    List<Book> findBooksByLibrary(LibraryId libraryId, Integer offset, Integer limit) throws RepositoryException;
}
