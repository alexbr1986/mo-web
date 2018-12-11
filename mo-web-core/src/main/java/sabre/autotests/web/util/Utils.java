package sabre.autotests.web.util;

import org.hamcrest.Matcher;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder;

import java.io.IOException;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.allOf;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.should;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.timeoutHasExpired;
import static ru.yandex.qatools.htmlelements.matchers.WrapsElementMatchers.exists;
import static ru.yandex.qatools.htmlelements.matchers.WrapsElementMatchers.isDisplayed;

public class Utils {

    /**
     * Returns randoms string
     *
     * @return random string with length = 13
     */
    public static String getRandomString() {
        return Long.toString(Math.abs(new Random().nextLong()), 36);
    }

    /**
     * Waiting for 15 seconds when trying to click element
     */
    public static void clicksOnElementWithWaiting(final WebElement element, WebDriver driver) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    /* MATCHERS */
    public static Matcher isPresent() {
        return allOf(exists(), isDisplayed());
    }

    public static <T> MatcherDecoratorsBuilder<T> withWaitFor(Matcher<T> matcher) {
        return should(matcher).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(15)));
    }

    public static ImageDiff diff(Screenshot screenshot1, Screenshot screenshot2) throws IOException {
        ImageDiffer differ = new ImageDiffer();
        return differ.makeDiff(screenshot1, screenshot2);
    }
}
