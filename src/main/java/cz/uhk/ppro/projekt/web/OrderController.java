package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.model.OrderSummary;
import cz.uhk.ppro.projekt.service.ProductService;
import cz.uhk.ppro.projekt.web.httpResponse.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
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


    /*@GetMapping("/product")
    public String getProduct(@RequestParam(value = "productId") int productId, Model model){
        Product product = productService.getById(productId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("product",product);
        return "product";
    }*/


    @GetMapping("summary")
    public String renderSummaryPage(HttpSession session, Model model){
        OrderSummary summary = (OrderSummary) session.getAttribute("orderSummary");
        //session.removeAttribute("orderSummary");
        List<Product> productsToBeOrdered = new ArrayList<>();
        List<OrderSummary.CartItem> cartItems = summary.getCart();
        int totalPrice = 0;
        for (OrderSummary.CartItem item : cartItems){
            // internal server error?
            Product product = service.getById(item.getId()).orElseThrow(ResourceNotFoundException::new);
            totalPrice+= item.getAmmount() * product.getPrice();
            productsToBeOrdered.add(product);
        }
        model.addAttribute("orderSummary",summary);
        model.addAttribute("productsToBeOrdered",productsToBeOrdered);
        model.addAttribute("totalPrice",totalPrice);
        return "summary";
    }

    @PostMapping("deliveryDetails")
    public void summaryRequested(@RequestBody OrderSummary orderSummary, HttpSession session){
        session.setAttribute("orderSummary",orderSummary);
        //TODO overeni validity vstupu od uzivatele
    }
}