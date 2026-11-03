package org.pages.search;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ParentPage;
import org.pages.navigators.MaterialNavigator;

import java.util.Arrays;
import java.util.List;

public class MaterialSearch extends ParentPage {

    final static String codeLocator = "//div[@data-testid='MaterialBasicSearchPrimaryCriteria-TextCode']//input[@type='text']";
    final static String nameLocator = "//div[@data-testid='MaterialBasicSearchPrimaryCriteria-TextName']//input[@type='text']";
    final static String typeLocator = "//div[@data-testid='MaterialBasicSearchTypeCriteria-CheckListTypes']//span[contains(@class, 'arrow')]";
    final static String statusLocator = "//div[@data-testid='MaterialBasicSearchStatusCreatedByCriteria-Status']";
    final static String searchButtonLocator = "//div[@data-dxlogicid='BtnSearch']";
    @FindBy(xpath = codeLocator)
    private WebElement codeField;
    @FindBy(xpath = nameLocator)
    private WebElement nameField;
    @FindBy(xpath = typeLocator)
    private WebElement typeField;
    @FindBy(xpath = statusLocator)
    private WebElement statusField;
    @FindBy(xpath = searchButtonLocator)
    private WebElement searchButton;
    @FindBy(xpath = "//div[@data-onclick='openTabInline']")
    private WebElement openMaterialLink;
    @FindBy(xpath = "//table[@id='SearchResultMaterials']")
    private WebElement searchResultTable;
    @FindBy(xpath = "//table[@id='SearchResultMaterials']//tr[@role='row' and not(@class='jqgfirstrow')]")
    private List<WebElement> rows;
    @FindBy(xpath = "//table[@id='SearchResultMaterials']//tr[@role='row' and not(@class='jqgfirstrow')]//td[@style='']")
    private List<WebElement> columns;

    public enum MaterialSearchItems {
        CODE("TextCode"),
        NAME("TextName"),
        TYPE("MaterialBasicSearchTypeCriteria-CheckListTypes"),
        STATUS("MaterialBasicSearchStatusCreatedByCriteria-Status");
        private String automationId;
        MaterialSearchItems(String automationId) {
            this.automationId = automationId;
        }

    }

    public MaterialSearch(WebDriver webDriver) {
        super(webDriver);
    }

    public MaterialSearch inputCode(String code) {
        openFrame();
        typeText(codeField, code);
        closeFrame();
        return this;
    }
    public MaterialSearch inputName(String name) {
        openFrame();
        typeText(nameField, name);
        closeFrame();
        return this;
    }
    public MaterialSearch selectType(String type) {
        openFrame();
        typeText(typeField, type);
        closeFrame();
        return this;
    }
    public MaterialSearch selectStatus(String status) {
        openFrame();
        typeText(statusField, status);
        closeFrame();
        return this;
    }
    public MaterialSearch searchButtonClick() {
        openFrame();
        click(searchButton);
        closeFrame();
        return this;
    }
    public MaterialSearch checkIsCodeFieldPresent() {
        openFrame();
        checkIsElementVisible(codeField);
        closeFrame();
        return this;
    }
    public MaterialSearch checkIsNameFieldPresent() {
        openFrame();
        checkIsElementVisible(nameField);
        closeFrame();
        return this;
    }
    public MaterialSearch checkIsTypeFieldPresent() {
        openFrame();
        checkIsElementVisible(typeField);
        closeFrame();
        return this;
    }
    public MaterialSearch checkIsStatusFieldPresent() {
        openFrame();
        checkIsElementVisible(statusField);
        closeFrame();
        return this;
    }
    public MaterialSearch checkIsSearchButtonPresent() {
        openFrame();
        checkIsElementVisible(searchButton);
        closeFrame();
        return this;
    }


    public MaterialSearch checkIsMaterialSearchPageOpened() {
         checkIsCodeFieldPresent();
         checkIsNameFieldPresent();
         checkIsTypeFieldPresent();
         checkIsStatusFieldPresent();
         checkIsSearchButtonPresent();
        return this;
    }

    public void checkIsSearchedMaterialFound(String materialName) {
        boolean isMaterialFound = isSearchedMaterialFoundInResults(materialName);
        Assert.assertTrue("Material was not found", isMaterialFound);
    }

    public MaterialSearch inputType(String type) {
        selectItemsInCheckBoxDropDown(typeField, type);
        return this;
    }

    public MaterialSearch inputStatus(String type) {
        selectItemsInCheckBoxDropDown(statusField, type);
        return this;
    }

    public MaterialNavigator openSearchedMaterial(int rowIndex, int cellIndex,String materialName) {
        openFrame();
        selectMaterial(searchResultTable,  rows, columns,  rowIndex,  cellIndex).click();
        closeFrame();
        return new MaterialNavigator(webDriver);
    }

    public MaterialSearch checkResultsTableCellData(int rowIndex, int cellIndex, String expectedData) {
        openFrame();
        String actualData = checkTableData( searchResultTable ,rows, columns, rowIndex, cellIndex);
        closeFrame();
        Assert.assertEquals("Expected data was not found in the table", expectedData, actualData);
        return this;
    }

}






