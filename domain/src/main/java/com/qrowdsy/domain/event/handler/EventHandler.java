package com.qrowdsy.domain.event.handler;

import com.qrowdsy.domain.event.Event;
import com.qrowdsy.domain.exception.EventHandlingException;

public interface EventHandler<TEvent extends Event> {
    void handle(TEvent operation) throws EventHandlingException;
    void onSuccess(TEvent operation);
    void onFailure(EventHandlingException e, TEvent operation);
}
