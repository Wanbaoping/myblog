package com.baoge.controller;


import com.baoge.entity.Blog;
import com.baoge.entity.Tag;
import com.baoge.service.BlogService;
import com.baoge.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop();
        if (id == -1) {
           id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        PageHelper.startPage(pageNum,1000);
        List<Blog> blogs = blogService.getByTag(id);

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
