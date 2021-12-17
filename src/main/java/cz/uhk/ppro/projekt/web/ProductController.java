package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.service.ProductService;
import cz.uhk.ppro.projekt.web.errors.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     *  /products?categories=Donut&tags=Sleva,Novinka&pageNo=1
     *
     */
    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProductsByCriteria(@RequestParam(value = "categories",required = false) String categories,
                                               @RequestParam(value = "tags", required = false) String tags,
                                               @RequestParam(value = "pageNo",required = false, defaultValue = "0") int pageNo){
        List<String> desiredCategories = new ArrayList<>();
        List<String> desiredTags = new ArrayList<>();
        if(tags != null){
            desiredTags = Arrays.asList(tags.split(","));
        }
        if(categories != null){
            desiredCategories = Arrays.asList(categories.split(","));
        }
        return productService.getByCriteria(desiredCategories, desiredTags, PageRequest.of(pageNo,9));
    }

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable long id){
        return productService.getById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam(value = "productId") int productId, Model model){
        Product product = productService.getById(productId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("product",product);
        return "product";
    }
}