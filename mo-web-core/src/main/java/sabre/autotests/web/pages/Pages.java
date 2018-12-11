package sabre.autotests.web.pages;

import org.openqa.selenium.WebDriver;

public class Pages {
    private WebDriver webDriver;

    public Pages(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public BrandHomePage onMainPage() {
        return new BrandHomePage(webDriver);
    }
}
