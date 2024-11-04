package com.demo;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.BaseTest;
import com.demo.pages.Pages;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Test Epic")
@Feature("Test feature")
@Owner("QA Bohomazov Dmytro")
public class FirstTest extends BaseTest {

    @Test(description = "FirstTest")
    public void firstTest() {
        String searchWord = System.getProperty("myArg", "default_value");
        logInfo("searchWord is " + searchWord);

        Pages.homePage().waitForSearchForm();
        Pages.homePage().search(searchWord);

        Pages.searchPage().waitForElements();
        List<SelenideElement> elements = Pages.searchPage().getFoundElements();
        Pages.searchPage().checkForSearchWord(searchWord, elements);
    }
}
