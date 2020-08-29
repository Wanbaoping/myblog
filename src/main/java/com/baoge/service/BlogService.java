package com.baoge.service;


import com.baoge.entity.Blog;
import com.baoge.entity.Tag;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getBlogWithContent(Long id);

    List<Blog> getAllBlog();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> getAllBlogWithTypeName();

    List<Blog> getBlogBySearch(Blog blog);

    int addBlogIdAndTags(Long id, List<Tag> listTag);

    int deleteBlogTag(Long id);

    /*最新博客列表实体类*/
    List<Blog> getAllFirstPageBlog();

    /*最新推荐实体类*/
    List<Blog> getAllRecommendBlog();

    List<Blog> getSerachBlogbyName(String query);

    Blog getDetailedBlog(Long id);

    //统计访问总数
    Integer getBlogViewTotal();

    List<Blog> getByType(Long typeId);

    List<Blog> getByTag(Long tagId);

    Map<String, List<Blog>> archiveBlog();

    int countBlog();

    List<Blog> getNewBlog();


    /* int updateBlogTag(Long id, List<Tag> listTag);*/
}

