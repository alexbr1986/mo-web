package sabre.autotests.web.steps;

import ru.yandex.qatools.ashot.Screenshot;
import sabre.autotests.web.rules.StepsRule;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

import static sabre.autotests.web.consts.TestUsersConsts.IGNORED_ELEMENTS;

public class ScreenSteps {

    private StepsRule stepsRule;

    private Consumer<StepsRule> prod;
    private Consumer<StepsRule> test;
    private Consumer<StepsRule> scenario;

    private Function<StepsRule, Screenshot> screen = stepsRule
            -> stepsRule.commonSteps().makeScreenshotWithout(IGNORED_ELEMENTS);

    public ScreenSteps(StepsRule stepsRule) {
        this.stepsRule = stepsRule;
    }

    public ScreenSteps onProd(Consumer<StepsRule> prod) {
        this.prod = prod;
        return this;
    }

    public ScreenSteps onTest(Consumer<StepsRule> test) {
        this.test = test;
        return this;
    }

    public ScreenSteps withScenario(Consumer<StepsRule> scenario) {
        this.scenario = scenario;
        return this;
    }

    public void compareOnScenario() throws IOException {
        prod.andThen(scenario).accept(stepsRule);
        Screenshot pageProd = screen.apply(stepsRule);

        test.andThen(scenario).accept(stepsRule);
        Screenshot pageTest = screen.apply(stepsRule);

        stepsRule.commonSteps().savePages(pageProd, pageTest);
    }

}
