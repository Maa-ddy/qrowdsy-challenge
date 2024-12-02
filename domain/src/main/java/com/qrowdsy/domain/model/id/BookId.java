package com.qrowdsy.domain.model.id;

import java.util.UUID;

public final class BookId extends ModelId<UUID> {

    private BookId(UUID rawId) {
        super(rawId);
    }

    public BookId() {
        this(UUID.randomUUID());
    }

    public static BookId of(UUID rawId) {
        return new BookId(rawId);
    }

    @Override
    protected UUID copyRawId(UUID rawId) {
        return rawId;
    }

    
    
}
