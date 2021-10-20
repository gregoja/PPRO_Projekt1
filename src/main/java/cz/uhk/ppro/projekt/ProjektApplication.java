package cz.uhk.ppro.projekt;

import cz.uhk.ppro.projekt.model.Product;
import cz.uhk.ppro.projekt.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ProjektApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    public static void main(String[] args) {
        SpringApplication.run(ProjektApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productDao.add(new Product(new BigDecimal("45.3"),"Homernut","Lorem Ipsum","boruvkonut.jpg"));
        productDao.add(new Product(new BigDecimal("45.8"),"Homernut","Lorem Ipsum","boruvkonut.jpg"));
        productDao.add(new Product(new BigDecimal("45.2"),"Homernut","Lorem Ipsum","boruvkonut.jpg"));
        productDao.add(new Product(new BigDecimal("45.4"),"Homernut","Lorem Ipsum","boruvkonut.jpg"));
        productDao.add(new Product(new BigDecimal("45.9"),"Homernut","Lorem Ipsum","boruvkonut.jpg"));
        productDao.add(new Product(new BigDecimal("45.12"),"Homernut","Lorem Ipsum","boruvkonut.jpg"));
    }
}
