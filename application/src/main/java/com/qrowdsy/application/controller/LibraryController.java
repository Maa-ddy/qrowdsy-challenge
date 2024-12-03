package com.qrowdsy.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qrowdsy.application.controller.dto.LibraryDto;
import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.Address;
import com.qrowdsy.domain.model.id.BookId;
import com.qrowdsy.domain.model.id.LibraryId;
import com.qrowdsy.domain.service.LibraryManagementService;
import com.qrowdsy.domain.service.LibrarySearchService;
import com.qrowdsy.domain.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {
    
    private final LibraryService libraryService;
    private final LibrarySearchService librarySearchService;
    private final LibraryManagementService libraryManagementService;

    public LibraryController(
        LibraryService libraryService, 
        LibrarySearchService librarySearchService, 
        LibraryManagementService libraryManagementService
    ) {
        this.libraryService = libraryService;
        this.librarySearchService = librarySearchService;
        this.libraryManagementService = libraryManagementService;
    }

    @PostMapping
    public UUID addLibrary(LibraryDto libraryDto) throws DomainException {
        return libraryService.createLibrary(
            libraryDto.name(),
            libraryDto.town(),
            new Address(libraryDto.town(), libraryDto.postCode()),
            libraryDto.phoneNumber(),
            libraryDto.emailAddress()
        ).rawId();
    }   

    @GetMapping("/{libraryId}")
    public LibraryDto getLibrary(UUID libraryId) throws DomainException {
        return LibraryDto.from(libraryService.findLibrary(LibraryId.of(libraryId)));
    }

    @GetMapping("/{libraryId}/genres")
    public List<String> getLibraryGenres(UUID libraryId) throws DomainException {
        return librarySearchService.getGenresInLibrary(LibraryId.of(libraryId));
    }

    @PostMapping("/{libraryId}/books/{bookId}")
    public void addBookToLibrary(UUID libraryId, UUID bookId, Integer quantity) throws DomainException {
        libraryManagementService.addBooksToLibrary(LibraryId.of(libraryId), BookId.of(bookId), 1);
    }
    
}
