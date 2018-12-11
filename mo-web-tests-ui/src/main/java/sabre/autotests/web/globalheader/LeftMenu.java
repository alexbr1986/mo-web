package sabre.autotests.web.globalheader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import sabre.autotests.web.rules.StepsRule;

import java.io.IOException;
import static sabre.autotests.web.rules.StepsRule.newStepsRule;

public class LeftMenu {

    @Rule
    public StepsRule stepsRule = newStepsRule();

    @Before
    public void setUp() {
        stepsRule.screenSteps().onProd(st -> st.urlSteps().webProd().open())
                .onTest(st -> st.urlSteps().webTest().open());
    }

    @Test
    @Title("Test opened left-side menu layout")
    public void menuView() throws IOException {
        stepsRule.screenSteps().withScenario(st -> st.commonSteps().shouldSee(st.pages().onMainPage().navGlobal())
                .clicksOn(st.pages().onMainPage().navGlobal().menuToggle())
                .shouldSee(st.pages().onMainPage().leftSideMenu()))
                .compareOnScenario();
    }

    @Test
    @Title("Test opened Our Hotels flyout - list view")
    public void ourHotelsListView() throws IOException {
        stepsRule.screenSteps().withScenario(st -> st.commonSteps().shouldSee(st.pages().onMainPage().navGlobal())
                .clicksOn(st.pages().onMainPage().navGlobal().menuToggle())
                .shouldSee(st.pages().onMainPage().leftSideMenu())
                .hover(st.pages().onMainPage().leftSideMenu().ourHotelsLink())
                .shouldSee(st.pages().onMainPage().leftSideMenu().ourHotelsList()))
                .compareOnScenario();
    }

    @Test
    @Title("Test opened Our Hotels flyout - map view")
    public void ourHotelsMapView() throws IOException {
        stepsRule.screenSteps().withScenario(st -> st.commonSteps().shouldSee(st.pages().onMainPage().navGlobal())
                .clicksOn(st.pages().onMainPage().navGlobal().menuToggle())
                .shouldSee(st.pages().onMainPage().leftSideMenu())
                .hover(st.pages().onMainPage().leftSideMenu().ourHotelsLink())
                .shouldSee(st.pages().onMainPage().leftSideMenu().ourHotelsList())
                .clicksOn(st.pages().onMainPage().leftSideMenu().mapToggle())
                .shouldSee(st.pages().onMainPage().leftSideMenu().ourHotelsMap())
                .clicksOn(st.pages().onMainPage().leftSideMenu().mapPin())
                .shouldSee(st.pages().onMainPage().leftSideMenu().mapProperty()))
                .compareOnScenario();
    }
}
