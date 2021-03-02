package com.boc.test.common.mapper;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps a domain object to a response DTO
 *
 * @param <S> Source domain class
 * @param <R> Response class
 */
public interface ResponseMapper<S, R> {

    R toResponse(S object);

    default List<R> toResponseList(List<S> objects) {
        if(objects == null) {
            return null;
        }

        return objects.stream().map(this::toResponse).collect(Collectors.toList());
    }
}