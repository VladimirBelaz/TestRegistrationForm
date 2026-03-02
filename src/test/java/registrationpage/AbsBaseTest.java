package registrationpage;

import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class AbsBaseTest {

    protected WebDriver driver = null;
    protected Logger logger;

    @BeforeEach
    public void init() {
        driver = new  WebDriverFactory().create();
        logger = LogManager.getLogger(this.getClass());
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
