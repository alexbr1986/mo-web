package sabre.autotests.web.rules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import sabre.autotests.web.steps.CommonSteps;

import java.io.IOException;

public class ScreenshotOnFailRule extends TestWatcher {

    private WebDriverRule webDriverRule;

    private ScreenshotOnFailRule(WebDriverRule webDriverRule) {
        this.webDriverRule = webDriverRule;
    }

    public static ScreenshotOnFailRule makeScreenshotOnFail(WebDriverRule webDriverRule) {
        return new ScreenshotOnFailRule(webDriverRule);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        CommonSteps commonSteps = new CommonSteps(webDriverRule.getDriver());
        try {
            commonSteps.saveScreenshot(commonSteps.makeScreenshot().getImage());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
