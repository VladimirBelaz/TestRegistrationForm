package pages;

import commons.AbsCommon;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public abstract class AbsBasePage extends AbsCommon {

    private final String BASE_URL = System.getProperty("BASE_URL");
    private final String path;

    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
    }

    public void open() {
        driver.get(BASE_URL + path);
    }
}
