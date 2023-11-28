package com.std.sd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/main")
    @ResponseBody
    public String main() {
        return "안녕하세요.";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/article/list";
    }
}
