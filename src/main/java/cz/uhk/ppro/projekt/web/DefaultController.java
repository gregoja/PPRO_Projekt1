package cz.uhk.ppro.projekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/index")
    public String renderIndexPage(Model model){
        //model.addAttribute("title","CUKRÁRNA U RAKVIČKY");
        return  "products";
    }

    @GetMapping("/")
    public String renderSlashPage(Model model){
        return renderIndexPage(model);
    }

    @GetMapping(value={"/uc"})
    public String renderUcPage(){
        return "uc";
    }
}