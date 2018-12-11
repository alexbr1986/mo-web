package sabre.autotests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import sabre.autotests.web.blocks.LeftSideMenu;
import sabre.autotests.web.blocks.NavGlobal;
import sabre.autotests.web.blocks.NavProperty;

public class BrandHomePage {

    public BrandHomePage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Global header - menu, logo")
    @FindBy(css = ".nav-section.global")
    private NavGlobal navGlobal;

    @Name("Global header - links, langs, fans")
    @FindBy(css = ".nav-section.global")
    private NavProperty navProperty;

    @Name("Left side menu")
    @FindBy(id = "main-menu")
    private LeftSideMenu leftSideMenu;

    @Name("Our hotels view")
    @FindBy(id = "search-hotels")
    private HtmlElement ourHotels;

    public NavGlobal navGlobal() { return navGlobal; }
    public NavProperty navProperty() { return navProperty; }
    public LeftSideMenu leftSideMenu() { return leftSideMenu ; }
    public HtmlElement ourHotels() { return ourHotels ; }
}
