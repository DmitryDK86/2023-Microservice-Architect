package ru.ddk.simplewebservice.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SwaggerController {

    @GetMapping("/sw")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
