package com.demo.pages;

import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class LoginPage extends PageTools {

    private final By logInButton = By.xpath("//button[contains(text(), 'some text')]");
    private final By loginForm = By.xpath("//form[contains(@onsubmit, 'some text')]");
    private final By usernameField = By.xpath("//input[@name='some text']");
    private final By passwordField = By.xpath("//input[@name='some text']");

    public void clickLoginButton() {
        click(logInButton);
    }

    public void setUsername(String username) {
        type(username, usernameField);
    }

    public void setPassword(String password) {
        type(password, passwordField);
    }

    public void waitForLoginForm() {
        waitForElementVisibility(loginForm);
    }

    public boolean isLoginFormVisible() {
        return isElementVisible(loginForm);
    }
}