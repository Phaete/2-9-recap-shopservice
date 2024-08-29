package com.phaete;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    // Variables
    private List<Product> productRepo= new ArrayList<>();

    // Constructors


    // Methods
    public void addProduct(Product product) {
        productRepo.add(product);
    }

    public void removeProduct(Product product) {
        productRepo.remove(product);
    }

    public Product getProduct(int productId) {
        for (Product product : productRepo) {
            if (product.id() == productId) {
                return product;
            }
        }
        return null; // return null if the product id is not in the list
    }

    public List<Product> getProducts() {
        return productRepo;
    }

}
