// BaseTest.java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Logger logger;

    private static final String BASE_URL = System.getProperty("BASE_URL");
    @BeforeEach
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());

        BrowserType browserType = BrowserType.valueOf(System.getProperty("browser", "CHROME").toUpperCase());
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Создаем драйвер с помощью фабрики
        driver = WebDriverFactory.createDriver(browserType, headless);
        driver.get(BASE_URL);
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