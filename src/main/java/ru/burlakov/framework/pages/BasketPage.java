package ru.burlakov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class BasketPage extends BasePage{
    @FindBy(xpath = "/html/head/title")
    private List<WebElement> pageTitle;
    @FindBy(xpath = "//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]")
    private WebElement checkRadioButton;
    @FindBy(xpath = "//span[@class=\"price__current\"]")
    private List<WebElement> priceProducts;
    @FindBy(xpath = "//button[@class=\"menu-control-button\" and contains(.,'Удалить')]")
    private List<WebElement> deleteProductlist;
    @FindBy(xpath = "//span[@class=\"cart-link__badge\"]")
    private WebElement amountIcon;
    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    private WebElement basketIcon;
    @FindBy (xpath = "//div[@class=\"cart-items__product\"]")
    private WebElement cardProduct;
    @FindBy(xpath = "//button[@data-commerce-action=\"CART_ADD\"]")
    private WebElement plusButton;
    @FindBy(xpath = "//div[@class=\"total-amount__label\"]//span[@class=\"price__current\"]")
    private WebElement totalPrice;
    @FindBy(xpath = "//span/span[@class=\"restore-last-removed\"]")
    private WebElement returnProductButton;
    @FindBy (xpath = "//a[@class=\"ec-promo\"]")
    private WebElement start;
    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage checkOpenBasketPage() {
        String value = "DNS – интернет магазин цифровой и бытовой техники по доступным ценам.";
        for(WebElement w : pageTitle)
            if(!w.getText().isEmpty())
                Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому", value, w.getText());
        return this;
    }
    /**
     * Проверка соответствия выбора гарантии
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage checkGaranty(){
        Assert.assertTrue("Не выбрана гарантия на 24 месяца!",checkRadioButton
                .getText()
                .equalsIgnoreCase("+ 24 мес."));
        return this;
    }
    /**
     * Проверка соответствия цены выбранным товарам
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage priceCondition(){
        boolean condition = false;
        int PSG = products.get(0).getPriceGarant();
        int Detroit = products.get(1).getPrice();
        int summ = PSG+Detroit;
        for(WebElement e : priceProducts){
            if(getPrice(e)==PSG || getPrice(e)==Detroit || getPrice(e)==summ)
                condition = true;
            else
                condition = false;
        }
        Assert.assertTrue("Цены на товары не совпадают",condition);
        return this;
    }
    /**
     * Удаление продукта соответствующей кнопкой
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage deleteProduct(String param){
        for(WebElement e : deleteProductlist) {
            if(e.findElement(By.xpath("./../../..//a[contains(.,'')]")).getText().contains(param)) {
                waitUtilElementToBeVisible(e);
                elementToBeClickable(e).click();

            }

        }
        return this;
    }
    /**
     * Проверка соответствия цены выбранным товарам
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage checkDeleteProduct(){
        boolean condition = false;
        wait.until(ExpectedConditions.textToBePresentInElement(basketIcon,getPriceString(products.get(0).getPriceGarant())));
        wait.until(ExpectedConditions.textToBePresentInElement(amountIcon,getPriceString(1)));
        if(getPrice(amountIcon)==1 && getPrice(basketIcon)==products.get(0).getPriceGarant())
            condition=true;
        Assert.assertTrue("Количество или цена в корзине не верны!",condition);
        return this;
    }
    /**
     * Добавление продукта посредством кнопки "+"
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage addProduct(){
        scrollToElementJs(cardProduct);
        elementToBeClickable(plusButton).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(basketIcon,getPriceString(products.get(0).getPriceGarant()))));
        return this;
    }
    /**
     * Проверка соответствия цены выбранным товарам
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage checkTotalPrice(){
        int total = products.get(0).getPriceGarant()*3;
        boolean condition = false;
        wait.until(ExpectedConditions.textToBePresentInElement(totalPrice,getPriceString(total)));
        if (getPrice(totalPrice) == total)
                condition = true;
        Assert.assertTrue("Цена товаров отличается от общей стоимости!",condition);
        return this;
    }
    /**
     * Возврат удаленного товара кнопкой "вернуть удаленный товар"
     *
     * @return BasketPage - т.е. остаемся на этой странице
     */
    public BasketPage returnDeleteProduct(){
        int total = products.get(0).getPriceGarant()*3+products.get(1).getPrice();
        scrollToElementJs(start);
        returnProductButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(totalPrice,getPriceString(total)));
        boolean condition = false;
        if(getPrice(totalPrice)==total)
            condition=true;
        Assert.assertTrue("Цена товаров отличается от общей стоимости!",condition);
        return this;
    }
}
