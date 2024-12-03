package com.qrowdsy.application.controller.dto;

import java.util.UUID;

import com.qrowdsy.domain.model.Library;

public record LibraryDto(UUID id, String name, String town, String postCode, String phoneNumber, String emailAddress) {
    public static LibraryDto from(Library library) {
        return new LibraryDto(
            library.id().rawId(),
            library.name(),
            library.address().town(),
            library.address().postCode(),
            library.phoneNumber(),
            library.emailAddress()
        );
    }
}
