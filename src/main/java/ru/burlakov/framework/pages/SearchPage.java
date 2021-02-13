package ru.burlakov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.burlakov.framework.utils.Product;

import java.util.List;

public class SearchPage extends BasePage{
    @FindBy(xpath = "/html/head/title")
    private List<WebElement> pageTitle;
    @FindBy(xpath = "//a[contains(.,'PlayStation 4 Slim Black')]")
    private WebElement cardPS;
    @FindBy(xpath = "//button[contains(.,'Купить')]")
    private WebElement buyButton;
    @FindBy(xpath = "//div[@class=\"product-card-price__current-wrap\"]/span")
    private WebElement priceIcon;
    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    private WebElement basketIcon;
    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return SearchPage - т.е. остаемся на этой странице
     */
    public SearchPage checkOpenSearchPage() {
        String value = "playstation - DNS – интернет магазин цифровой и бытовой техники по доступным ценам.";
        for(WebElement w : pageTitle)
            if(!w.getText().isEmpty())
                Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому", value, w.getText());
        return this;
    }

    /**
     * Находим продукт и открываем карту продукта
     */
    public CardProductPage checkProduct(){
       elementToBeClickable(cardPS).click();
       return app.getCardProductPage().checkCardProductPage();
    }
    /**
     * Добавить продукт в корзину сразу из списка
     * Проверить соответствие цены продуктов к цене корзины
     */
    public SearchPage buyNow() {
        waitUtilElementToBeVisible(priceIcon);
        products.add(new Product("Detroit",getPrice(priceIcon)));
        elementToBeClickable(buyButton).click();
        int sum = products.get(0).getPriceGarant()+products.get(1).getPrice();
        wait.until(ExpectedConditions.textToBePresentInElement(basketIcon, getPriceString(sum)));
        Assert.assertTrue("Цены продуктов не совпадают с ценой указанной в корзине",
                getPrice(basketIcon)==products.get(0).getPriceGarant()+products.get(1).getPrice());
        return this;
    }
    /**
     * Переходим в корзину
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage goToBasket(){
        elementToBeClickable(basketIcon).click();
        return app.getBasketPage();
    }


}
