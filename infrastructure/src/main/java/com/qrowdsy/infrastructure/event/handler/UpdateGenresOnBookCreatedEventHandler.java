package com.qrowdsy.infrastructure.event.handler;

import com.qrowdsy.domain.event.BookCreatedEvent;
import com.qrowdsy.domain.event.handler.EventHandler;
import com.qrowdsy.domain.exception.EventHandlingException;

public class UpdateGenresOnBookCreatedEventHandler implements EventHandler<BookCreatedEvent> {

    @Override
    public void handle(BookCreatedEvent operation) throws EventHandlingException {
        System.out.println("Book created");
    }

    @Override
    public void onSuccess(BookCreatedEvent operation) {
    }

    @Override
    public void onFailure(EventHandlingException e, BookCreatedEvent operation) {
    }
    
}
