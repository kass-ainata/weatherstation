package com.bankcanada.climate.common;

/**
 * Maps a request DTO to a domain object
 * @param <R> Request class
 * @param <T> Target domain class
 */
public interface RequestConverter<R, T> {
    T fromRequest(R Request);
}
