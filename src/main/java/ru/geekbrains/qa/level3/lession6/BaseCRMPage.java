package ru.geekbrains.qa.level3.lession6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseCRMPage extends ProtoPage{

    public BaseCRMPage(WebDriver driver) {
        super(driver);
    }

    // Left menu items

    @FindBys({
            @FindBy(xpath = "//div[@id='menu-items-block']"),
            @FindBy(xpath = "div[@class='menu-items-header']")
    })
    WebElement sandwichLeftMenuItem;

    /**
     * all Left menu items
     */


    // Top menu items

    @FindBy(xpath = "//div[@class='main-buttons']")
    WebElement mainMenu;


    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Сделки']")
    WebElement dealsTopMenuItem;

        @FindBy(xpath = "//div[@data-text='Сделки']")
        WebElement dealsCounter;

        @Step("Получить значение счётчика Сделок")
        int getDealsCounter() {
            return Integer.parseInt(dealsCounter.getAttribute("data-counter"));
        }

    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Контакты']")
    WebElement contactsTopMenuItem;

    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Компании']")
    WebElement companiesTopMenuItem;

    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Счета']")
    WebElement invoicesTopMenuItem;

    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Аналитика']")
    WebElement analitycsTopMenuItem;

    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Товары']")
    WebElement goodsTopMenuItem;

    @FindBy(xpath = "//span[@class='main-buttons-item-text-title' and text()='Складской учёт']")
    WebElement warehouseTopMenuItem;

    @FindBy(xpath = "//div[@id='crm_control_panel_menu_more_button']")
    WebElement moreTopMenuItem;






}
