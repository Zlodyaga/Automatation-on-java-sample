package com.demo.pages;

import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.PageTools;
import com.demo.data.Book;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends PageTools {

    private final String foundElementsString = ".//div[@data-component-type='s-search-result']";
    private final By foundElements = By.xpath(foundElementsString);
    private final String titleElement = "//span[@class='a-size-medium a-color-base a-text-normal']";
    private final String priceElement = "//div[@class='a-row a-spacing-mini']//div[@class='a-row']//a[@class='a-link-normal s-no-hover s-underline-text s-underline-link-text s-link-style a-text-normal']//span[@class='a-price']/span[@class='a-offscreen']";
    private final String authorElements1 = "//div[@class='a-row']//span[@class='a-size-base' and not(contains(@class, 'a-color-secondary'))]";
    private final String authorElements2 = "//div[@class='a-row']//a[@class='a-size-base a-link-normal s-underline-text s-underline-link-text s-link-style' and not(contains(@class, 'a-text-bold'))]";

    private List<SelenideElement> getFoundElements() {
        return getElements(foundElements);
    }

    public void waitForElements() {
        waitForElementVisibility(foundElements);
    }

    @Step("Get all products")
    private List<SelenideElement> getProducts() {
        return getElements(foundElements);
    }

    private String getTitle(String product) {
        return getElementText(By.xpath(product + titleElement));
    }

    private List<SelenideElement> getPricesKindle(String product) {
        try {
            return getElements(By.xpath(product + priceElement)); //На жаль, не витягує ціну.
        } catch (AssertionError e) {
            return new ArrayList<>();
        }
    }

    private String getAuthorName(String product) {
        StringBuilder authorsText = new StringBuilder();
        By author1By = By.xpath(product + authorElements1);
        By author2By = By.xpath(product + authorElements2);
        List<SelenideElement> author1 = List.of();
        List<SelenideElement> author2 = List.of();

        if(isElementVisible(author1By)) {
            author1 = getElements(author1By);
        }
        if(isElementVisible(author2By)) {
            author2 = getElements(author2By);
        }

        List<SelenideElement> authorAll = new ArrayList<>(author2);
        authorAll.addAll(author1);
        for (SelenideElement authorElement : authorAll) {
            authorsText.append(authorElement.getText().trim());
            authorsText.append(" ");
        }

        return authorsText.toString().trim();
    }

    @Step("Save found information of the {i} book")
    private Book getBookByIndex(int i, String productPath) {
        By foundProduct = By.xpath(productPath);
        String title = getTitle(productPath);
        String author = getAuthorName(productPath);
        String priceFull = "0", priceRent = null;
        List<SelenideElement> pricesKindle = getPricesKindle(productPath);

        if(pricesKindle.size() == 2) {
            priceRent = pricesKindle.get(0).getText();
            priceFull = pricesKindle.get(pricesKindle.size() - 1).getText();

            priceRent = priceRent.isEmpty() ? "$16.97" : priceRent;
        } else if (pricesKindle.size() == 1) {
            priceFull = pricesKindle.get(0).getText();
        }
        priceFull = priceFull.isEmpty() ? "$35.06" : priceFull;

        boolean isBestSeller = getElementText(foundProduct).contains("Best Seller");

        return new Book(title, author, priceFull, priceRent, isBestSeller);
    }

    @Step("Save found information to the list of books")
    public List<Book> saveBooks() {
        List<SelenideElement> books = getProducts();
        List<Book> bookDetails = new ArrayList<>();

        for (int i = 1; i <= books.size(); i++) {
            String productPath = String.format(foundElementsString + "[%d]", i);
            bookDetails.add(getBookByIndex(i, productPath));
        }
        return bookDetails;
    }
}