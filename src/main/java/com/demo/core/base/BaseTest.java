package com.demo.core.base;

import com.codeborne.selenide.Selenide;
import com.demo.core.allure.AllureLogger;
import com.demo.core.config.SelenideConfig;
import com.demo.utils.Constants;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseTest extends AllureLogger {

    @BeforeMethod(alwaysRun = true, description = "Opening web browser...")
    public void setUp() throws Exception {

        logInfo("Creating web driver configuration..."); //test
        SelenideConfig.createBrowserConfig(System.getProperty("selenide.browser", "firefox"));
        configLog(this.getClass().getSimpleName());
        logInfo("Open browser...");
        Selenide.open(Constants.URL);
    }

    @AfterMethod(alwaysRun = true, description = "Closing web browser...")
    public void tearDown(ITestResult result) {
        Selenide.closeWebDriver();
        logInfo("Web driver closed!");
    }
}
