package com.demo.pages;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPage extends PageTools {

    private final By foundElements = By.xpath("//div[@class=\"MjjYud\"]");

    public List<SelenideElement> getFoundElements() {
        return getElements(foundElements);
    }

    public boolean checkForSearchWord(String searchWord, List<SelenideElement> elements) {
        boolean check = true;
        for (SelenideElement element : elements) {
        shouldMatchText(searchWord, foundElements);
        }

        return check;
    }

    public void waitForElements() {
        waitForElementVisibility(foundElements);
    }
}