package org.actionWithElements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainActionElements {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    protected Logger logger = Logger.getLogger(getClass());

    public MainActionElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element ");
        Assert.fail("Can not work with element ");
    }

    public CommonActionsWithElements getCommonActions() {
        return new CommonActionsWithElements(webDriver);
    }
    public ActionWithTableElements getTableActions() {
        return new ActionWithTableElements(webDriver);
    }
    CommonActionsWithElements commonActionsWithElements = new CommonActionsWithElements(webDriver);

}
