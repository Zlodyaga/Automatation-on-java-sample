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
            double amount = Double.parseDouble(removeDollarSign(Pages.accountPage().getAmountField()));
            double futureBalance = Double.parseDouble(removeDollarSign(Pages.accountPage().getFutureBalanceField()));
            String type = Pages.accountPage().getTypeField();
            String date = Pages.accountPage().getDateField();
            String category = Pages.accountPage().getCategoryField();
            return new Transaction(amount, futureBalance, type, date, category);
        } else return null;
    }

    private static String removeDollarSign(String value) {
        if (value == null) return null;
        return value.replace("$", "");
    }
}
