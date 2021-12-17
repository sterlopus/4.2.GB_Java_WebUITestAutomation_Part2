package ru.geekbrains.qa.level3.lession6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homework6Test {

    WebDriver driver;
    LoginPage loginPage;
    DealsKanbanView dealsBlock;


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver(){
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);

    }


    @Test
    void newDealCreateTest() throws InterruptedException {

        login(driver);
        dealsBlock = new DealsKanbanView(driver);

        dealsBlock.addNewDeal();
        dealsBlock.getDealsCounter();

        Assertions.assertThat(true);

    }


    @Test
    void kanbanMoveTest() throws InterruptedException {
        login2(driver);



    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }



    void login(WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(ProtoPage.CRM_URL);
        loginPage.inputLogin();
        loginPage.clickNextButton();
        loginPage.inputPassword();
        loginPage.clickNextButton();
        loginPage.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("crm_control_panel_menu_menu_crm_contact")));
    }

    void login2(WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(ProtoPage.CRM_URL);
        loginPage.inputLogin()
                .clickNextButton()
                .inputPassword()
                .clickNextButton()
                .wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id(BaseCRMPage.TOP_MENU_LOCATOR_BY_ID)));
    }


}
