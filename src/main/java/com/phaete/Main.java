package com.phaete;

public class Main {
    public static void main(String[] args) {

        ShopService shopServiceList = new ShopService(new OrderListRepo());
        ShopService shopServiceMap = new ShopService(new OrderMapRepo());
    }
}