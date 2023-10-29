package com.project.tmc.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController {
    @GetMapping("/error-page")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String errorMassage(
            @RequestParam(name = "error_code") Integer errorCode,
            @RequestParam(name = "error_text") String errorText,
            @RequestParam(name = "previous_page",
                    defaultValue = "/") String previousPage,
            Model model) {
        model.addAttribute("error_code", errorCode);
        model.addAttribute("error_text", errorText);
        model.addAttribute("previous_page", previousPage);

        return "error/error-page";
    }
}