package com.demo.pages;

import com.demo.core.base.PageTools;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.demo.utils.DateTime.*;

public class AccountPage extends PageTools {

    private final String lastTransactionField = "//table[@class='xwidget_grid_table display']/tbody[1]";
    private final String tableOfSearch = "//div[@data-container-id='searchresult']//div[@class='xwidget_grid_table_wrapper']";
    private final By amountField = By.xpath(lastTransactionField + "//td[@data-column-id='amount']/span[@class='renderers_currency']");
    private final By futureBalanceField = By.xpath(lastTransactionField + "//td[@data-column-id='amount_balance']/span[@class='renderers_currency']");
    private final By typeField = By.xpath(lastTransactionField + "//td[@data-column-id='description']");
    private final By dateField = By.xpath(lastTransactionField + "//td[@data-column-id='postingDate']/span");
    private final By categoryField = By.xpath(lastTransactionField + "//td[@data-column-id='category']");
    private final By advancedSearchButtonShow = By.xpath("//div[@class='display_grid']//button[@class='crm_button ui-button ui-corner-all ui-widget']");
    private final By advancedSearchButton = By.xpath("//div[@data-container-id='Buttons']//div[@data-field-id='searchButton']//button[@type='button']");
    private final By endDateField = By.xpath("//input[@aria-label='end Date']");
    private final By startDateField = By.xpath("//input[@aria-label='Start Date']");
    private final By objectToScrollUp = By.xpath("//div[@data-field-id='accountid']//div[@class='flexbox flexbox-row-wrap flexbox-grow-1']");

    public void waitForSearchButton() {
        waitForElementVisibility(advancedSearchButtonShow);
    }

    public void waitForSearchResults() {
        waitForElementVisibility(By.xpath(tableOfSearch));
    }

    public boolean isTableOfTransactionVisible() {
        return isElementVisible(By.xpath(tableOfSearch + lastTransactionField));
    }

    @Step("Set dates in fields")
    public void setPastDaysFromToday(int days) {
        String today = getLocalDateTimeByPattern("MM/dd/yyyy");
        type(today, endDateField);
        type(getDateMinusDays(today, days), startDateField);
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        click(advancedSearchButton);
    }

    @Step("Open advanced search fields")
    public void clickSearchButtonShow() {
        click(advancedSearchButtonShow);
    }

    @Step("Scroll up")
    public void scrollUp() {
        scrollToPlaceElementInCenter(objectToScrollUp);
    }

    // Getters

    public String getAmountField() {
        return getElementText(amountField);
    }

    public String getFutureBalanceField() {
        return getElementText(futureBalanceField);
    }

    public String getTypeField() {
        return getElementText(typeField);
    }

    public String getDateField() {
        return getElementText(dateField);
    }

    public String getCategoryField() {
        return getElementText(categoryField);
    }
}