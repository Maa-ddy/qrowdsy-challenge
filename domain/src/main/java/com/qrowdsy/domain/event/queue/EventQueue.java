package com.qrowdsy.domain.event.queue;

import com.qrowdsy.domain.event.Event;
import com.qrowdsy.domain.event.handler.EventHandler;
import com.qrowdsy.domain.util.TypeReference;

public interface EventQueue<TEvent extends Event> {

    <TSubEvent extends TEvent> void subscribe(EventHandler<TSubEvent> handler, TypeReference<TSubEvent> type);

    <TSubEvent extends TEvent> void unsubscribe(EventHandler<TSubEvent> handler);

    <TSubEvent extends TEvent> void publish(TSubEvent event);

}