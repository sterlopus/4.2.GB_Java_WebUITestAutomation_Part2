package ru.geekbrains.qa.level3.lession6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.qa.level3.lession7.CustomLogger;

@Story("Сделки")
public class Homework6Test {

    WebDriver driver;
    LoginPage loginPage;
    DealsKanbanView dealsBlock;


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        dealsBlock = new DealsKanbanView(driver);
    }


    @Test
    @Feature("Новые сделки")
    @Description("Тест  создания новой сделки")
    @Severity(SeverityLevel.BLOCKER)
    void newDealCreateTest() throws InterruptedException {

        login(driver);

        int counter = dealsBlock.getDealsCounter();
        dealsBlock.addNewDeal();
        dealsBlock.switchToNewDealFrame();
        DealFrame dealFrame = new DealFrame(driver);

        dealFrame.fillTitle()
                .fillTotalSum()
                .fillContactName()
                .fillCompanyName()
                .saveNewDeal()
                .closeDealFrame();

        Assertions.assertThat(dealsBlock.getDealsCounter()).isEqualTo(++counter);

    }


    @Test
    @Feature("Канбан")
    @Description("Тест перетаскивания сделки между столбцами представления Канбан")
    @Severity(SeverityLevel.NORMAL)
    void kanbanMoveTest() throws InterruptedException {
        login(driver);
        DealsKanbanView dealsKanbanView = new DealsKanbanView(driver);

        int sourceColumnCounterBefore = dealsKanbanView.getColumnDealCounter(0);
        int targetColumnCounterBefore = dealsKanbanView.getColumnDealCounter(2);

        Thread.sleep(1000);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(
                        dealsKanbanView.allKanbanDeals.get(1),
                        dealsKanbanView.prepaymentInvoicedDealsKanbanColumn)
                .build()
                .perform();

        Thread.sleep(1000);

        int sourceColumnCounterAfter = dealsKanbanView.getColumnDealCounter(0);
        int targetColumnCounterAfter = dealsKanbanView.getColumnDealCounter(2);

        Assertions.assertThat(sourceColumnCounterAfter).isEqualTo(--sourceColumnCounterBefore);
        Assertions.assertThat(targetColumnCounterAfter).isEqualTo(++targetColumnCounterBefore);

    }


    @AfterEach
    void tearDown() {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        logs.forEach(l -> Allure.addAttachment("Элемент лога браузера", l.getMessage()));
        driver.quit();
    }


    void login(WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(Bitrix24Constants.CRM_URL);
        loginPage.inputLogin()
                .clickNextButton()
                .inputPassword()
                .clickNextButton()
                .wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id(Bitrix24Constants.TOP_MENU_LOCATOR_BY_ID)));
    }


}
