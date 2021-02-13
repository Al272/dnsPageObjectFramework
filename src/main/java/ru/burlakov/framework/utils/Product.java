package ru.burlakov.framework.utils;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private int price;
    private int priceGarant;
    public Product(String name, int price, int priceGarant) {
        this.name = name;
        this.price = price;
        this.priceGarant = priceGarant;
    }
    public Product(String name, int price) {
        this(name, price,0);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceGarant() {
        return priceGarant;
    }

    public void setPriceGarant(int priceGarant) {
        this.priceGarant = priceGarant;
    }
}
