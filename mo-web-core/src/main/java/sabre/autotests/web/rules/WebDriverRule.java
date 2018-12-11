package sabre.autotests.web.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static sabre.autotests.web.util.prop.WebDriverProperties.props;

public class WebDriverRule extends ExternalResource {

    private WebDriver webDriver;

    @Override
    protected void before() throws Throwable {
        String seleniumUrl;
        seleniumUrl = "http://localhost:4444/wd/hub";
        /*if (props().useLocalSelenium()) {
            seleniumUrl = "http://localhost:4444/wd/hub";
        } else {
            final String seleniumQuotaName ="selenium";
            final String seleniumQuotaPassword ="selenium";
            final String seleniumGridHost ="sg.yandex-team.ru";

            seleniumUrl = String.format("http://%s:%s@%s:4444/wd/hub",
                seleniumQuotaName, seleniumQuotaPassword, seleniumGridHost);

            String excluded = Stream.of("localhost|127.*|[::1]", seleniumGridHost).collect(joining("|"));
            System.setProperty("proxySet", "true");
            System.setProperty("http.nonProxyHosts", excluded);
        }*/
        System.out.println("Requesting a browser from " + seleniumUrl);
        DesiredCapabilities capabilities =
                new DesiredCapabilities(props().driverType(), props().version(), props().platform());
        webDriver = new RemoteWebDriver(new URL(seleniumUrl), capabilities);
        System.out.println("Ok!");
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @Override
    protected void after() {
        webDriver.quit();
        //webDriver.close();
    }

    public WebDriver getDriver() {
        return webDriver;
    }
}
