package com.qrowdsy.application.controller.dto;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public record BookDto(UUID id, String name, String author, String genre, @DateTimeFormat(pattern="yyyy-MM-dd") Date releaseDate) {
}
