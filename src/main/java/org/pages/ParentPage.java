package org.pages;

import org.actionWithElements.ActionWithTableElements;
import org.openqa.selenium.WebDriver;
import org.actionWithElements.CommonActionsWithElements;

abstract public class ParentPage extends CommonActionsWithElements {

    protected ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CommonActionsWithElements getCommonActions() {
        return new CommonActionsWithElements(webDriver);
    }
    public ActionWithTableElements getTableActions() {
        return new ActionWithTableElements(webDriver);
    }
}
