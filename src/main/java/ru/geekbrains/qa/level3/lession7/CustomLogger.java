package ru.geekbrains.qa.level3.lession7;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CustomLogger implements WebDriverListener {

//    @Override
//    public void beforeFindElement(WebDriver driver, By locator) {
//        getScreenshot("скриншот поиска элемента", driver);
//    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        getScreenshot("скриншот ошибки " + e, (WebDriver)target);
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        getScreenshot("финальный скриншот перед закрытием браузера", driver);
    }

    private void getScreenshot(String string, WebDriver driver){
        Allure.addAttachment(string,
                new ByteArrayInputStream( ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }

}