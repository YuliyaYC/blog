package com.cyy.web.admin;

import com.cyy.po.Tag;
import com.cyy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/adminTag")
    public String tags(@PageableDefault(size = 3, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTag(pageable));

        tagService.listTag(pageable);
        return "admin/adminTag";

    }

    @GetMapping("/adminTag/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/adminNewTag";
    }

    @GetMapping("/adminTag/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/adminNewTag";
    }

    @PostMapping("/adminTag")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {

        Tag t2 = tagService.getTagByName(tag.getName());
        if (t2 != null) {
            result.rejectValue("name", "nameError", "该标签不能添加重复分类");
        }

        if (result.hasErrors()) {
            return "admin/adminNewTag";
        }

        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/adminTag";
    }

    @PostMapping("/adminTag/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,
                           @PathVariable Long id, RedirectAttributes attributes) {

        Tag t3 = tagService.getTagByName(tag.getName());
        if (t3 != null) {
            result.rejectValue("name", "nameError", "该分类不能添加重复分类");
        }

        if (result.hasErrors()) {
            return "admin/adminNewTag";
        }

        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/adminTag";
    }

    @GetMapping("/adminTag/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "操作成功");
        return "redirect:/admin/adminTag";
    }

}


