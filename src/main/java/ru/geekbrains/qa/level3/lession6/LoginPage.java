package ru.geekbrains.qa.level3.lession6;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ProtoPage{


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
        loginInputField.sendKeys(Bitrix24Constants.LOGIN);
        return this;
    }

    LoginPage inputPassword() {
        passwordInputField.sendKeys(Bitrix24Constants.PASSWORD);
        return this;
    }

    LoginPage clickNextButton() throws InterruptedException {
        Thread.sleep(100);  // cannot replace with 'wait'
        submitLoginButton.click();
        Thread.sleep(100);  // cannot replace with 'wait'
        return this;
    }

}
