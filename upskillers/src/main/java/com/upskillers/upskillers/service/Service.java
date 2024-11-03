package com.upskillers.upskillers.service;

import java.util.List;

import com.upskillers.upskillers.exceptions.ResponseCustom;

public interface Service <T, I> {
    T get(I id);
    ResponseCustom add(T t);
    ResponseCustom update(T t);
    ResponseCustom delete(I id);
    List<T> getAll();
}
