package org.actionWithElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ActionWithTableElements extends CommonActionsWithElements {
    public ActionWithTableElements(WebDriver webDriver) {
        super(webDriver);
    }
    protected int getRowCount(WebElement frame, List<WebElement> rows) {
        try {
            webDriver.switchTo().frame(frame);
            return rows.size() - 1;
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return 0;
        }
    }

    protected WebElement getRowByIndex(WebElement frame, List<WebElement> rows, Integer index) {
        try {
            webDriver.switchTo().frame(frame);
            return rows.get(index);
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }

    protected WebElement selectMaterialFromSearchResults(WebElement frame, String gridItem) {
        try {
            webDriver.switchTo().frame(frame);
            String xpathColumn = String.format("//a[text()='%s']", gridItem);
            return webDriver.findElement(By.xpath(xpathColumn));
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }

    }
}
