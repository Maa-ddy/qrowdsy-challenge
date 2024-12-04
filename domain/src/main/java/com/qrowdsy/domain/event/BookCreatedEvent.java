package com.qrowdsy.domain.event;

import com.qrowdsy.domain.model.id.BookId;

public record BookCreatedEvent(BookId bookId) implements Event {
    
}
