package com.demo.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demo.core.base.PageTools;
import com.demo.data.Book;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.demo.utils.Constants.URL_BOOK;

public class TestBookPage extends PageTools {

    private final By titlePath = By.xpath(".//span[@id='productTitle']");
    private final By bestSellerPath = By.xpath(".//div[@id='centerAttributesLeftColumn']");
    private final By pricePath = By.xpath(".//a[@id='a-autoid-0-announce']//span[@class='slot-price']//span[@class='a-size-base a-color-secondary']");
    private final By authorsPath = By.xpath(".//div[@class='a-section a-spacing-micro bylineHidden feature']//span[@class='author notFaded']//a[@class='a-link-normal']");

    public void open() {
        Selenide.open(URL_BOOK);
    }

    @Step("Save information about book to find")
    public Book getBook() { //
        waitForElements();
        String title = getElementText(titlePath);
        List<SelenideElement> authorCollection = getElements(authorsPath);
        String author = getFromCollectionAuthor(authorCollection);
        String[] prices = getElementText(pricePath).split(" - ");
        String priceRent = prices[0].trim();
        String priceFull = prices[1].trim();
        boolean isBestSeller = getElementText(bestSellerPath).contains("Best Seller");

        return new Book(title, author, priceFull, priceRent, isBestSeller);
    }

    private String getFromCollectionAuthor(List<SelenideElement> authorAll) {
        StringBuilder authorsText = new StringBuilder();
        int countElements = authorAll.size(), i = 1;
        for (SelenideElement authorElement : authorAll) {
            authorsText.append(authorElement.getText().trim());
            if(i != countElements) {authorsText.append(",");}
            i++;
        }

        return authorsText.toString().trim();
    }

    public void waitForElements() {
        waitForElementVisibility(titlePath);
    }
}