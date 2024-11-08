package com.demo;

import com.demo.actions.Actions;
import com.demo.core.base.BaseTest;
import com.demo.data.Transaction;
import com.demo.pages.Pages;
import com.demo.utils.Generator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demo.utils.Constants.*;
import static com.demo.utils.DateTime.getLocalDateTimeByPattern;

@Epic("Test Epic")
@Feature("Test accounts")
@Owner("QA Bohomazov Dmytro")
public class AccountTests extends BaseTest {
    String testDate;
    String testNote;
    String testAmount;
    String testType;
    String testCategory;


    @Test(description = "Login/Logout test", priority = 1)
    public void loginTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Assert.assertEquals(Pages.navigationPage().getHeaderText(), "Adamtms Jacksonniqjj");

        Actions.loginActions().doLogout();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible(), "The login form is not visible");
    }

    @Test(description = "Transfer money from one account to other", priority = 2)
    public void makeTransferTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Pages.homePage().waitForAccountField();
        Pages.homePage().clickOnFirstAccount();
        Pages.accountPage().waitForSearchButton();
        Pages.accountPage().clickTransferMoneyButton();

        Pages.transferMoneyPage().waitForAccountField();
        Pages.transferMoneyPage().clickOnToAccountField();
        Pages.transferMoneyPage().clickOnToAccountSelect();

        testDate = getLocalDateTimeByPattern("MM/dd/yyyy");
        testNote = Generator.genString(10);
        testAmount = Generator.getRandomFormattedDecimalStringValueWithMax("0.00", 5);
        testType = "Transfer";
        testCategory = "Transfers and withdrawals";

        Pages.transferMoneyPage().typeDate(testDate);
        Pages.transferMoneyPage().typeNote(testNote);
        Pages.transferMoneyPage().typeAmount(testAmount);

        Pages.transferMoneyPage().clickOnContinueButton();
        Pages.transferMoneyPage().clickOnDoneButton();

        Pages.transferMoneyPage().goToTransferHistory();
        String testAmountFound = Pages.transferMoneyPage().getLastTransferAmount();
        String testNoteFound = Pages.transferMoneyPage().getLastTransferNote();
        String testDateFound = Pages.transferMoneyPage().getLastTransferDate();

        Assert.assertEquals(testAmountFound, testAmount, "Test amount doesn't match");
        Assert.assertEquals(testNoteFound, testNote, "Test note doesn't match");
        Assert.assertEquals(testDateFound, testDate, "Test date doesn't match");

        Actions.loginActions().doLogout();
    }

    @Test(description = "Get last transaction of transfer in previous test", priority = 3)
    public void getLastTransactionTest() {
        Transaction transactionToCompare = new Transaction("- " + testAmount, testType, testDate, testCategory, testNote); //TODO Додавання даних з змінних вище. Також зробити у цьому тесті обирання фільтрації трансферів
        Actions.loginActions().doLogin(LOGIN, PASSWORD);

        Pages.homePage().waitForAccountField();
        Pages.homePage().clickOnFirstAccount();
        Pages.accountPage().waitForSearchButton();

        Pages.accountPage().clickSearchButtonShow();
        Pages.accountPage().chooseTransactionType();
        Pages.accountPage().clickSearchButton();
        Transaction found = Actions.accountActions().getLastTransaction();

        Assert.assertEquals(found, transactionToCompare, "Transaction should be the same");

        Pages.accountPage().scrollUp();
        Actions.loginActions().doLogout();
    }

    @Test(description = "Advanced search past days", priority = 4)
    public void searchByPastDaysTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Pages.homePage().waitForAccountField();
        Pages.homePage().clickOnFirstAccount();

        Actions.accountActions().searchTransactionsPastDays(7);

        Assert.assertNotNull(Actions.accountActions().getLastTransaction(), "Not found transactions");
        Pages.accountPage().scrollUp();
        Actions.loginActions().doLogout();
    }
}
