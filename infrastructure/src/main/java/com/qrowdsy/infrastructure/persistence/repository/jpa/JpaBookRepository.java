package com.qrowdsy.infrastructure.persistence.repository.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qrowdsy.infrastructure.persistence.entity.BookEntity;

public interface JpaBookRepository extends JpaRepository<BookEntity, UUID> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM Book b WHERE b.name like '%' || :criteria || '%' or b.author like '%' || :criteria || '%' or b.genre like '%' || :criteria || '%' offset :offset limit :limit"
    )
    List<BookEntity> filterByCriteria(String criteria, Integer offset, Integer limit);


}
