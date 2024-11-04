package com.demo.pages;

import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class NavigationPage extends PageTools {

    private final By userMenu = By.xpath("//div[@class='user-actions']");
    private final By headerText = By.xpath("//h3");
    private final By logoutButton = By.xpath("//div[@class='user-menu']/a[@href='/logout']");


    public String getHeaderText() {
        return getElementText(headerText);
    }

    public void waitForUserMenu() {
        waitForElementVisibility(userMenu);
    }

    public void hoverOverUserMenu() {
        getSelenideElement(userMenu).hover();
    }

    public void clickLogoutButton() {
        click(logoutButton);
    }
}