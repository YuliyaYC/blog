package com.cyy.web;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    //获取根路径
    @GetMapping("/")
    public String index(){
       // int i =9/0;
//        String blog =null;
//
//        if (blog == null) {
//            throw new NotFoundException("博客不存在");
//        }

        //System.out.println("-----------index----------");
        //返回index页
        return "index";
    }

    //获取博客路径
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    //获取标签页路径
    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    //获取分类页路径
    @GetMapping("/types")
    public String types(){
        return "types";
    }

    //获取归档页路径
    @GetMapping("/archieves")
    public String archieves(){
        return "archieves";
    }

    //获取关于我页路径
    @GetMapping("/about")
    public String about(){
        return "about";
    }




}
