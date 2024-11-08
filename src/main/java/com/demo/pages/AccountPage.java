package com.demo.pages;

import com.demo.core.base.PageTools;
import com.demo.data.Transaction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.demo.utils.DateTime.*;

public class AccountPage extends PageTools {

    private final String lastTransactionField = "//tr[@class='xwidget_grid_row xwidget_grid_row_190490388'][1]";
    private final By amountField = By.xpath(lastTransactionField + "/td[@data-column-id='amount']/span[@class='renderers_currency']");
    private final By futureBalanceField = By.xpath(lastTransactionField + "/td[@data-column-id='amount_balance']/span[@class='renderers_currency']");
    private final By typeField = By.xpath(lastTransactionField + "/td[@data-column-id='description']");
    private final By dateField = By.xpath(lastTransactionField + "/td[@data-column-id='postingDate']/span");
    private final By categoryField = By.xpath(lastTransactionField + "/td[@data-column-id='category']");
    private final By advancedSearchButtonShow = By.xpath("//div[@class='display_grid']//button[@class='crm_button ui-button ui-corner-all ui-widget']");
    private final By advancedSearchButton = By.xpath("//div[@data-container-id='Buttons']//div[@data-field-id='searchButton']//button[@type='button']");
    private final By endDateField = By.xpath("//input[@aria-label='end Date']");
    private final By startDateField = By.xpath("//input[@aria-label='Start Date']");
    private final By objectToScrollUp = By.xpath("//div[@data-field-id='accountid']//div[@class='flexbox flexbox-row-wrap flexbox-grow-1']");

    @Step("Get last transaction")
    public Transaction getLastTransaction() {
        try {
            Thread.sleep(1000);
            double amount = Double.parseDouble(removeDollarSign(getElementText(amountField)));
            double futureBalance = Double.parseDouble(removeDollarSign(getElementText(futureBalanceField)));
            String type = getElementText(typeField);
            String date = getElementText(dateField);
            String category = getElementText(categoryField);
            return new Transaction(amount, futureBalance, type, date, category);
        } catch (InterruptedException e) {
            return null;
        }
    }

    public void waitForSearchButton() {
        waitForElementVisibility(advancedSearchButtonShow);
    }

    @Step("Set dates in fields")
    public void setPastDaysFromToday(int days) {
        String today = getLocalDateTimeByPattern("MM/dd/yyyy");
        String pastDate = getDateMinusDays(today, days);

        //click(endDateField);
        type(today, endDateField);

        //click(startDateField);
        type(pastDate, startDateField);
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        click(advancedSearchButton);
    }

    @Step("Open advanced search fields")
    public void clickSearchButtonShow() {
        click(advancedSearchButtonShow);
    }

    private static String removeDollarSign(String value) {
        if (value == null) {
            return null;  // Обработка случая, когда значение равно null
        }
        return value.replace("$", "");  // Убираем все символы "$" из строки
    }

    @Step("Scroll up")
    public void scrollUp() {
        scrollToPlaceElementInCenter(objectToScrollUp);
    }
}