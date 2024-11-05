package com.demo.pages;

import com.demo.core.base.PageTools;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends PageTools {

    private final By searchField = By.id("twotabsearchtextbox");
    private final By dropdown = By.cssSelector("[aria-describedby='searchDropdownDescription']");

    @Step("Enter to search \"Java\"")
    public void search(String searchObject) {
        typeWithEnter(searchObject, searchField);
    }

    @Step("Choose filter Book")
    public void chooseFilter(String choose) {
        getSelenideElement(dropdown).selectOptionContainingText(choose);
    }

    public void waitForSearchForm() {
        waitForElementVisibility(searchField);
    }
}