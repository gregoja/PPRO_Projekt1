package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.model.OrderSummary;
import cz.uhk.ppro.projekt.service.OrderService;
import cz.uhk.ppro.projekt.service.ProductService;
import cz.uhk.ppro.projekt.web.errors.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.InsufficientResourcesException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
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
    public String renderSummaryPage(HttpSession session, Model model) throws InsufficientResourcesException {
        OrderSummary summary = (OrderSummary) session.getAttribute("orderSummary");
        if(summary == null) throw new InsufficientResourcesException();
        List<Product> productsToBeOrdered = new ArrayList<>();
        List<OrderSummary.CartItem> cartItems = summary.getCart();
        int totalPrice = 0;
        for (OrderSummary.CartItem item : cartItems){
            Product product = productService.getById(item.getId()).orElseThrow(ResourceNotFoundException::new);
            totalPrice+= item.getAmmount() * product.getPrice();
            productsToBeOrdered.add(product);
        }
        model.addAttribute("orderSummary",summary);
        model.addAttribute("productsToBeOrdered",productsToBeOrdered);
        model.addAttribute("totalPrice",totalPrice);

        session.setAttribute("productsToBeOrdered",productsToBeOrdered);
        return "summary";
    }

    @PostMapping("validateDeliveryDetails")
    @ResponseBody
    public void validateDeliveryDetails(@RequestBody @Valid OrderSummary orderSummary, HttpSession session){
        session.setAttribute("orderSummary",orderSummary);
    }

    @PostMapping("completeOrder")
    @ResponseBody
    @SuppressWarnings (value="unchecked")
    public void completeOrder(HttpSession session) throws InsufficientResourcesException {
        OrderSummary summary = (OrderSummary) session.getAttribute("orderSummary");
        List<Product> productsToBeOrdered = (List<Product>) session.getAttribute("productsToBeOrdered");
        if(summary == null || productsToBeOrdered == null) throw new InsufficientResourcesException();
        orderService.save(summary, productsToBeOrdered);
        session.removeAttribute("orderSummary");
        session.removeAttribute("productsToBeOrdered");
    }
}