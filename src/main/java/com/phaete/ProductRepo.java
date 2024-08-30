package com.phaete;

import java.util.*;

public class ProductRepo {

    // Variables
    private Map<Product, Integer> productRepo = new HashMap<>();

    // Constructors


    // Methods
    public void addProduct(Product product, Integer stock) {
        productRepo.put(product, stock);
    }

    public void removeProduct(Product product) {
        productRepo.remove(product);
    }

    public Product getProduct(int productId) {
        for (Product product : productRepo.keySet()) {
            if (product.id() == productId) {
                return product;
            }
        }
        return null; // return null if the product id is not in the list
    }

    public void increaseStock(Product product, int quantity) {
        if (productRepo.containsKey(product)) {
                productRepo.put(product, productRepo.get(product) + quantity);
        } else {
            System.out.println("Can not change the stock of the product: " + product.id() + " as it is not in the list.");
        }
    }

    public void decreaseStock(Product product, int quantity) {
        if (productRepo.containsKey(product) && hasEnoughStock(product, quantity)) {
            productRepo.put(product, productRepo.get(product) - quantity);
        } else {
            System.out.println("Can not change the stock of the product: " + product.id() + " as it is not in the list.");
        }
    }

    public boolean hasEnoughStock(Product product, int quantity) {
        if (productRepo.containsKey(product)) {
            return productRepo.get(product) >= quantity;
        } else {
            return false;
        }
    }

    public Set<Product> getProducts() {
        return productRepo.keySet();
    }

    public Map<Product, Integer> getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(Map<Product, Integer> productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public String toString() {
        return "ProductRepo{" +
                "productRepo=" + productRepo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(productRepo, that.productRepo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productRepo);
    }
}
