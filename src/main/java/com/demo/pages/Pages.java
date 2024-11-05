package com.demo.pages;

import com.demo.core.allure.AllureLogger;
import com.demo.pages.samplePages.LoginPage;
import com.demo.pages.samplePages.NavigationPage;

public class Pages extends AllureLogger {
    /**
     * Pages
     */
    private static LoginPage loginPage;
    private static NavigationPage navigationPage;
    private static HomePage homePage;
    private static SearchPage searchPage;
    private static TestBookPage testBookPage;

    /**
     * This function return an instance of `NavigationPage`
     */
    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static NavigationPage navigationPage() {
        if (navigationPage == null) {
            navigationPage = new NavigationPage();
        }
        return navigationPage;
    }

    public static HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public static SearchPage searchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    public static TestBookPage testBookPage() {
        if (testBookPage == null) {
            testBookPage = new TestBookPage();
        }
        return testBookPage;
    }
}