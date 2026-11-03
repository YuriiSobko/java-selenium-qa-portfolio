package org.pages.toolbar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.actionWithElements.CommonActionsWithElements;

public class MainToolbarClass extends CommonActionsWithElements {
    public MainToolbarClass(WebDriver webDriver) {
        super(webDriver);

    }

    @FindBy(xpath = "//li[@data-testid='materials-menu']")
    private WebElement materials;
    @FindBy(xpath = "//li[@data-testid='specifications-menu']")
    private WebElement specifications;
    @FindBy(xpath = "//a[@data-testid='demo-menu-materialsearch']")
    private WebElement materialSearch;
    @FindBy(xpath = "//a[@data-testid='demo-menu-newmaterial(anytype)']")
    private WebElement newMaterial;
    @FindBy(xpath = "//a[@data-testid='demo-menu-specificationsearch']")
    private WebElement specificationSearch;
    @FindBy(xpath = "//a[@data-testid='demo-menu-newspecification']")
    private WebElement newSpecification;

    public enum MenuItems {
        MATERIAL_SEARCH("demo-menu-materialsearch"),
        NEW_MATERIAL("demo-menu-newmaterial(anytype)"),
        SPECIFICATION_SEARCH("demo-menu-specificationsearch"),
        NEW_SPECIFICATION("demo-menu-newspecification");
        private String automationId;

        MenuItems(String automationId) {
            this.automationId = automationId;
        }

    }

    public MainToolbarClass openMaterialsMenu() {
        materials.click();
        return this;
    }

    public MainToolbarClass openSpecificationsMenu() {
        specifications.click();
        return this;
    }

    public MainToolbarClass selectMenuListItem(MenuItems menuListItem) {
        selectMenuItemInDropDown(materials, menuListItem.automationId);
        return this;
    }

}


