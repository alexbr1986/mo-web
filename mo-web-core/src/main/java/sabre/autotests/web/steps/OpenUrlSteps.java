package sabre.autotests.web.steps;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import javax.ws.rs.core.UriBuilder;

import static javax.ws.rs.core.UriBuilder.fromUri;
import static sabre.autotests.web.util.prop.CompareProperties.props;

public class OpenUrlSteps {

    private WebDriver driver;

    public OpenUrlSteps(WebDriver driver) {
        this.driver = driver;
    }

    private UriBuilder uri;

    public OpenUrlSteps webProd() {
        uri = fromUri(props().prodHost());
        return this;
    }

    public OpenUrlSteps webTest() {
        uri = fromUri(props().testHost());
        return this;
    }

    public OpenUrlSteps withHost(String host) {
        uri = fromUri(host);
        return this;
    }

    public OpenUrlSteps withPath(String path) {
        uri.path(path);
        return this;
    }

    public OpenUrlSteps withParam(String name, String value) {
        uri.queryParam(name, value);
        return this;
    }
    public void open() {
        openUrl(uri.build().toString());
    }

    @Step("Opening URL «{0}»")
    public void openUrl(String url) {
        driver.get(url);
    }
}
