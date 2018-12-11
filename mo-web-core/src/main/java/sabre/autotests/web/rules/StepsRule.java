package sabre.autotests.web.rules;

import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import sabre.autotests.web.pages.Pages;
import sabre.autotests.web.steps.CommonSteps;
import sabre.autotests.web.steps.OpenUrlSteps;
import sabre.autotests.web.steps.ScreenSteps;

import static org.junit.rules.RuleChain.emptyRuleChain;
import static sabre.autotests.web.rules.ScreenshotOnFailRule.makeScreenshotOnFail;

public class StepsRule extends ExternalResource {

    private WebDriverRule webDriverRule = new WebDriverRule();
    private RuleChain chain = emptyRuleChain();

    private CommonSteps commonSteps;
    private ScreenSteps screenSteps;
    private OpenUrlSteps urlSteps;
    private Pages pages;

    public static StepsRule newStepsRule() {
        return new StepsRule();
    }

    public WebDriver getDriver() {
        return webDriverRule.getDriver();
    }

    public CommonSteps commonSteps() {
        if (commonSteps == null) {
            commonSteps = new CommonSteps(webDriverRule.getDriver());
        }
        return commonSteps;
    }

    public ScreenSteps screenSteps() {
        if (screenSteps == null) {
            screenSteps = new ScreenSteps(this);
        }
        return screenSteps;
    }

    public OpenUrlSteps urlSteps() {
        if (urlSteps == null) {
            urlSteps = new OpenUrlSteps(webDriverRule.getDriver());
        }
        return urlSteps;
    }

    public Pages pages() {
        if (pages == null) {
            pages = new Pages(webDriverRule.getDriver());
        }
        return pages;
    }

    public StepsRule around(TestRule rule) {
        chain = chain.around(rule);
        return this;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        chain = chain.around(webDriverRule)
                .around(makeScreenshotOnFail(webDriverRule));

        return chain.apply(base, description);
    }
}
