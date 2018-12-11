package sabre.autotests.web.steps;

import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsNot.not;
import static ru.yandex.qatools.htmlelements.matchers.WrapsElementMatchers.hasText;
import static sabre.autotests.web.util.Utils.*;

public class CommonSteps {

    private WebDriver driver;

    public CommonSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public CommonSteps clicksOn(HtmlElement... elements) {
        for (HtmlElement element : elements) {
            element.click();//clicksOnElementWithWaiting(element, driver);
        }
        return this;
    }

    @Step
    public CommonSteps hover(HtmlElement... elements) {
        for (HtmlElement element : elements) {
            new Actions(driver).moveToElement(element.getWrappedElement()).perform();
        }
        return this;
    }

    public <T extends Matcher<HtmlElement>> CommonSteps should(String description, T matcher, HtmlElement... elements) {
        assertThat(format("Not all elements <%s> %s", asList(elements), description),
                asList(elements),
                everyItem(matcher));
        return this;
    }

    public <T extends Matcher<TypifiedElement>> CommonSteps should(String description, T matcher, TypifiedElement... elements) {
        assertThat(format("Not all elements <%s> %s", asList(elements), description),
                asList(elements),
                everyItem(matcher));
        return this;
    }

    @Step("On this page following elements should be visible: «{0}»")
    public CommonSteps shouldSee(HtmlElement... elements) {
        return should("visible", withWaitFor(isPresent()), elements);
    }

    @Step("Element «{0}». Text «{1}»")
    public CommonSteps shouldSeeThatElementHasText(HtmlElement element, Matcher<String> matcher) {
        assertThat("Element [ " + element + " ] doesn't contain required text", element, withWaitFor(hasText(matcher)));
        return this;
    }

    @Step("On this page following elements should NOT be visible «{0}»")
    public CommonSteps shouldNotSee(HtmlElement... elements) {
        return should("not visible", withWaitFor(not(isPresent())), elements);
    }

    @Step("Clicking on element «{0}» when it's visible on the page")
    public CommonSteps clickWhenSee(HtmlElement... elements) {
        return shouldSee(elements).clicksOn(elements);
    }

    @Step("Clicking on the 1st list element «{0}», with attribute «title» equal to «{1}»")
    public CommonSteps clickOnFirstElementWithTitle(List<HtmlElement> elements, String title) {
        return clicksOn(findFirstElementWithAttribute(elements, "title", title));
    }

    @Step("Entering text «{1}» in element «{0}»")
    public CommonSteps sendKeys(HtmlElement element, String text) {
        element.sendKeys(text);
        return this;
    }

    @Step("Searching for list element with attribute «{1}» equal to«{2}»")
    public HtmlElement findFirstElementWithAttribute(List<HtmlElement> elements, String attrName, String attrValue) {
        List<HtmlElement> matchedElements = elements.stream()
                .filter(element -> element.getAttribute(attrName).equals(attrValue)).collect(toList());
        assertThat("Cannot find elements with specified attribute", matchedElements, not(empty()));
        return matchedElements.get(0);
    }

    public Screenshot makeScreenshot() {
        AShot ashot = new AShot();
        return ashot.takeScreenshot(driver);
    }

    public Screenshot makeScreenshotWithout(Set<By> ignoredElements) {
        AShot ashot = new AShot();
        ashot.ignoredElements(ignoredElements);
        return ashot.takeScreenshot(driver);
    }

    @Step("Browser window dimensions: (Width: {0}, Height: {1})")
    public CommonSteps setsWindowSize(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
        return this;
    }

    @Step("Saving comparison results")
    public CommonSteps savePages(Screenshot page1, Screenshot page2) throws IOException {
        saveScreenshot(page1.getImage());
        saveScreenshot(page2.getImage());
        saveDiffScreenshot(diff(page1, page2).getMarkedImage());

        assertThat("Production/testing pages are different", !diff(page1, page2).hasDiff());
        return this;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(BufferedImage image) throws IOException {
        return imageAsByte(image);
    }

    @Attachment(value = "DIFF", type = "image/png")
    public byte[] saveDiffScreenshot(BufferedImage image) throws IOException {
        return imageAsByte(image);
    }

    private byte[] imageAsByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        return baos.toByteArray();
    }
}
