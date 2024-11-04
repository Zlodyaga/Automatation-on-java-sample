package com.demo.pages;

import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class HomePage extends PageTools {

    private final By searchField = By.xpath("//textarea[@class=\"gLFyf\"]");

    public void search(String searchObject) {
        typeWithEnter(searchObject, searchField);
    }

    public void waitForSearchForm() {
        waitForElementVisibility(searchField);
    }
}