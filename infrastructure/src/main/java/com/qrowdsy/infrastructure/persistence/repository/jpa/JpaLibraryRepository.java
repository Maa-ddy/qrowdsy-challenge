package com.qrowdsy.infrastructure.persistence.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrowdsy.infrastructure.persistence.entity.LibraryEntity;

public interface JpaLibraryRepository extends JpaRepository<LibraryEntity, UUID> {
    
}
