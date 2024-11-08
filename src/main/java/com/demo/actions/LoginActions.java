package com.demo.actions;

import com.demo.pages.Pages;
import io.qameta.allure.Step;
import org.testng.Assert;

public class LoginActions {

    @Step("Login with enrolled client's data")
    public void doLogin(String userName, String password) {
        Pages.loginPage().waitForLoginForm();
        Pages.loginPage().setUsername(userName);
        Pages.loginPage().setPassword(password);
        Pages.loginPage().clickLoginButton();
        Pages.navigationPage().waitForUserMenu();
        Assert.assertEquals(Pages.navigationPage().getHeaderText(), "Adamtms Jacksonniqjj");
    }

    @Step("Logout")
    public void doLogout() {
        Pages.navigationPage().hoverOverUserMenu();
        Pages.navigationPage().clickLogoutButton();
        Assert.assertTrue(Pages.loginPage().isLoginFormVisible(),
                "The login form is not visible");
    }
}
