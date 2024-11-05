package com.demo;

import com.demo.core.base.BaseTest;
import com.demo.data.Book;
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
        Pages.homePage().waitForSearchForm();
        Pages.homePage().chooseFilter("Books");
        Pages.homePage().search("Java");

        Pages.searchPage().waitForElements();
        List<Book> books = Pages.searchPage().saveBooks();

        Pages.testBookPage().open();
        Pages.testBookPage().waitForElements();
        Book findBook = Pages.testBookPage().getBook();

        Assert.assertFalse(books.isEmpty(), "List with books is empty");
        Assert.assertTrue(books.stream().anyMatch((book -> book.equals(findBook))), "Book not found in the list");
    }
}
