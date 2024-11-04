package com.demo.actions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MainActions {

    public void switchToTab(int index) {
        Selenide.switchTo().window(index);
    }

    public void openNewTab() {
        Selenide.executeJavaScript("window.open()");
    }

    public void openLinkFromClipboard() throws IOException, UnsupportedFlavorException {
        String clipboardValue = Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor).toString();

        Selenide.executeJavaScript("window.location.href = '" + clipboardValue + "'");
    }

    public String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

}