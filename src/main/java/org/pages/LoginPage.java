package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.data.TestData.BASE_URL;
import static org.data.TestData.USER_NAME;
import static org.data.TestData.USER_PASSWORD;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    @FindBy(xpath = "//input[@id='UserName']")
    private WebElement userName;
    @FindBy(xpath = "//input[@id='Password']")
    private WebElement userPassword;
    @FindBy(xpath = "//button[@id='btnDev']")
    private WebElement enterDemoAppWeb;


    public LoginPage setUserName(String name) {
        typeText(userName, name);
        return  this;
    }
    public LoginPage setPassword(String password) {
        typeText(userPassword, password);
        return this;
    }
    public  LoginPage  enterDemoAppWebButtonClick(){
        click(enterDemoAppWeb);
        return this;
    }

    public HomePage loginToDemoAppWeb(){
        if (BASE_URL == null || BASE_URL.isBlank() || USER_NAME == null || USER_PASSWORD == null) {
            throw new IllegalStateException("Set QA_BASE_URL, QA_USERNAME and QA_PASSWORD in your private environment.");
        }
        openPage(BASE_URL);
        setUserName(USER_NAME);
        setPassword(USER_PASSWORD);
        enterDemoAppWebButtonClick();
        return new HomePage(webDriver);
    }

}
