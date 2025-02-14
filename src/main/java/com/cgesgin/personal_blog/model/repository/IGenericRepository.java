package com.cgesgin.personal_blog.model.repository;

import java.util.List;

public interface IGenericRepository <T>{

    Boolean save(T value);
    Boolean remove(T value);
    Boolean update(T value);
    List<T> getAll();
    T get(String id);

}
