package org.pages.navigators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ParentPage;
import org.pages.search.MaterialSearch;

import java.util.List;

public class MaterialNavigator extends ParentPage {
    public MaterialNavigator(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@data-onclick='openTabInline']")
    private WebElement materialName;
    @FindBy(xpath = "//table[@id='SearchResultMaterials']")
    private WebElement materialCode;



    public MaterialNavigator checkNameOfMaterial(){
        return this;
    }

    public MaterialNavigator checkTypeOfMaterial(){
        return this;
    }
    public MaterialNavigator checkCodeOfMaterial(){
        return this;
    }


    public MaterialNavigator checkIsRedirectToMaterialNavigator() {
       // checkIsMaterialSearchPageOpened();
        return this;
    }

}