package ru.geekbrains.qa.level3.lession6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProtoPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    public static final String CRM_URL = "https://b24-in8ijg.bitrix24.ru";
    public static final String TOP_MENU_LOCATOR_BY_ID = "crm_control_panel_menu_menu_crm_contact";


    public ProtoPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }



}


// когда используем задержку по локатору: somethingByElementLocated(By...) - как её обработать в методе класса страницы?
// там же у нас сплошные WebElements...
// Ну то есть:
//      LoginPage inputLogin() {
//        wait.until
//        loginInputField.sendKeys(LOGIN);
//        return this;
//    }
// (loginPage.wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.id("crm_control_panel_menu_menu_crm_contact")));
