package com.qrowdsy.domain.model.id;

public abstract class ModelId<T> {

    private final T rawId;

    protected ModelId(T rawId) {
        this.rawId = rawId;
    }

    public boolean equals(ModelId<T> other) {
        return rawId.equals(other.rawId);
    }

    public T rawId() {
        return copyRawId(rawId);
    }

    protected abstract T copyRawId(T rawId);
    
}
