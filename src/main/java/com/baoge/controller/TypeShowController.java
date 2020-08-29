package com.baoge.controller;

import com.baoge.entity.Blog;
import com.baoge.entity.Type;
import com.baoge.service.BlogService;
import com.baoge.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    //    分页查询分类
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop();
        //System.out.println("types:"+types);
    /*    for (Type type : types){
            System.out.println(type);
            System.out.println("--------");
            System.out.println(type.getBlogs());
            System.out.println("========");}*/
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        PageHelper.startPage(pageNum, 10000);
        List<Blog> blogs = blogService.getByType(id);

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
