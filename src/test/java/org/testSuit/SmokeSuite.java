package org.testSuit;

import org.categories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mainToolBarTests.MaterialSearchTests;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
    MaterialSearchTests.class,
})
public class SmokeSuite {

}
