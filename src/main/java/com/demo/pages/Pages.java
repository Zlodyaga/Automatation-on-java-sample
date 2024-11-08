package com.demo.pages;

import com.demo.core.allure.AllureLogger;

public class Pages extends AllureLogger {
    /**
     * Pages
     */
    private static LoginPage loginPage;
    private static NavigationPage navigationPage;
    private static HomePage homePage;
    private static AccountPage accountPage;
    private static TransferMoneyPage transferMoneyPage;

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

    public static AccountPage accountPage() {
        if (accountPage == null) {
            accountPage = new AccountPage();
        }
        return accountPage;
    }

    public static TransferMoneyPage transferMoneyPage() {
        if (transferMoneyPage == null) {
            transferMoneyPage = new TransferMoneyPage();
        }
        return transferMoneyPage;
    }
}