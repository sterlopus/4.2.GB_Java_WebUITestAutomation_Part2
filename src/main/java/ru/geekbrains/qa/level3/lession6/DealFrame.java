package ru.geekbrains.qa.level3.lession6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DealFrame extends ProtoPage{

    public DealFrame(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='TITLE']")
    WebElement dealTitle;

        DealFrame fillTitle() {
            dealTitle.clear();
            dealTitle.sendKeys(Bitrix24Constants.TEST_TEXT);
            return this;
        }

    @FindBy(xpath = "//input[contains(@class,'ui-ctl-inline')]")
    WebElement totalSum;

        DealFrame fillTotalSum() {
            totalSum.clear();
            totalSum.sendKeys(Bitrix24Constants.DEAL_SUM);
            return this;
        }

    @FindBy(xpath = "//input[contains(@placeholder, 'Имя контакта, телефон или e-mail')]")
    WebElement contactName;

        DealFrame fillContactName() {
            contactName.sendKeys(Bitrix24Constants.CONTACT_NAME);
            return this;
        }

    @FindBy(xpath = "//input[contains(@placeholder, 'Название компании, телефон или e-mail')]")
    WebElement companyName;

        DealFrame fillCompanyName() {
            companyName.sendKeys(Bitrix24Constants.COMPANY_NAME);
            return this;
        }

    @FindBy(xpath = "//button[@title='[Ctrl+Enter]']")
    WebElement saveNewDealButton;

        DealFrame saveNewDeal() {
            saveNewDealButton.click();
            return this;
        }

    @FindBy(id = "pagetitle")
    WebElement dealPageTitle;


    @FindBy(xpath = "//div[@title='Закрыть']")
    WebElement closeDealFrameButton;

        void closeDealFrame() {
            wait.until(d -> dealPageTitle.getText().contains("Deal"));
            driver.switchTo().defaultContent();
            closeDealFrameButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Bitrix24Constants.TOP_MENU_LOCATOR_BY_ID)));
        }

}
