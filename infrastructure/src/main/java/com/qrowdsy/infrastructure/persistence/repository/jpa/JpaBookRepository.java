package com.qrowdsy.infrastructure.persistence.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrowdsy.infrastructure.persistence.entity.BookEntity;

public interface JpaBookRepository extends JpaRepository<BookEntity, UUID> {

}
