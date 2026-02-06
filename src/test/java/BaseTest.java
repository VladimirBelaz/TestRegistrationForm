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
        // Инициализация логгера для наследника
        logger = LogManager.getLogger(this.getClass());

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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