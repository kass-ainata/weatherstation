package com.bankcanada.climate.common;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Converts from domain object to a response one
 *
 */
public interface ResponseConverter<S, D> {

    D toResponseDto(S sourceObj);

    default List<D> toResponseList(List<S> sourceObjs) {
        return Optional.ofNullable(sourceObjs).get().stream().map(this::toResponseDto).collect(Collectors.toList());
    }
}