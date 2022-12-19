package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class BlogController {
    @GetMapping("/blog")
    public String blogMain(Model model){
        Map<String, Object> m = model.asMap();
        int i=0;
        return "blog-main";
    }
    @GetMapping("/blog/rr")
    public String blogMain2(HttpServletRequest request,Model model ){
        model.addAttribute("name", "hello "+request.getParameter("name"));
        int i=0;
        return "rr";
    }
}
