package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@title='Administrative tools']")
    private WebElement administratorLabel;
    @FindBy(xpath = "//div[@title='Demo Administrator']")
    private WebElement adminToolsLabel;
    @FindBy(xpath = "//li[@data-testid='Home Page']")
    private WebElement homePageLabel;

    public HomePage checkIsAminToolLabelsPresent() {
        checkIsElementVisible(adminToolsLabel);
        return this;
    }
    public HomePage checkIsAdministratorLabelPresent() {
        checkIsElementVisible(administratorLabel);
        return this;
    }
    public HomePage checkIsHomePageLabel() {
        checkIsElementVisible(administratorLabel);
        return this;
    }
    public HomePage checkIsRedirectToHomePage() {
        checkIsAminToolLabelsPresent();
        checkIsAdministratorLabelPresent();
        checkIsHomePageLabel();
        return this;
    }




}
