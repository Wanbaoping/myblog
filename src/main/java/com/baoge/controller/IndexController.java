package com.baoge.controller;

import com.baoge.entity.Blog;
import com.baoge.entity.Tag;
import com.baoge.entity.Type;
import com.baoge.service.BlogService;
import com.baoge.service.TagService;
import com.baoge.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
 *首页控制器
 * */
@Controller
public class IndexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    //通过get方式请求路径
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, RedirectAttributes attributes) {
        PageHelper.startPage(pageNum, 8);
        List<Blog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<Blog> recommendBlogs = blogService.getAllRecommendBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(allFirstPageBlog);
/*        for (Blog blog : pageInfo.getList())
       {
            System.out.println(blog);
            System.out.println("--------");
            System.out.println("========");
        }*/
        model.addAttribute("pageInfo", pageInfo);
        //System.out.println(recommendBlogs);
        model.addAttribute("recommendBlogs", recommendBlogs);
        List<Type> types = typeService.listTypeTop();
        model.addAttribute("types", types);
/*        for (Type type : types){
           System.out.println(type);
            System.out.println("--------");
            System.out.println(type.getBlogs());
            System.out.println("========");
        }*/
        List<Tag> tags = tagService.listTagTop();
        model.addAttribute("tags", tags);
/*        for (Tag tag : tags) {
            System.out.println(tag);
            System.out.println("--------");
            System.out.println(tag.getBlogs());
            System.out.println("========");
        }*/

        return "index";
    }

    //    搜索博客
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<Blog> searchBlog = blogService.getSerachBlogbyName(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    //    跳转博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog detailedBlog = blogService.getDetailedBlog(id);
        //List<Comment> comments = commentService.listCommentByBlogId(id);
        //model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

    //   最新博客列表
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        List<Blog> newBlog = blogService.getNewBlog();
        model.addAttribute("newblogs", newBlog);
        return "_fragments :: newblogList";
    }
}
