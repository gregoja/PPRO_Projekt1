package cz.uhk.ppro.projekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
BEST PRACTICES
Názvy zdrojů: podstatná jména
Názvy operací: slovesa
Kolekce:
/customers
Konkrétní „instance“:
/customers/123
Provázané objekty:
/customers/123/orders
 */

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