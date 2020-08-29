package com.baoge.service.impl;

import com.baoge.NotFoundException;
import com.baoge.dao.TypeMapper;
import com.baoge.entity.Type;
import com.baoge.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getType(long id) {
        return typeMapper.getType(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public int updateType(long id, Type type) {
        Type t = typeMapper.getType(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        return typeMapper.updateType(type);
    }

    @Override
    public void deleteType(long id) {
        typeMapper.deleteType(id);
    }

    @Override
    public List<Type> listTypeTop() {

        return typeMapper.listTypeTop();
    }

}
