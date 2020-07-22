package com.baoge.dao;

import com.baoge.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);


    List<Type> listTypeTop();
}
