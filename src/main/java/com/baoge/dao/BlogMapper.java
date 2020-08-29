package com.baoge.dao;

import com.baoge.entity.Blog;
import com.baoge.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    Blog getBlog(Long id);

    List<Blog> getAllBlog();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> getAllBlogWithTypeName();

    List<Blog> getSearchBlog(Blog blog);

    int saveBlogAndTags(Long id, List<Tag> listTag);

    Blog getBlogWithContent(Long id);

    int deleteBlogTag(Long id);

    List<Blog> getAllFirstPageBlog();

    List<Blog> getAllRecommendBlog();

    List<Blog> getSearchBlogbyName(String query);

    Blog getDetailedBlog(Long id);

    //统计评论总数
    int getCommentCountById(Long blogId);

    //统计访问总数
    Integer getBlogViewTotal();

    int updateViews(Long id);

    List<Blog> getByTypeId(Long typeId);

    List<Blog> getByTag(Long tagId);

    List<String> finBlogYear();

    List<Blog> findBlogByYear(String year);

    List<Blog> getNewBlog();





    /*   int updateBlogTag(Long id, List<Tag> listTag);*/
}
