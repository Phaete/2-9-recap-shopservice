package com.phaete;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductRepo {

    // Variables
    private List<Product> productRepo = new ArrayList<>();

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

    public void increaseStock(Product product, int quantity) {
        if (productRepo.contains(product)) {
            productRepo.set(
                    productRepo.indexOf(product),
                    new Product(
                            product.id(),
                            product.name(),
                            product.price(),
                            product.stock() + quantity
                    )
            );
        } else {
            System.out.println("Can not change the stock of the product: " + product.id() + " as it is not in the list.");
        }
    }

    public void decreaseStock(Product product, int quantity) {
        if (productRepo.contains(product)) {
            productRepo.set(
                    productRepo.indexOf(product),
                    new Product(
                            product.id(),
                            product.name(),
                            product.price(),
                            product.stock() - quantity
                    )
            );
        } else {
            System.out.println("Can not change the stock of the product: " + product.id() + " as it is not in the list.");
        }
    }

    public List<Product> getProducts() {
        return productRepo;
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

    @Override
    public String toString() {
        return "ProductRepo{" +
                "productRepo=" + productRepo +
                '}';
    }
}
