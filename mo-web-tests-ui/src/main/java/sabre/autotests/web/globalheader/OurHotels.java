package sabre.autotests.web.globalheader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import sabre.autotests.web.rules.StepsRule;

import java.io.IOException;

import static sabre.autotests.web.rules.StepsRule.newStepsRule;

public class OurHotels {

    @Rule
    public StepsRule stepsRule = newStepsRule();

    @Before
    public void setUp() {
        stepsRule.screenSteps().onProd(st -> st.urlSteps().webProd().open())
                .onTest(st -> st.urlSteps().webTest().open());
    }

    @Test
    @Title("Test opened Our Hotels view layout")
    public void ourHotelsView() throws IOException {
        stepsRule.screenSteps().withScenario(st -> st.commonSteps().shouldSee(st.pages().onMainPage().navProperty())
                .clicksOn(st.pages().onMainPage().navProperty().ourHotelsLink())
                .shouldSee(st.pages().onMainPage().ourHotels()))
                .compareOnScenario();
    }
}
