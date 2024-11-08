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

    @Test(description = "Login/Logout test")
    public void loginTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Assert.assertEquals(Pages.navigationPage().getHeaderText(), "Adamtms Jacksonniqjj");

        Actions.loginActions().doLogout();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible(), "The login form is not visible");
    }

    @Test(description = "Get last transaction")
    public void getLastTransactionTest() {
        Transaction transactionToCompare = new Transaction(300, 300, "Deposit", "11/01/2024", "Salary and wages");
        Actions.loginActions().doLogin(LOGIN, PASSWORD);

        Pages.homePage().clickOnFirstAccount();
        Pages.accountPage().waitForSearchButton();

        Transaction found = Actions.accountActions().getLastTransaction();

        Assert.assertEquals(found, transactionToCompare, "Transaction should be the same");
        Actions.loginActions().doLogout();
    }

    @Test(description = "Advanced search past days")
    public void thirdTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Pages.homePage().clickOnFirstAccount();

        Actions.accountActions().searchTransactionsPastDays(7);

        Assert.assertNotNull(Actions.accountActions().getLastTransaction(), "Not found transactions");
        Pages.accountPage().scrollUp();
        Actions.loginActions().doLogout();
    }
}
