package com.demo.pages;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPage extends PageTools {

    private final By foundElements = By.xpath("//div[@class=\"MjjYud\"]//div[@class=\"N54PNb BToiNc\"]//h3[@class=\"LC20lb MBeuO DKV0Md\"]");

    public List<SelenideElement> getFoundElements() {
        return getElements(foundElements);
    }

    public void waitForElements() {
        waitForElementVisibility(foundElements);
    }
}