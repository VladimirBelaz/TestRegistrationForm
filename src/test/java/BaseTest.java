// BaseTest.java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger;

    @BeforeEach
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());

        BrowserType browserType = BrowserType.valueOf(System.getProperty("browser", "CHROME").toUpperCase());
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Создаем драйвер с помощью фабрики
        driver = WebDriverFactory.createDriver(browserType, headless);

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