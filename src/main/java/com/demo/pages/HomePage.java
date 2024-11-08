package com.demo.pages;

import com.demo.core.base.PageTools;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends PageTools {

    private final By accountFirstField = By.xpath("//tr[@data-id='190490384']/td[@data-column-id='column1']");

    @Step("Open account detailed page")
    public void clickOnFirstAccount() {
        click(accountFirstField);
    }

    public void waitForAccountField() {
        waitForElementVisibility(accountFirstField);
    }
}