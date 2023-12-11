package com.example.authentication.convertor;

public interface BaseConvertor<E,D> {
    E convertDto(D d);

    D convertEntity(E e);
}
