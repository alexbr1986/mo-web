package sabre.autotests.web.blocks;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class LeftSideMenu extends HtmlElement {

    @Name("Our hotels link")
    @FindBy(id = "order_18")
    private HtmlElement ourHotelsLink;

    @Name("Our hotels flyout - list view")
    @FindBy(css = "#our-hotels")
    private HtmlElement ourHotelsList;

    @Name("Our hotels flyout - map toggle")
    @FindBy(css = ".map-trigger")
    private HtmlElement mapToggle;

    @Name("Our hotels flyout - map view")
    @FindBy(css = "#map-canvas-nav")
    private HtmlElement ourHotelsMap;

    @Name("Our hotels flyout - map pin")
    @FindBy(css = "div[title='Bodrum']")
    private HtmlElement mapPin;

    @Name("Our hotels flyout - map property box")
    @FindBy(css = "div.property-info")
    private HtmlElement mapProperty;

    public HtmlElement ourHotelsLink() { return ourHotelsLink ; }
    public HtmlElement ourHotelsList() { return ourHotelsList ; }
    public HtmlElement mapToggle() { return mapToggle ; }
    public HtmlElement mapPin() { return mapPin ; }
    public HtmlElement mapProperty() { return mapProperty ; }
    public HtmlElement ourHotelsMap() { return ourHotelsMap ; }
}