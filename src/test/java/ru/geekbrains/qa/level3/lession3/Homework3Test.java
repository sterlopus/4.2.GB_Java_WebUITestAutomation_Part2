package ru.geekbrains.qa.level3.lession3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Objects;

public class Homework3Test {

    private static final String CRM_URL = "https://b24-in8ijg.bitrix24.ru";
    private static final String LOGIN = "gleb.smirnov@me.com";
    private static final String PASSWORD = "Bitrix24-gbuser";
    private static final String TEST_TEXT = "new_deal";
    private static final String CONTACT_NAME = "Дмитрий Степанов";
    private static final String COMPANY_NAME = "Астер";

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--start-maximized");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        FluentWait<WebDriver> wait = new FluentWait<>(driver);

        driver.get(CRM_URL);
        driver.manage().window().maximize();
        login(driver);
        newDeal(driver);
        Thread.sleep(3000);
        driver.quit();

    }

    public static void login(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("login")).sendKeys(LOGIN);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//label[@for='remember']")).click();
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
    }

    public static void newDeal(WebDriver driver) {
        driver.findElement(By.xpath("//a[@title='Добавить сделку']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='side-panel-iframe']")));
        driver.findElement(By.xpath("//input[@name='TITLE']")).sendKeys(TEST_TEXT);
        driver.findElement(By.xpath("//input[contains(@class,'ui-ctl-inline')]")).clear();
        driver.findElement(By.xpath("//input[contains(@class,'ui-ctl-inline')]")).sendKeys("75000");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Имя контакта, телефон или e-mail')]"))
                .sendKeys(CONTACT_NAME);
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Название компании, телефон или e-mail')]"))
                .sendKeys(COMPANY_NAME);
        driver.findElement(By.xpath("//button[@title='[Ctrl+Enter]']")).click();

        String s = driver.findElement(By.xpath("//span[@id='pagetitle_btn_wrapper']")).getAttribute("value");
        String s1 = driver.findElement(By.xpath("//span[@id='pagetitle_btn_wrapper']")).getText();

        System.out.println("s = " + s + "\ns1 = " + s1);

        if (Objects.equals(s, TEST_TEXT)) {
            System.out.println("test OK " + s);
        } else {
            System.out.println("test NOK " + s);
        }
        driver.findElement(By.xpath("//div[@class='side-panel-label-icon side-panel-label-icon-close']"));
        driver.switchTo().parentFrame();
    }

}