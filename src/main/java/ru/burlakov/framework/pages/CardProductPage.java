package ru.burlakov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.burlakov.framework.utils.Product;
import java.util.List;

public class CardProductPage extends BasePage{
    @FindBy(xpath = "/html/head/title")
    private List<WebElement> pageTitle;
    @FindBy(xpath = "//div[@class=\"product-card-price__current-wrap\"]/span")
    private WebElement priceIcon;
    @FindBy(xpath = "//select")
    private WebElement garantySelect;
    @FindBy(xpath = "//button[contains(.,'Купить')]")
    private WebElement buyButton;
    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return CardProductPage - т.е. остаемся на этой странице
     */
    public CardProductPage checkCardProductPage() {
        String value = "Купить Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры в интернет магазине DNS." +
                " Характеристики, цена PlayStation 4 Slim Black 1 TB | 1688353";
        for(WebElement w : pageTitle)
            if(!w.getText().isEmpty())
                Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому", value, w.getText());
        return this;
    }
    /**
     *выбрать гарантию - 2 года\запомнить цены
     * @return CardProductPage - т.е. остаемся на этой странице
     */
    public CardProductPage chooseGaranty(){
        waitUtilElementToBeVisible(priceIcon);
        products.add(new Product("SP",getPrice(priceIcon)));
        //выбираем гарантию 2 года
        garantySelect.click();
        new Select(garantySelect).selectByVisibleText("2 года");
        garantySelect.click();
        waitUtilElementToBeVisible(priceIcon);
        //копируем измененную цену
        products.get(0).setPriceGarant(getPrice(priceIcon));
        return this;
    }

    /**
     * нажать на кнопку купить
     * @return StartPage
     */
    public StartPage buyProduct(){
        elementToBeClickable(buyButton).click();
        return app.getStartPage();
    }
}
