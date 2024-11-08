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

/*
    @Test(description = "Login/Logout test")
    public void loginTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Assert.assertEquals(Pages.navigationPage().getHeaderText(), "Adamtms Jacksonniqjj");

        Actions.loginActions().doLogout();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible(), "The login form is not visible");
    }
*/
    @Test(description = "Transfer money from one account to other")
    public void makeTransferTest() {
        Actions.loginActions().doLogin(LOGIN, PASSWORD);
        Pages.homePage().clickOnFirstAccount();
        Pages.accountPage().waitForSearchButton();
        Pages.accountPage().clickTransferMoneyButton();

        Pages.transferMoneyPage().waitForAccountField();
        Pages.transferMoneyPage().clickOnToAccountField();
        Pages.transferMoneyPage().clickOnToAccountSelect();

        testDate = getLocalDateTimeByPattern("MM/dd/yyyy");
        testNote = Generator.genString(10);
        testAmount = Generator.getRandomFormattedDecimalStringValueWithMax("0.00", 5);

        Pages.transferMoneyPage().typeDate(testDate);
        Pages.transferMoneyPage().typeNote(testNote);
        Pages.transferMoneyPage().typeAmount(testAmount);

        Pages.transferMoneyPage().clickOnContinueButton();
        Pages.transferMoneyPage().clickOnDoneButton();

        Pages.transferMoneyPage().goToTransferHistory(); //TODO доробити з цього моменту
        Assert.assertEquals(Pages.transferMoneyPage().getLastTransferAmount(), testAmount);
        Assert.assertEquals(Pages.transferMoneyPage().getLastTransferNote(), testNote);
        Assert.assertEquals(Pages.transferMoneyPage().getLastTransferDate(), testDate);

        Actions.loginActions().doLogout();
    }
/*
    @Test(description = "Get last transaction")
    public void getLastTransactionTest() {
        Transaction transactionToCompare = new Transaction(300, 300, "Deposit", testDate, "Salary and wages"); //TODO Додавання даних з змінних вище. Також зробити у цьому тесті обирання фільтрації трансферів
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
*/
}
