package ru.geekbrains.qa.level3.lession6;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ProtoPage{

    // CONSTANTS
//    private static final String CRM_URL = "https://b24-in8ijg.bitrix24.ru";
    private static final String LOGIN = "gleb.smirnov@me.com";
    private static final String PASSWORD = "Bitrix24-gbuser";


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Web Elements
    @FindBy(id = "login")
    WebElement loginInputField;

    @FindBy(id = "password")
    WebElement passwordInputField;

    @FindBy(xpath = "//button[@data-action='submit']")
    WebElement submitLoginButton;


    // Methods
    LoginPage inputLogin() {
        loginInputField.sendKeys(LOGIN);
        return this;
    }

    LoginPage inputPassword() {
        passwordInputField.sendKeys(PASSWORD);
        return this;
    }

    LoginPage clickNextButton() throws InterruptedException {
        Thread.sleep(100);
        submitLoginButton.click();
        return this;
    }


    void login() throws InterruptedException {
        driver.findElement(By.id("login")).sendKeys(LOGIN);
        Thread.sleep(100);
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//label[@for='remember']")).click();
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
    }


}
