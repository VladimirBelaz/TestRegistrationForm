// BaseTest.java
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger;

    @BeforeEach
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());

        BrowserType browserType = BrowserType.valueOf(System.getProperty("browser", "CHROME").toUpperCase());
        driver = WebDriverFactory.createDriver(browserType);

        logger.info("Драйвер инициализирован");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Драйвер успешно закрыт ✅");
            } catch (Exception e) {
                logger.error("Ошибка при закрытии драйвера: {} ❌", e.getMessage());
            }
            driver = null;
        }
        logger.info("Завершение теста");
    }
}