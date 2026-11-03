package org.testSuit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mainToolBarTests.MaterialSearchTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MaterialSearchTests.class
})
public class RegressionSuite {

}
