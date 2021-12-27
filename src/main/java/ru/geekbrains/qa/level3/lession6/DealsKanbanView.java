package ru.geekbrains.qa.level3.lession6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DealsKanbanView extends BaseCRMPage {

    public DealsKanbanView(WebDriver driver) {
        super(driver);
    }

    //  kanban view columns
    @FindBy(xpath = "//div[@data-id='NEW']")
    WebElement newDealKanbanColumn;

    @FindBy(xpath = "//div[@data-id='PREPARATION']")
    WebElement docsPreparationDealsKanbanColumn;

    @FindBy(xpath = "//div[@data-id='PREPAYMENT_INVOICE']")
    WebElement prepaymentInvoicedDealsKanbanColumn;

    @FindBy(xpath = "//div[@data-id='EXECUTING']")
    WebElement executingDealsKanbanColumn;

    @FindBy(xpath = "//div[@data-id='FINAL_INVOICE']")
    WebElement finalInvoicedDealsKanbanColumn;

    /**
     *  other kanban columns items
     */

    @FindBy(xpath = "//div[@data-id='WON']")
    WebElement wonDealsKanbanColumn;
    //  end of kanban view columns



    // Buttons and deals
    @FindBy(xpath = "//a[@title='Добавить сделку']")
    WebElement addNewDealButton;

        DealsKanbanView addNewDeal() {
            addNewDealButton.click();
            return this;
        }


    @FindBy(xpath = "//iframe[@class='side-panel-iframe']")
    WebElement newDealFrame;

        DealsKanbanView switchToNewDealFrame() {
            driver.switchTo().frame(newDealFrame);
            return this;
        }


    @FindBy(xpath = "//div[@class='main-kanban-column-total-item']")
    List<WebElement> columnDealsCounters;

        int getColumnDealCounter(int i) {
            return Integer.parseInt(columnDealsCounters.get(i).getText());
        }


    @FindBy(xpath = "//div[@class='main-kanban-item']")
    WebElement anyKanbanDeal;

    @FindBy(xpath = "//div[@class='main-kanban-item']")
    List<WebElement> allKanbanDeals;

}
