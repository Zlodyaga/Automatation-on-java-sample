package com.demo;

import com.demo.actions.Actions;
import com.demo.core.base.BaseTest;
import com.demo.data.Transaction;
import com.demo.pages.Pages;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demo.utils.Constants.*;

@Epic("Test Epic")
@Feature("Test accounts")
@Owner("QA Bohomazov Dmytro")
public class AccountTests extends BaseTest {

    @Test(description = "Get last transaction")
    public void firstTest() {
        Transaction transactionToCompare = new Transaction(300, 300, "Deposit", "11/01/2024", "Salary and wages");
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Pages.homePage().clickOnFirstAccount();
        Pages.accountPage().waitForSearchButton();

        Assert.assertEquals(Pages.accountPage().getLastTransaction(), transactionToCompare, "Transaction should be the same");
        Actions.loginActions().doLogout();
    }

    @Test(description = "Advanced search past days")
    public void secondTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Pages.homePage().clickOnFirstAccount();

        Pages.accountPage().waitForSearchButton();
        Pages.accountPage().clickSearchButtonShow();
        Pages.accountPage().setPastDaysFromToday(7);
        Pages.accountPage().clickSearchButton();

        Assert.assertNotNull(Pages.accountPage().getLastTransaction(), "Not found transactions");
        Pages.accountPage().scrollUp();
        Actions.loginActions().doLogout();
    }

}
