package com.cyy.web.admin;

import com.cyy.po.Type;
import com.cyy.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/adminType")
    public String types(@PageableDefault(size = 3, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));

        typeService.listType(pageable);
        return "admin/adminType";

    }

    @GetMapping("/adminType/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/adminNewType";
    }

    @GetMapping("/adminType/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/adminNewType";
    }

    @PostMapping("/adminType")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        Type t2 = typeService.getTypeByName(type.getName());
        if (t2 != null) {
            result.rejectValue("name", "nameError", "该分类不能添加重复分类");
        }

        if (result.hasErrors()) {
            return "admin/adminNewType";
        }

        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/adminType";
    }

    @PostMapping("/adminType/{id}")
    public String editPost(@Valid Type type, BindingResult result,
                           @PathVariable Long id, RedirectAttributes attributes) {

        Type t3 = typeService.getTypeByName(type.getName());
        if (t3 != null) {
            result.rejectValue("name", "nameError", "该分类不能添加重复分类");
        }

        if (result.hasErrors()) {
            return "admin/adminNewType";
        }

        Type t = typeService.updateType(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/adminType";
    }

    @GetMapping("/adminType/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "操作成功");
        return "redirect:/admin/adminType";
    }

}
