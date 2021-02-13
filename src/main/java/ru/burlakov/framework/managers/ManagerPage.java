package ru.burlakov.framework.managers;


import ru.burlakov.framework.pages.*;

/**
 * @author Alexey_Burlakov
 * Класс для управления страничками
 */
public class ManagerPage {

    /**
     * Менеджер страничек
     */
    private static ManagerPage managerPage;

    /**
     * Стартовая страница
     */
    private StartPage startPage;

    /**
     * Страница поиска
     */
    private SearchPage searchPage;

    /**
     * Страница корзины
     */
    private BasketPage basketPage;

    /**
     * Страница продукта
     */
    private CardProductPage cardProductPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see ManagerPage#getManagerPage()
     */
    private ManagerPage() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static ManagerPage getManagerPage() {
        if (managerPage == null) {
            managerPage = new ManagerPage();
        }
        return managerPage;
    }
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link BasketPage}
     *
     * @return BasketPage
     */
    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }

    /**
     * Ленивая инициализация {@link CardProductPage}
     *
     * @return CardProductPage
     */
    public CardProductPage getCardProductPage() {
        if (cardProductPage == null) {
            cardProductPage = new CardProductPage();
        }
        return cardProductPage;
    }

    /**
     * Ленивая инициализация {@link SearchPage}
     *
     * @return SearchPage
     */
    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }
}

