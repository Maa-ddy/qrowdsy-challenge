package com.qrowdsy.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="book_library")
public class BookLibraryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @ManyToOne private BookEntity book;
    @ManyToOne private LibraryEntity library;
    private Integer quantity;

    public BookLibraryEntity() {}
    
    public BookLibraryEntity(BookEntity book, LibraryEntity library, Integer quantity) {
        this.book = book;
        this.library = library;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
