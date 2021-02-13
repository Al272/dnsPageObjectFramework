package ru.burlakov.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author Alexey_Burlakov
 * Стартовая страница приложения https://www.dns-shop.ru/
 */
public class StartPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']/..//span[2]")
    private WebElement searchButton;

    /**
     * Функция поиска по сайту
     *
     * @param request - наименование поля
     * @return SearchPage - т.е. остаемся на этой странице
     */
    public SearchPage selectSearchDNS(String request) {
                    elementToBeClickable(searchBox).click();
                    searchBox.sendKeys(request);
                    searchButton.click();
                    return app.getSearchPage().checkOpenSearchPage();
    }


}
