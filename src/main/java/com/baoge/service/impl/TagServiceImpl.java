package com.baoge.service.impl;

import com.baoge.NotFoundException;
import com.baoge.dao.TagMapper;
import com.baoge.entity.Tag;
import com.baoge.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagMapper TagMapper;

    @Override
    public int saveTag(Tag Tag) {
        return TagMapper.saveTag(Tag);
    }

    @Override
    public Tag getTag(long id) {
        return TagMapper.getTag(id);
    }

    @Override
    public List<Tag> getAllTag() {
        return TagMapper.getAllTag();
    }

    @Override
    public Tag getTagByName(String name) {
        return TagMapper.getTagByName(name);
    }

    @Override
    public int updateTag(long id, Tag Tag) {
        Tag t = TagMapper.getTag(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        return TagMapper.updateTag(Tag);
    }

    @Override
    public void deleteTag(long id) {
        TagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> listTag(String tagIds) {//1,2,3
        List<Tag> list = new ArrayList<Tag>();
        if (!"".equals(tagIds) && tagIds != null) {
            String[] idarray = tagIds.split(",");
            for (int i = 0; i < idarray.length; i++) {
               list.add(TagMapper.getAllTagById(idarray[i]));
            }
            return list;
        }
        return null;
    }

    @Override
    public List<Tag> listTagTop() {
        return TagMapper.listTagTop();
    }

}
