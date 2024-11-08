package com.demo.pages;

import com.demo.actions.Actions;
import com.demo.core.base.PageTools;
import org.openqa.selenium.By;

public class NavigationPage extends PageTools {

    private final By userMenu = By.xpath("//cui-menu[@container='headMenu']");
    private final By headerText = By.xpath("//span[@class='ui-button-text']/span[@class='profile-name']");
    private final By logoutButton = By.xpath("//a[@aria-label='Logout']");
    private final By logoutButtonOk = By.xpath("//button[@class='dialog-button-ok ui-button ui-corner-all ui-widget']");


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
        scrollToElement(userMenu);
        click(logoutButton);
        click(logoutButtonOk);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}