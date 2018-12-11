package sabre.autotests.web.blocks;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class NavGlobal extends HtmlElement {

    @Name("Left side menu toggle")
    @FindBy(css = ".menu-icon")
    private HtmlElement menuToggle;

    @Name("MO logo")
    @FindBy(css = ".title-bar-title")
    private HtmlElement logo;

    public HtmlElement menuToggle() { return menuToggle ; }
    public HtmlElement logo() { return logo ; }
}
