package ru.geekbrains.qa.level3.lession5;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Story("Сделки")
public class Homework5Test {

    private static final String CRM_URL = "https://b24-in8ijg.bitrix24.ru";
    private static final String LOGIN = "gleb.smirnov@me.com";
    private static final String PASSWORD = "Bitrix24-gbuser";
    private static final String TEST_TEXT = "Deal #1";
    private static final String CONTACT_NAME = "Дмитрий Степанов";
    private static final String COMPANY_NAME = "Астер";

    WebDriver driver;
    FluentWait<WebDriver> wait;
    Actions actions;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void startNewSession() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new FluentWait<>(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        driver.get(CRM_URL);
        driver.manage().window().maximize();
    }


    @Test
    @Feature("Новые сделки")
    @Description("Создание новой сделки")
    void newDealTest() throws InterruptedException {
        login(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crm_control_panel_menu_menu_crm_contact")));

        int dealsCounter = Integer.parseInt(driver
                .findElement(By.xpath("//div[@data-text='Сделки']"))
                .getAttribute("data-counter"));
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
        wait.until(d -> d.findElement(By.id("pagetitle")).getText().contains("Deal"));

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//div[@title='Закрыть']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crm_control_panel_menu_menu_crm_catalog")));

        Assertions.assertThat(Integer.parseInt(driver.findElement(By.xpath("//div[@data-text='Сделки']"))
                        .getAttribute("data-counter")))
                .isEqualTo(dealsCounter + 1);
    }

    @Test
    @Feature("Канбан")
    @Description("Перетаскивание сделки между столбцами Канбан")
    void kanbanMoveTest() throws InterruptedException {
        login(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crm_control_panel_menu_menu_crm_contact")));

        int counter = Integer.parseInt(driver.findElement(
                By.xpath("//div[.='Cчёт на предоплату']/following-sibling::div")).getText());
        System.out.println("counter = " + counter);

        actions.dragAndDrop(
                        driver.findElement(By.xpath("//div[@data-id='NEW']//div[@class='crm-kanban-item']")),
                        driver.findElement(By.xpath("//div[@data-id='PREPAYMENT_INVOICE']")))
                .build()
                .perform();

        Assertions.assertThat(Integer.parseInt(driver.findElement(
                By.xpath("//div[.='Cчёт на предоплату']/following-sibling::div")).getText())).isEqualTo(++counter);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void login(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("login")).sendKeys(LOGIN);
        Thread.sleep(100);
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//label[@for='remember']")).click();
        driver.findElement(By.xpath("//button[@data-action='submit']")).click();
    }

}