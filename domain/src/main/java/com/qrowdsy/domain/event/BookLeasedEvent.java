package com.qrowdsy.domain.event;

import com.qrowdsy.domain.model.id.BookId;

public record BookLeasedEvent(BookId bookId) implements Event {
    
}
