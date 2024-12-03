package com.qrowdsy.infrastructure.persistence.repository;


import java.util.List;

import com.qrowdsy.domain.exception.NofFoundException;
import com.qrowdsy.domain.exception.RepositoryException;
import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.Library;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.infrastructure.persistence.entity.BookLibraryEntity;
import com.qrowdsy.infrastructure.persistence.entity.LibraryEntity;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaBookLibraryRepository;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaBookRepository;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaLibraryRepository;

public class LibraryRepositoryImpl implements LibraryRepository {

    private final JpaLibraryRepository jpaLibraryRepository;
    private final JpaBookLibraryRepository jpaBookLibraryRepository;
    private final JpaBookRepository jpaBookRepository;

    public LibraryRepositoryImpl(JpaLibraryRepository jpaLibraryRepository, JpaBookLibraryRepository jpaBookLibraryRepository, JpaBookRepository jpaBookRepository) {
        this.jpaLibraryRepository = jpaLibraryRepository;
        this.jpaBookLibraryRepository = jpaBookLibraryRepository;
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public LibraryId create(String name, String town, Address address, String phoneNumber, String emailAddress)
            throws RepositoryException {
        var libraryEntity = jpaLibraryRepository.save(
            new LibraryEntity(name, town, address.postCode(), phoneNumber, emailAddress)
        );
        return LibraryId.of(libraryEntity.getId());
    }

    @Override
    public Library find(LibraryId id) throws RepositoryException {
        var library = jpaLibraryRepository.findById(id.rawId());
        if (library.isEmpty()) {
            throw new RepositoryException();
        }
        return library.get().toModel();
    }

    @Override
    public LibraryId update(Library library) throws RepositoryException {
        var libraryEntity = jpaLibraryRepository.save(
            LibraryEntity.from(library)
        );
        return LibraryId.of(libraryEntity.getId());
    }

    @Override
    public void delete(LibraryId id) throws RepositoryException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void assignBookToLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        var library = jpaLibraryRepository.findById(libraryId.rawId());
        var book = jpaBookRepository.findById(bookId.rawId());
        if (library.isEmpty() || book.isEmpty()) {
            throw new NofFoundException();
        }
        jpaBookLibraryRepository.save(new BookLibraryEntity(book.get(), library.get(), 0));
    }

    @Override
    public void unassignBookFromLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        findByLibraryIdAndBookId(libraryId, bookId)
            .forEach(bookLibraryEntity -> jpaBookLibraryRepository.delete(bookLibraryEntity));
    }

    private List<BookLibraryEntity> findByLibraryIdAndBookId(LibraryId libraryId, BookId bookId) throws NofFoundException {
        var library = jpaLibraryRepository.findById(libraryId.rawId());
        var book = jpaBookRepository.findById(bookId.rawId());
        if (library.isEmpty() || book.isEmpty()) {
            throw new NofFoundException();
        }
        return jpaBookLibraryRepository.findByLibraryIdAndBookId(library.get().getId(), book.get().getId());
    }

    @Override
    public void updateBooksQuantity(LibraryId libraryId, BookId bookId, Integer quantity) throws RepositoryException {
        findByLibraryIdAndBookId(libraryId, bookId)
            .forEach(bookLibraryEntity -> {
                bookLibraryEntity.setQuantity(quantity);
                jpaBookLibraryRepository.save(bookLibraryEntity);
            }
        );
    }

    @Override
    public boolean bookExistsInLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        return !findByLibraryIdAndBookId(libraryId, bookId).isEmpty();
    }

    @Override
    public Integer getBookQuantityInLibrary(LibraryId libraryId, BookId bookId) throws RepositoryException {
        return findByLibraryIdAndBookId(libraryId, bookId).stream()
            .reduce(0, (a, b) -> a + b.getQuantity(), Integer::sum);
    }

    @Override
    public List<String> getGenresInLibrary(LibraryId libraryId) throws RepositoryException {
        return jpaBookLibraryRepository.findGenresIdLibrary(libraryId.rawId());
    }
    
}
