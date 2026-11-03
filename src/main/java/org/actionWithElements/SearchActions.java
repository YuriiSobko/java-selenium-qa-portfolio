package org.actionWithElements;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchActions extends CommonActionsWithElements {

    public SearchActions(WebDriver webDriver) {
        super(webDriver);
    }

    protected void openFrame() {
        try {
            WebElement frame = webDriver.findElement(By.tagName("iframe"));
            webDriver.switchTo().frame(frame);
            logger.info("Frame was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void closeFrame() {
        try {
            webDriver.switchTo().defaultContent();
            logger.info("Frame was closed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectItemsInCheckBoxDropDown(WebElement dropDownElement, String menuItem) {
        try {
            openFrame();
            dropDownElement.click();
            WebElement checkAllXpath = webDriver.findElement(By.xpath("//span[text()='Check all']//preceding-sibling::div[@class='checkbox checked']"));
            String classAttribute = checkAllXpath.getAttribute("class");
            if (classAttribute.contains("checked")) {
                checkAllXpath.click();
            }
            List<WebElement> checkboxes = webDriver.findElements(By.xpath("//div[@class='DivItem']"));
            for (WebElement checkbox : checkboxes) {
                String checkboxXpath = String.format("//div[@class='DivItem']//span[@title='%s']", menuItem);
                WebElement checkboxLabel = checkbox.findElement(By.xpath(checkboxXpath));

                if (checkboxLabel != null) {
                    checkboxLabel.click();
                    break;
                }
            }
           // logger.info(menuItem + " was selected in DropDown: " + getElementName(dropDownElement));
            dropDownElement.click();
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected int getRowCount(List<WebElement> rows) {
        try {
            openFrame();
            return rows.size() - 1;
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return 0;
        }
    }

    protected WebElement getRowByIndex(List<WebElement> rows, Integer index) {
        try {
            openFrame();
            return rows.get(index);
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }

    protected WebElement selectMaterialFromSearchResults(String gridItem) {
        try {
            openFrame();
            String xpathColumn = String.format("//a[text()='%s']", gridItem);
            return webDriver.findElement(By.xpath(xpathColumn));
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }
    private boolean isIframePresent() {
        List<WebElement> iframes = webDriver.findElements(By.tagName("iframe"));
        if (iframes.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void checkIsElementInsideFrameVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisibleInsideIframes(locator));
    }

    protected boolean isElementVisibleInsideIframes(String locator) {
        List<WebElement> iframes = webDriverWait20.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
        for (WebElement iframe : iframes) {
            try {
                webDriver.switchTo().frame(iframe);
                List<WebElement> elements = webDriver.findElements(By.xpath(locator));
                if (elements.isEmpty()) {
                    webDriver.switchTo().defaultContent();
                    continue;
                }
                webDriverWait10.until(ExpectedConditions.visibilityOf(elements.get(0)));
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    webDriver.switchTo().defaultContent();
                    return true;
                }
                webDriver.switchTo().defaultContent();
            } catch (Exception e) {
                logger.info("Element not found in iframe. Trying next iframe...");
            }
        }
        return false;
    }

    protected boolean isSearchedMaterialFoundInResults(String materialName) {
        try {
            if(isIframePresent()){
                closeFrame();
                openFrame();
            }
            String xpathColumn = String.format("//a[text()='%s']", materialName);
            boolean isMaterialFound = webDriver.findElement(By.xpath(xpathColumn)).isDisplayed();
            if (isMaterialFound) {
                logger.info("Material is found in search results");
            } else {
                logger.info("Material is not found in search results");
            }
            closeFrame();
            return isMaterialFound;
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return false;
        }
    }

    protected void click(WebElement webElement) {
        try {
            if (isIframePresent()) {
                openFrame();
                webElement.click();
                logger.info("Element was clicked ");
                closeFrame();
            } else {
                webElement.click();
                logger.info("Element was clicked ");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Cannot work with element", e);
        }
    }

}
