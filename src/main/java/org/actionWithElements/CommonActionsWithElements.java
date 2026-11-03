package org.actionWithElements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait5, webDriverWait10, webDriverWait15, webDriverWait20;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait5 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        webDriverWait20 = new WebDriverWait(webDriver, Duration.ofSeconds(20));
    }

    void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element ");
        Assert.fail("Can not work with element ");
    }


    public void typeText(WebElement webElement, String input) {
        try {
            webElement.clear();
            webElement.sendKeys(input);
            logger.info("Element was filled with value: " + input);

        } catch (Exception e) {
            throw new IllegalStateException("Cannot work with element", e);
        }
    }

    public void getTextFromField(WebElement webElement) {
        try {
            webElement.getAttribute("value");
            logger.info("Element was filled with value: ");
        } catch (Exception e) {
            throw new IllegalStateException("Cannot work with element", e);
        }
    }

    protected void click(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked ");

        } catch (Exception e) {
            throw new IllegalStateException("Cannot work with element", e);
        }
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


    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + "Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;

        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    public String getElementName(){
        return null;
    }

    // check if element is visible
    public void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    public void checkIsElementInsideFrameVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisibleInsideIframes(locator));
    }


    protected void openPage(String url) {
        webDriver.get(url);
        logger.info("Page was opened: " + url);
    }

    private String getElementName(WebElement webElement) {
        String elementName;
        try {
            elementName = webElement.getAccessibleName();
        } catch (Exception e) {
            elementName = "";
        }
        return elementName;
    }


    protected void selectMenuItemInDropDown(WebElement dropDownElement, String menuItem) {
        try {
            String xpath = String.format("//a[@data-testid='%s']", menuItem);
            dropDownElement.click();
            webDriver.findElement(By.xpath(xpath)).click();
            logger.info(menuItem + " value was selected in DropDown" + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
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

    protected void openFrame() {
        try {
            webDriver.switchTo().defaultContent();
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
            logger.info(menuItem + " was selected in DropDown: " + getElementName(dropDownElement));
            dropDownElement.click();
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDD(WebElement dropDownElement, String valueForSelect) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByValue(valueForSelect);
            logger.info(valueForSelect + " value was selected in DropDown" + getElementName(dropDownElement));
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

    protected String checkTableData(WebElement table, List<WebElement> rows,
                                    List<WebElement> cells, int rowIndex, int cellIndex) {
        try {
            String actualText = "";
            for (int i = 0; i < rows.size(); i++) {
                if (i == rowIndex) {
                    for (int j = 0; j < cells.size(); j++) {
                        if (j == cellIndex) {
                            actualText = cells.get(j).getText().trim();
                        }
                    }
                }
            }
            return actualText;
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }


    protected WebElement selectMaterial(WebElement table, List<WebElement> rows,
                                    List<WebElement> columns, int rowIndex, int cellIndex) {
        try {
            WebElement openLinkElement = null;
            for (int i = 0; i < rows.size(); i++) {
                if (i == rowIndex) {
                    for (int j = 0; j < columns.size(); j++) {
                        if (j == cellIndex) {
                           String actualText = columns.get(j).getText().trim();
                            String xpathElementOpenLink = String.format("//a[@data-uqkey='OPEN'  and contains(text(), '%s')]", actualText);
                            openLinkElement = webDriver.findElement(By.xpath(xpathElementOpenLink));
                        }
                    }
                }
            }
            return openLinkElement;
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }


    protected boolean isSearchedMaterialFoundInResults(String materialName) {
        try {
            if (isIframePresent()) {
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

}

