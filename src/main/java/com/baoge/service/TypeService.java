package com.baoge.service;

import com.baoge.entity.Type;

import java.util.List;

public interface TypeService {
    int saveType(Type type);

    Type getType(long id);

    List<Type> getAllType();

    Type getTypeByName(String name);

    int updateType(long id,Type type);

    void deleteType(long id);


    List<Type> listTypeTop();
}
