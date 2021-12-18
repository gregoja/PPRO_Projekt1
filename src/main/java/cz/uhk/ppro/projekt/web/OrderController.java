package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.model.OrderSummary;
import cz.uhk.ppro.projekt.service.ProductService;
import cz.uhk.ppro.projekt.web.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping("cart")
    public String renderCartPage(){
        return "cart";
    }

    @GetMapping("deliveryDetails")
    public String renderDeliveryDetailsPage(){
        return "deliveryDetails";
    }

    @GetMapping("summary")
    public String renderSummaryPage(HttpSession session, Model model){
        OrderSummary summary = (OrderSummary) session.getAttribute("orderSummary");
        if(summary == null) return "error";
        List<Product> productsToBeOrdered = new ArrayList<>();
        List<OrderSummary.CartItem> cartItems = summary.getCart();
        int totalPrice = 0;
        for (OrderSummary.CartItem item : cartItems){
            Product product = service.getById(item.getId()).orElseThrow(ResourceNotFoundException::new);
            totalPrice+= item.getAmmount() * product.getPrice();
            productsToBeOrdered.add(product);
        }
        model.addAttribute("orderSummary",summary);
        model.addAttribute("productsToBeOrdered",productsToBeOrdered);
        model.addAttribute("totalPrice",totalPrice);



        //session.removeAttribute("orderSummary");
        return "summary";
    }

    //TODO zmenit nazev tohoto endpointu
    @PostMapping("deliveryDetails")
    public void validateDeliveryDetails(@RequestBody @Valid OrderSummary orderSummary, HttpSession session){
        session.setAttribute("orderSummary",orderSummary);
        System.out.println(orderSummary);
    }
}