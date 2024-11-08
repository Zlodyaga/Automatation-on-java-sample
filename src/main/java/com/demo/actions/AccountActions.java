package com.demo.actions;

import com.demo.data.Transaction;
import com.demo.pages.Pages;
import io.qameta.allure.Step;

public class AccountActions {

    @Step("Search transactions for past {days} days")
    public void searchTransactionsPastDays(int days) {
        Pages.accountPage().waitForSearchButton();
        Pages.accountPage().clickSearchButtonShow();
        Pages.accountPage().setPastDaysFromToday(days);
        Pages.accountPage().clickSearchButton();
    }

    @Step("Get last transaction")
    public Transaction getLastTransaction() {
        Pages.accountPage().waitForSearchResults();
        if(Pages.accountPage().isTableOfTransactionVisible())
        {
            Pages.accountPage().clickLastTransactionsDetailsButton();
            String amount = removeDollarSign(Pages.accountPage().getAmountField());
            String futureBalance = removeDollarSign(Pages.accountPage().getFutureBalanceField());
            String date = Pages.accountPage().getDateField();
            String category = Pages.accountPage().getCategoryField();
            String type = Pages.accountPage().getTypeField();
            String note = Pages.accountPage().getNoteField();
            return new Transaction(amount, futureBalance, type, date, category, note);
        } else return null;
    }

    private static String removeDollarSign(String value) {
        if (value == null) return null;
        return value.replace("$", "");
    }
}
