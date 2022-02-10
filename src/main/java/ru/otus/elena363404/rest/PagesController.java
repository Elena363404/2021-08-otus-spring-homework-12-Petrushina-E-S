package ru.otus.elena363404.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/public")
    public String publicPage() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getPrincipal());
        return "public";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return "redirect:/";
    }

    @GetMapping("/success")
    public String successPage() {
        return "redirect:/";
    }


    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
