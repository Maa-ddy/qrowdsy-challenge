package com.qrowdsy.infrastructure.persistence.repository.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qrowdsy.infrastructure.persistence.entity.BookLibraryEntity;


public interface JpaBookLibraryRepository extends JpaRepository<BookLibraryEntity, UUID> {

    List<BookLibraryEntity> findByBookId(UUID bookId);

    Page<BookLibraryEntity> findByLibraryId(UUID libraryId, Pageable pageable);

    List<BookLibraryEntity> findByLibraryIdAndBookId(UUID libraryId, UUID bookId);

    @Query("SELECT DISTINCT bl.book.genre FROM book_library bl WHERE bl.library.id = :libraryId")
    List<String> findGenresIdLibrary(UUID libraryId);

}
