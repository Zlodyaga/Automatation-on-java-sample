package com.demo.data;

import java.util.Objects;

public class Transaction {

    private double amount;
    private double futureBalance;
    private String type;
    private String date;
    private String category;

    public Transaction(double amount, double futureBalance, String type, String date, String category) {
        this.amount = amount;
        this.futureBalance = futureBalance;
        this.type = type;
        this.date = date;
        this.category = category;
    }

    // Getters and Setters

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFutureBalance() {
        return futureBalance;
    }

    public void setFutureBalance(double futureBalance) {
        this.futureBalance = futureBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Double.compare(that.futureBalance, futureBalance) == 0 &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, futureBalance, type, date, category);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", futureBalance=" + futureBalance +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
