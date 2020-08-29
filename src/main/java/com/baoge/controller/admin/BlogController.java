package com.baoge.controller.admin;

import com.baoge.entity.Blog;
import com.baoge.entity.User;
import com.baoge.service.BlogService;
import com.baoge.service.TagService;
import com.baoge.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Blog> list = blogService.getAllBlogWithTypeName();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    //    搜索博客
    @PostMapping("/blogs/search")
    public String search(Blog blog, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        List<Blog> blogBySearch = blogService.getBlogBySearch(blog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    //跳转来到博客更改页面
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlogWithContent(id);
        blog.init();
        model.addAttribute("blog", blog);
        System.out.println(blog);
        return "admin/blogs-input";
    }

    //    博客新增
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //设置blog的user
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置blog的tags
        blog.setTags(tagService.listTag(blog.getTagIds()));
        if (blog.getId() != null) {
            int a = blogService.updateBlog(blog);
            int c = blogService.deleteBlogTag(blog.getId());
            int i = blogService.addBlogIdAndTags(blog.getId(),/*tagService.listTag(blog.getTagIds())*/blog.getTags());
            if (i == 0) {
                attributes.addFlashAttribute("message", "新增失败");
            } else {
                // int i = blogService.addBlogIdAndTags(blog.getId(),/*tagService.listTag(blog.getTagIds())*/blog.getTags());
                attributes.addFlashAttribute("message", "新增成功");
            }

        } else {


            int b = blogService.saveBlog(blog);
            //System.out.println("这个值是"+blog.getId());
   /*     for (int i=0;i<tagService.listTag(blog.getTagIds()).size();i++){
            System.out.println(tagService.listTag(blog.getTagIds()).get(i));

        }*/
            int i = blogService.addBlogIdAndTags(blog.getId(),/*tagService.listTag(blog.getTagIds())*/blog.getTags());
            if (b == 0) {
                attributes.addFlashAttribute("message", "新增失败");
            } else {
                // int i = blogService.addBlogIdAndTags(blog.getId(),/*tagService.listTag(blog.getTagIds())*/blog.getTags());
                attributes.addFlashAttribute("message", "新增成功");
            }
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlogTag(id);
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
