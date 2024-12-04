package com.qrowdsy.domain.event;


import java.lang.reflect.Type;

public interface Event {
    default Type getType() {
        return this.getClass();
    }
}
