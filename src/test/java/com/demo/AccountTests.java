package com.demo;

import com.demo.actions.Actions;
import com.demo.core.base.BaseTest;
import com.demo.data.Transaction;
import com.demo.pages.Pages;
import com.demo.utils.Generator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.demo.utils.Constants.*;
import static com.demo.utils.DateTime.getLocalDateTimeByPattern;

@Epic("Test Epic")
@Feature("Test accounts")
@Owner("QA Bohomazov Dmytro")
public class AccountTests extends BaseTest {
    String testDate;
    String testDateOnSite;
    String testNote;
    String testAmount;
    String testType;
    String testCategory;

    @BeforeMethod
    public void setUpForSpecificTest() {
        testDate = getLocalDateTimeByPattern("yyyy-MM-dd");
        testDateOnSite = getLocalDateTimeByPattern("MM/dd/yyyy");        testNote = Generator.genString(10);
        testAmount = Generator.getRandomFormattedDecimalStringValueWithMax("0.00", 5);
        testType = "Transfer";
        testCategory = "Transfers and withdrawals";
        RestAssured.baseURI = "https://dev120.nymbus.com";
        JSONObject requestJson = new JSONObject();
        requestJson.put("start", testDate);
        requestJson.put("frequency", FREQUENCY_ONCE_ID);
        requestJson.put("toAccountRootId", ACCOUNT_SECOND_ID);
        requestJson.put("fromAccountRootId", ACCOUNT_FIRST_ID);
        requestJson.put("amount", testAmount.replace(",","."));
        requestJson.put("notes", testNote);
        String requestJsonString = requestJson.toJSONString();
        String[][] tokens = AuthHelper.authenticateAndGetTokens();
        String accessToken = tokens[0][1];
        String sessionId = tokens[1][1];
        Response createTransferResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .cookie("JSESSIONID", sessionId)
                .body(requestJsonString) // JSON тело запроса для создания перевода
                .when()
                .post("/coreweb/controller/~olb:olb_createTransfer");
        if (createTransferResponse.getStatusCode() != 200) {
            throw new RuntimeException("Create transfer failed with status code: " + createTransferResponse.getStatusCode());
        }
    }

    @Test(description = "Get last transaction of transfer in previous test", priority = 3)
    public void getLastTransactionTest() {
        Transaction transactionToCompare = new Transaction("- " + testAmount, testType, testDateOnSite, testCategory, testNote);
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
}
