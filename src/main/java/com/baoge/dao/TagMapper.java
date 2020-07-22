package com.baoge.dao;

import com.baoge.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    int saveTag(Tag Tag);

    Tag getTag(Long id);

    List<Tag> getAllTag();

    Tag getTagByName(String name);

    int updateTag(Tag Tag);

    void deleteTag(Long id);

    Tag getAllTagById(String tagId);

    List<Tag> listTagTop();
}
