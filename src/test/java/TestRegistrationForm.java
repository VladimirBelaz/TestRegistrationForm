import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class TestRegistrationForm {
    private static final String BASE_URL = System.getenv("BASE_URL");
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private WebDriver driver;
    private final Logger logger = LogManager.getLogger(TestRegistrationForm.class);

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Открытие страницы: {}", BASE_URL);
        driver.get(BASE_URL);
        logger.info("Страница успешно открыта, заголовок: {} ✅", driver.getTitle());
        logger.info("Текущий URL: {}", driver.getCurrentUrl());
    }

    @Test
    public void testAlert() {
        logger.info("Начало теста авторизации");
        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("email")).sendKeys("testers@mail.ru");
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("confirm_password")).sendKeys(PASSWORD);
        driver.findElement(By.id("birthdate")).sendKeys("23.02.2000");
        Select languageSelect = new Select(driver.findElement(By.id("language_level")));
        languageSelect.selectByValue("intermediate");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        logger.info("Отправка данных ✅");
        String[] lines = driver.findElement(By.id("output")).getText().split("\n");

        Assertions.assertTrue(lines[0].trim().contains(USERNAME));
        Assertions.assertTrue(lines[1].trim().contains("testers@mail.ru"));
        Assertions.assertTrue(lines[2].trim().contains("2000-02-23"));
        Assertions.assertTrue(lines[3].trim().contains("intermediate"));
        logger.info("Авторизация успешна ✅");

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