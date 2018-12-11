package sabre.autotests.web.util.prop;

import org.openqa.selenium.Platform;
import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;
import ru.qatools.properties.providers.MapPropPathReplacerProvider;

@Resource.Classpath("webdriver.properties")
public class WebDriverProperties {

    private static WebDriverProperties instance;

    public static WebDriverProperties props() {
        if (null == instance) {
            instance = new WebDriverProperties();
        }
        return instance;
    }

    private WebDriverProperties() {
        PropertyLoader.newInstance()
                .withPropertyProvider(new MapPropPathReplacerProvider(System.getProperties()))
                .populate(this);
    }

    @Property("webdriver.driver")
    private String driverType = "firefox";

    @Property("webdriver.version")
    private String version = "3.14.0";

    @Property("webdriver.platform")
    private Platform platform = Platform.ANY;

    @Property("local.selenium")
    private boolean useLocalSelenium = false;

    public String driverType() {
        return driverType;
    }

    public String version() {
        return version;
    }

    public Platform platform() {
        return platform;
    }

    public boolean useLocalSelenium() {
        return useLocalSelenium;
    }

}
