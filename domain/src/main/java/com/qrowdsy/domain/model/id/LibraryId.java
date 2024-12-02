package com.qrowdsy.domain.model.id;

import java.util.UUID;

public final class LibraryId extends ModelId<UUID> {

    protected LibraryId(UUID rawId) {
        super(rawId);
    }

    public LibraryId() {
        this(UUID.randomUUID());
    }

    public static LibraryId of(UUID rawId) {
        return new LibraryId(rawId);
    }

    @Override
    protected UUID copyRawId(UUID rawId) {
        return rawId;
    }
    
}
