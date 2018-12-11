package sabre.autotests.web.blocks;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class NavProperty extends HtmlElement {

    @Name("Our hotels link")
    @FindBy(linkText = "Our Hotels")
    private HtmlElement ourHotelsLink;

    @Name("Offers link")
    @FindBy(linkText = "Offers")
    private HtmlElement offersLink;

    @Name("Dining link")
    @FindBy(linkText = "Dining")
    private HtmlElement diningLink;

    @Name("Wellness link")
    @FindBy(linkText = "Wellness")
    private HtmlElement wellnessLink;

    public HtmlElement ourHotelsLink() { return ourHotelsLink ; }
    public HtmlElement diningLink() { return diningLink ; }
    public HtmlElement offersLink() { return offersLink ; }
    public HtmlElement wellnessLink() { return wellnessLink ; }
}
