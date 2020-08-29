package com.baoge.service;


import com.baoge.entity.Tag;

import java.util.List;

public interface TagService {
    int saveTag(Tag Tag);

    Tag getTag(long id);

    List<Tag> getAllTag();

    Tag getTagByName(String name);

    int updateTag(long id, Tag Tag);

    void deleteTag(long id);

    List<Tag> listTag(String tagIds);

    List<Tag> listTagTop();
}
