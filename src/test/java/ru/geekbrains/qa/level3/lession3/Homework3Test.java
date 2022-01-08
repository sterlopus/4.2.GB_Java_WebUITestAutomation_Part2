package ru.geekbrains.qa.level3.lession3;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

@Story("Сделки")
public class Homework3Test {

    private static final String CRM_URL = "https://b24-in8ijg.bitrix24.ru";
    private static final String LOGIN = "gleb.smirnov@me.com";
    private static final String PASSWORD = "Bitrix24-gbuser";
    private static final String TEST_TEXT = "Deal #1";
    private static final String CONTACT_NAME = "Дмитрий Степанов";
    private static final String COMPANY_NAME = "Астер";

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

//        options.addArguments("--incognito", "--start-maximized");
//        ChromeOptions options = new ChromeOptions();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        FluentWait<WebDriver> wait = new FluentWait<>(driver);
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get(CRM_URL);
        driver.manage().window().maximize();
        login(driver);      // login test
        Thread.sleep(3000);
        newDeal(driver);    // create new deal test
        Thread.sleep(3000);
        kanbanMove(driver); // move new deal over kanban desk
        Thread.sleep(3000);
        driver.quit();
    }

    public static void login(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("login")).sendKeys(LOGIN);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        Thread.sleep(1000);                                                             // TODO: replace with wait
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-action = 'submit']")));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-action='submit']")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-action='submit']")));
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//label[@for='remember']")).click();
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
    }

    public static void newDeal(WebDriver driver) throws InterruptedException {
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
        driver.switchTo().defaultContent();
//        FluentWait<WebDriver> wait = new FluentWait<>(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Закрыть']")));
        Thread.sleep(1000);                                                           // TODO: replace with wait
        driver.findElement(By.xpath("//div[@title='Закрыть']")).click();
    }

    public static void kanbanMove(WebDriver driver) {
        Actions act = new Actions(driver);
        act.dragAndDrop(
                driver.findElement(By.xpath("//div[@data-id='NEW']//div[@class='crm-kanban-item']")),
                driver.findElement(By.xpath("//div[@data-id='PREPAYMENT_INVOICE']")))
                .build()
                .perform();
    }

}