package com.qrowdsy.infrastructure.event.handler;

import com.qrowdsy.domain.event.BookLeasedEvent;
import com.qrowdsy.domain.event.handler.EventHandler;
import com.qrowdsy.domain.exception.EventHandlingException;

public class SendEmailOnBookLeasedEventHandler implements EventHandler<BookLeasedEvent> {

    @Override
    public void handle(BookLeasedEvent operation) throws EventHandlingException {
        // send an email
    }

    @Override
    public void onSuccess(BookLeasedEvent operation) {
        // log, update db, ...
    }

    @Override
    public void onFailure(EventHandlingException e, BookLeasedEvent operation) {
        // log, update db, ...
    }
    
}
