package com.qrowdsy.application.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.qrowdsy.domain.repository.BookRepository;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.infrastructure.persistence.repository.BookRepositoryImpl;
import com.qrowdsy.infrastructure.persistence.repository.LibraryRepositoryImpl;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaBookLibraryRepository;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaBookRepository;
import com.qrowdsy.infrastructure.persistence.repository.jpa.JpaLibraryRepository;

@Configuration
@EnableJpaRepositories("com.qrowdsy.infrastructure.persistence.repository.jpa")
@EntityScan("com.qrowdsy.infrastructure.persistence.entity")
public class PersistenceConfiguration {
    
    @Bean public BookRepository bookRepository(JpaBookRepository jpaBookRepository, JpaBookLibraryRepository jpaBookLibraryRepository) {
        return new BookRepositoryImpl(jpaBookRepository, jpaBookLibraryRepository);
    }

    @Bean public LibraryRepository libraryRepository(JpaBookLibraryRepository jpaBookLibraryRepository, JpaBookRepository jpaBookRepository, JpaLibraryRepository jpaLibraryRepository) {
        return new LibraryRepositoryImpl(jpaLibraryRepository, jpaBookLibraryRepository, jpaBookRepository);
    }
    
}
