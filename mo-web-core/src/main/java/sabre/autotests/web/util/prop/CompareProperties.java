package sabre.autotests.web.util.prop;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;
import ru.qatools.properties.providers.MapPropPathReplacerProvider;


@Resource.Classpath("moweb.properties")
public class CompareProperties {

    private static CompareProperties instance;

    public static CompareProperties props() {
        if (null == instance) {
            instance = new CompareProperties();
        }
        return instance;
    }

    private CompareProperties() {
        PropertyLoader.newInstance()
                .withPropertyProvider(new MapPropPathReplacerProvider(System.getProperties()))
                .populate(this);
    }

    @Property("mo.test.host")
    private String testHost = "https://mandarinoriental-test.sabredemos.com";

    @Property("mo.prod.host")
    private String prodHost = "https://mandarinoriental.com";

    @Property("ui.lang")
    private String lang = "en";

    public String testHost() {return testHost;}
    public String prodHost() {return prodHost;}
    public String lang() {
        return lang;
    }
}
