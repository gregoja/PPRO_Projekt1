package cz.uhk.ppro.projekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/index")
    public String renderIndexPage(){
        return "products";
    }

    @GetMapping("/")
    public String renderSlashPage(){
        return renderIndexPage();
    }

    @GetMapping(value={"/uc"})
    public String renderUcPage(){
        return "uc";
    }
}