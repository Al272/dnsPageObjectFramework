package ru.burlakov.framework.test;


import org.junit.Test;
import ru.burlakov.framework.base.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {
        app.getStartPage()
               .selectSearchDNS("playstation")
                .checkProduct()
                .chooseGaranty()
                .buyProduct()
                .selectSearchDNS("Detroit")
                .buyNow()
                .goToBasket()
                .checkGaranty()
                .priceCondition()
                .deleteProduct("Detroit")
                .checkDeleteProduct()
                .addProduct()
                .addProduct()
                .checkTotalPrice()
                .returnDeleteProduct();
    }

}
