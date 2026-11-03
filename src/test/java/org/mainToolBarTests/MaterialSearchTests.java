package org.mainToolBarTests;

import org.BaseTest;
import org.categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;

import static org.pages.toolbar.MainToolbarClass.MenuItems.MATERIAL_SEARCH;

public class MaterialSearchTests extends BaseTest {


    @Test
    @Category(SmokeTestFilter.class)
    public void TC001_openMaterialSearch() {
        demoApp.getLoginPage().loginToDemoAppWeb();
        demoApp.getMainToolbar().selectMenuListItem(MATERIAL_SEARCH);
        demoApp.getMaterialSearch().checkIsMaterialSearchPageOpened();
    }

    @Test
    public void TC002_searchForMaterialUsingCode() {
        demoApp.getLoginPage().loginToDemoAppWeb();
        demoApp.getMainToolbar().selectMenuListItem(MATERIAL_SEARCH);
        demoApp.getMaterialSearch().checkIsMaterialSearchPageOpened()
                .inputCode("DEMO-CODE-001")
                .searchButtonClick()
                .checkResultsTableCellData(0,2, "DEMO-CODE-001");

    }
    @Test
    public void TC003_searchForMaterialUsingName() {
        demoApp.getLoginPage().loginToDemoAppWeb();
        demoApp.getMainToolbar().selectMenuListItem(MATERIAL_SEARCH);
        demoApp.getMaterialSearch().checkIsMaterialSearchPageOpened()
                .inputName("DEMO MATERIAL")
                .searchButtonClick()
                .checkResultsTableCellData(0,3, "DEMO MATERIAL");

    }
    @Test
    public void TC004_searchForMaterialUsingNameAndType() {
        demoApp.getLoginPage().loginToDemoAppWeb();
        demoApp.getMainToolbar().selectMenuListItem(MATERIAL_SEARCH);
        demoApp.getMaterialSearch().checkIsMaterialSearchPageOpened()
                .inputName("DEMO FORMULA")
                .inputType("Formula")
                .searchButtonClick()
                .checkResultsTableCellData(0,3, "DEMO FORMULA");
    }

    @Test
    public void TC005_openMaterialUsingName() {
        demoApp.getLoginPage().loginToDemoAppWeb();
        demoApp.getMainToolbar().selectMenuListItem(MATERIAL_SEARCH);
        demoApp.getMaterialSearch().checkIsMaterialSearchPageOpened()
                .inputName("DEMO FORMULA")
                .inputType("Formula")
                .searchButtonClick()
                .checkResultsTableCellData(0, 3, "DEMO FORMULA")
                .openSearchedMaterial(0, 3, "DEMO FORMULA")
                .getTableActions();
    }

}
