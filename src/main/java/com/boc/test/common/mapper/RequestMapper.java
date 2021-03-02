package com.boc.test.common.mapper;

/**
 * Maps a request DTO to a domain object
 * @param <R> Request class
 * @param <T> Target domain class
 */
public interface RequestMapper<R, T> {
    T fromRequest(R Request);
}
