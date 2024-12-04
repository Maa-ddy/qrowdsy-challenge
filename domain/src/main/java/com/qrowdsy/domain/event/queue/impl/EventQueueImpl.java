package com.qrowdsy.domain.event.queue.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.qrowdsy.domain.event.Event;
import com.qrowdsy.domain.event.handler.EventHandler;
import com.qrowdsy.domain.event.queue.EventQueue;
import com.qrowdsy.domain.exception.EventHandlingException;
import com.qrowdsy.domain.util.TypeReference;


public class EventQueueImpl<TEvent extends Event> implements EventQueue<TEvent> {

    private final Map<Type, List<EventHandler<TEvent>>> handlers;
    private final ExecutorService executor;

    public EventQueueImpl() {
        this.handlers = new HashMap<Type, List<EventHandler<TEvent>>>();
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    public <TSubEvent extends TEvent> void subscribe(
        EventHandler<TSubEvent> handler,
        TypeReference<TSubEvent> eventType
    ) {
        if (!this.handlers.containsKey(eventType.getType())) {
            this.handlers.put(eventType.getType(), new ArrayList<>());
        }
        this.handlers.get(eventType.getType()).add((EventHandler<TEvent>)handler);
    }

    @Override
    public <TSubEvent extends TEvent> void unsubscribe(EventHandler<TSubEvent> handler) {
        if (this.handlers.containsKey((new TypeReference<TEvent>(){}).getType())) {
            this.handlers.remove((new TypeReference<TEvent>(){}).getType());
        }
    }
    
    @Override
    public <TSubEvent extends TEvent> void publish(TSubEvent event) {
        for (var handler : handlers.getOrDefault(event.getType(), new ArrayList<>())) {
            CompletableFuture.runAsync(() -> {
                try {
                    handler.handle(event);
                } catch (EventHandlingException e) {
                    handler.onFailure(e, event);
                    throw new RuntimeException(e.getCause());
                }
            }, executor)
            .thenRun(() -> handler.onSuccess(event));
        }
    }

}