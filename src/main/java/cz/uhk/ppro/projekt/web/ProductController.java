package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.service.ProductService;
import cz.uhk.ppro.projekt.web.httpResponse.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products", params = {"pageNo","pageSize"})
    @ResponseBody()
    public List<Product> getPagedProducts(@RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize){
        return productService.getByCriteria(null,null,PageRequest.of(pageNo,pageSize));
    }


    @GetMapping(value = "/products")
    @ResponseBody()
    public List<Product> getAllProducts(){
        return getPagedProducts(0,9);
    }

    @GetMapping(value = "products",params = "productId")
    @ResponseBody
    public Product getProductById(@RequestParam(value = "productId") long productId){
        return productService.getById(productId).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam(value = "productId") int productId, Model model){
        Product product = productService.getById(productId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("product",product);
        return "product";
    }

    @GetMapping("wtf")
    @ResponseBody
    public List<Product> getProductsByCriteria(){
        List<String> desiredCategories = new ArrayList<>();
        List<String> desiredTags = new ArrayList<>();
        // desiredTags.add("Sleva");
        // desiredTags.add("Novinka");
        // desiredCategories.add("Donut");
        return productService.getByCriteria(desiredCategories, desiredTags, PageRequest.of(0,5));
    }
}