package com.demo;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.BaseTest;
import com.demo.pages.Pages;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Test Epic")
@Feature("Test searching")
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

        for (SelenideElement element : elements) {
            Assert.assertTrue(
                    element.getText().contains(searchWord),
                    "Text does not contain '%s': %s".formatted(searchWord, element.getText())
            );
        }
    }
}
