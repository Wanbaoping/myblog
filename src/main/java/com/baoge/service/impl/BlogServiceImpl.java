package com.baoge.service.impl;

import com.baoge.NotFoundException;
import com.baoge.dao.BlogMapper;
import com.baoge.entity.Blog;
import com.baoge.entity.Tag;
import com.baoge.service.BlogService;
import com.baoge.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public Blog getBlogWithContent(Long id) {
        return blogMapper.getBlogWithContent(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    public List<Blog> getAllBlogWithTypeName() {
        return blogMapper.getAllBlogWithTypeName();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> getBlogBySearch(Blog blog) {
        return blogMapper.getSearchBlog(blog);
    }

    @Override
    public int addBlogIdAndTags(Long id, List<Tag> listTag) {
        return blogMapper.saveBlogAndTags(id, listTag);
    }

    @Override
    public int deleteBlogTag(Long id) {
        return blogMapper.deleteBlogTag(id);
    }

    @Override
    public List<Blog> getAllFirstPageBlog() {
        return blogMapper.getAllFirstPageBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSerachBlogbyName(String query) {
        return blogMapper.getSearchBlogbyName(query);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
//        文章访问数量自增
         blogMapper.updateViews(id);
//        文章评论数量更新
        blogMapper.getCommentCountById(id);
        return detailedBlog;
    }

    @Override
    public Integer getBlogViewTotal() {

        return blogMapper.getBlogViewTotal();
    }

    @Override
    public List<Blog> getByType(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    public List<Blog> getByTag(Long tagId) {
        return blogMapper.getByTag(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.finBlogYear();
        Set<String> set = new HashSet<>(years);
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findBlogByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.getAllBlog().size();
    }

    @Override
    public List<Blog> getNewBlog() {
        return blogMapper.getNewBlog();
    }



/*    @Override
    public int updateBlogTag(Long id, List<Tag> listTag) {
        return blogMapper.updateBlogTag(id,listTag);
    }*/


}
