package cz.uhk.ppro.projekt.controllers;

import cz.uhk.ppro.projekt.model.Product;
import cz.uhk.ppro.projekt.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("products")
    @ResponseBody
    public List<Product> getProducts(){
        return productDao.getProducts();
    }

    @GetMapping(value = "products",params = "productId")
    @ResponseBody
    public Product getProductById(@RequestParam(value = "productId") int productId){
        return productDao.getById(productId);
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam(value = "productId") int productId, Model model){
        model.addAttribute("product",productDao.getById(productId));
        return "product";
    }

}
