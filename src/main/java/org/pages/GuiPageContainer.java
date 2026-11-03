package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.search.MaterialSearch;
import org.pages.toolbar.MainToolbarClass;

public class GuiPageContainer {

    private WebDriver webDriver;

    public GuiPageContainer(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public LoginPage getLoginPage(){
        return new LoginPage(webDriver);
    }
    public MainToolbarClass getMainToolbar(){
        return new MainToolbarClass(webDriver);
    }
    public MaterialSearch getMaterialSearch(){
        return new MaterialSearch(webDriver);
    }


}
