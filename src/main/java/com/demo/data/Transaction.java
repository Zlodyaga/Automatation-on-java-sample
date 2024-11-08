package com.demo.data;

import java.util.Objects;

public class Transaction {

    private String amount;
    private String futureBalance;
    private String type;
    private String date;
    private String category;
    private String note;

    public Transaction(String amount, String type, String date, String category, String note) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    public Transaction(String amount, String futureBalance, String type, String date, String category, String note) {
        this(amount, type, date, category, note);
        this.futureBalance = futureBalance;
    }


    // Getters and Setters

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFutureBalance() {
        return futureBalance;
    }

    public void setFutureBalance(String futureBalance) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(that.amount.replace(",","."), amount.replace(",",".")) &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(note, that.note) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, type, date, category, note);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                (futureBalance != null ? ", futureBalance=" + futureBalance : "") +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                (note != null ? ", note=" + note : "") +
                '}';
    }
}
