package com.demo.pages;

import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class LoginPage extends PageTools {

    private final By logInButton = By.xpath("//button[@class='MuiButton-root MuiButton-variantSolid MuiButton-colorPrimary MuiButton-sizeMd styles_authSolidButton__grj_G css-yx8c15']");
    private final By loginForm = By.xpath("//div[@class='MuiCard-root MuiCard-vertical MuiCard-variantPlain MuiCard-colorNeutral MuiCard-sizeMd styles_loginCard___aTcy css-11ib4gw']");
    private final By usernameField = By.xpath("//input[@class='MuiInput-input css-1u0jcuo']");
    private final By passwordField = By.xpath("//input[@class='MuiInput-input css-fqt4w4']");

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