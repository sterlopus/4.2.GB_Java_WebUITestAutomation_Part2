package ru.geekbrains.qa.level3.lession6;

import io.qameta.allure.Step;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ProtoPage {


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
    @Step("Ввод значения логина")
    LoginPage inputLogin() {
        loginInputField.sendKeys(Bitrix24Constants.LOGIN);
        return this;
    }

    @Step("Ввод значения пароля")
    LoginPage inputPassword() {
        passwordInputField.sendKeys(Bitrix24Constants.PASSWORD);
        return this;
    }

    @Step("клик на кнопке ДАЛЬШЕ")
    LoginPage clickNextButton() throws InterruptedException {
        Thread.sleep(200);  // cannot replace with 'wait'
        submitLoginButton.click();
        Thread.sleep(200);  // cannot replace with 'wait'
        return this;
    }

}
