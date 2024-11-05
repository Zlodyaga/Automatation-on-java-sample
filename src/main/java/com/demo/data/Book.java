package com.demo.data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String priceFull;
    private String priceRent;
    private boolean isBestSeller;

    // Constructor
    public Book(String title, String author, String priceFull, String priceRent, boolean isBestSeller) {
        this.title = title;
        this.author = formatAuthors(author);
        this.priceFull = priceFull;
        this.priceRent = priceRent;
        this.isBestSeller = isBestSeller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;

        List<String> thisAuthors = Arrays.stream(author.split(",\\s*"))
                .filter(author -> !author.trim().equalsIgnoreCase("et al."))
                .toList();

        List<String> otherAuthors = Arrays.stream(book.author.split(",\\s*"))
                .filter(author -> !author.trim().equalsIgnoreCase("et al."))
                .toList();

        boolean authorsMatch = thisAuthors.stream().anyMatch(otherAuthors::contains);

        return isBestSeller == book.isBestSeller &&
                Objects.equals(title, book.title) &&
                authorsMatch &&
                Objects.equals(priceFull, book.priceFull) &&
                Objects.equals(priceRent, book.priceRent);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, author, priceFull, priceRent, isBestSeller);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = formatAuthors(author);
    }

    public String getPriceFull() {
        return priceFull;
    }

    public void setPriceFull(String priceFull) {
        this.priceFull = priceFull;
    }

    public String getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(String priceRent) {
        this.priceRent = priceRent;
    }

    public boolean isBestSeller() {
        return isBestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    public String getFullPrice() {
        if(priceRent == null || priceRent.isEmpty()) return priceFull;
        else return priceFull + " For rent:" + priceRent;
    }

    private String formatAuthors(String input) {
        if (input == null || input.isEmpty()) {
            return input; // return if empty or null
        }

        // Delete "by" in the beginning with spaces
        String formatted = input.replaceAll("^by\\s*", "");

        // Delete spaces before commas
        formatted = formatted.replaceAll("\\s+,", ",");

        // Add a space after the comma if there is none
        formatted = formatted.replaceAll(",(\\S)", ", $1");

        return formatted;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + getFullPrice() +
                ", isBestSeller=" + isBestSeller +
                '}';
    }
}
