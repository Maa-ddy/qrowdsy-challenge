package com.qrowdsy.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qrowdsy.application.controller.BookController;
import com.qrowdsy.application.controller.LibraryController;
import com.qrowdsy.domain.event.BookCreatedEvent;
import com.qrowdsy.domain.event.BookLeasedEvent;
import com.qrowdsy.domain.event.Event;
import com.qrowdsy.domain.event.queue.EventQueue;
import com.qrowdsy.domain.event.queue.impl.EventQueueImpl;
import com.qrowdsy.domain.repository.BookRepository;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.BookService;
import com.qrowdsy.domain.service.LibraryManagementService;
import com.qrowdsy.domain.service.LibrarySearchService;
import com.qrowdsy.domain.service.LibraryService;
import com.qrowdsy.domain.service.impl.BookServiceImpl;
import com.qrowdsy.domain.service.impl.LibraryManagementServiceImpl;
import com.qrowdsy.domain.service.impl.LibrarySearchServiceImpl;
import com.qrowdsy.domain.service.impl.LibraryServiceImpl;
import com.qrowdsy.domain.util.TypeReference;
import com.qrowdsy.infrastructure.event.handler.PushNotificationOnBookLeasedEventHandler;
import com.qrowdsy.infrastructure.event.handler.SendEmailOnBookLeasedEventHandler;
import com.qrowdsy.infrastructure.event.handler.UpdateGenresOnBookCreatedEventHandler;

@Configuration
public class ApplicationConfiguration {

    @Bean public BookController bookController(BookService bookService) {
        return new BookController(bookService);
    }

    @Bean public LibraryController libraryController(
        LibraryService libraryService,
        LibrarySearchService librarySearchService,
        LibraryManagementService libraryManagementService
    ) {
        return new LibraryController(libraryService, librarySearchService, libraryManagementService);
    }

    @Bean public BookService bookService(BookRepository bookRepository, LibraryRepository libraryRepository, EventQueue<Event> queue) {
        return new BookServiceImpl(bookRepository, libraryRepository, queue);
    }

    @Bean public LibraryService libraryService(LibraryRepository libraryRepository) {
        return new LibraryServiceImpl(libraryRepository);
    }

    @Bean public LibrarySearchService librarySearchService(LibraryRepository libraryRepository, BookRepository bookRepository) {
        return new LibrarySearchServiceImpl(libraryRepository, bookRepository);
        
    }

    @Bean public LibraryManagementService libraryManagementService(LibraryRepository libraryRepository, EventQueue<Event> queue) {
        return new LibraryManagementServiceImpl(libraryRepository, queue);
    }

    @Bean public EventQueue<Event> eventQueue() {
        var queue = new EventQueueImpl<>();
        registerEventHandlers(queue);
        return queue;
    }

    private void registerEventHandlers(EventQueue<Event> queue) {
        var pushNotificationOnBookLeasedEventHandler = new PushNotificationOnBookLeasedEventHandler();
        queue.subscribe(pushNotificationOnBookLeasedEventHandler, new TypeReference<BookLeasedEvent>() {});

        var sendEmailOnBookLeasedEventHandler = new SendEmailOnBookLeasedEventHandler();
        queue.subscribe(sendEmailOnBookLeasedEventHandler, new TypeReference<BookLeasedEvent>() {});

        var updateGenresOnBookCreatedEventHandler = new UpdateGenresOnBookCreatedEventHandler();
        queue.subscribe(updateGenresOnBookCreatedEventHandler, new TypeReference<BookCreatedEvent>() {});
    }
    
}
