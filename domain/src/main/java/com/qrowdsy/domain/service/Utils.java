package com.qrowdsy.domain.service;

public class Utils {
    public static <T> T getOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
