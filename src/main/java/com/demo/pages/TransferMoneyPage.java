package com.demo.pages;

import com.demo.core.base.PageTools;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TransferMoneyPage extends PageTools {

    private final By amountField = By.xpath("//div[@data-field-id='amount']//input[@type='text']");
    private final By dateField = By.xpath("//div[@data-field-id='start']//input[@type='text']");
    private final By noteField = By.xpath("//div[@data-field-id='notes']//input[@type='text']");
    private final By toAccountField = By.xpath("//div[@data-field-id='toAccountRootId']//div[@class='xwidget_field_container']");
    private final By toAccountFieldSelect = By.xpath("//div[@data-field-id='toAccountRootId']//div[@class='xwidget_field_container']//span[@class='account_listitem']");
    private final By doneButton = By.xpath("//div[@id='transfer-doneButton']//button[@type='button']");
    private final By continueButton = By.xpath("//div[@data-container-id='Buttons']/div[@data-field-id='continueButton']//button[@type='button']");
    private final By transferHistoryButton = By.xpath("//a[@aria-label='Transfer History']/..");

    private final String lastTransfer = "(//table[@class='xwidget_grid_table display']/tbody/tr[not(contains(@class, 'xwidget_grid_details_row'))])[3]";
    private final By lastTransferAmount = By.xpath(lastTransfer + "//td[@data-column-id='amount']/span");
    private final By lastTransferNotes = By.xpath(lastTransfer + "//td[@data-column-id='notes']");
    private final By lastTransferDate = By.xpath(lastTransfer + "//td[@data-column-id='effectivedate']/span");

    @Step("Click on to account field")
    public void clickOnToAccountField() {
        click(toAccountField);
    }

    @Step("Select account to transfer")
    public void clickOnToAccountSelect() {
        click(toAccountFieldSelect);
    }

    @Step("Click on continue transfer button")
    public void clickOnContinueButton() {
        click(continueButton);
        waitForDoneButton();
    }

    @Step("Click on done transfer button")
    public void clickOnDoneButton() {
        waitForAccountField();
        click(doneButton);
        waitForAccountField();
    }

    @Step("Go to the transfer history")
    public void goToTransferHistory() {
        click(transferHistoryButton);
        Pages.transferMoneyPage().waitForLastTransferFields();
    }

    @Step("Get last transfer amount")
    public String getLastTransferAmount() {
        return getElementText(lastTransferAmount).replace(".", ",").replace("$","");
    }

    @Step("Get last transfer date")
    public String getLastTransferDate() {
        return getElementText(lastTransferDate);
    }

    @Step("Get last transfer notes")
    public String getLastTransferNote() {
        return getElementText(lastTransferNotes);
    }

    @Step("Type amount of transfer {amount}")
    public void typeAmount(String amount) {
        type(amount, amountField);
    }

    @Step("Type date of transfer {date}")
    public void typeDate(String date) {
        type(date, dateField);
    }

    @Step("Type note of transfer {note}")
    public void typeNote(String note) {
        type(note, noteField);
    }

    public void waitForAccountField() {
        waitForElementVisibility(toAccountField);
    }

    public void waitForLastTransferFields() {
        waitForElementVisibility(lastTransferAmount);
    }

    public void waitForDoneButton() {
        waitForElementVisibility(doneButton);
    }
}