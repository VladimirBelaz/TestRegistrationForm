import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class RegistrationFormTest extends BaseTest {
    private static final String BASE_URL = System.getProperty("BASE_URL");
    private static final String USERNAME = System.getProperty("USERNAME");
    private static final String PASSWORD = System.getProperty("PASSWORD");

    @Test
    public void testAlert() {
        logger.info("Начало теста авторизации");
        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("email")).sendKeys("testers@mail.ru");
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("confirm_password")).sendKeys(PASSWORD);
        driver.findElement(By.id("birthdate")).sendKeys("23.02.2000");
        Select languageSelect = new Select(driver.findElement(By.id("language_level")));
        languageSelect.selectByValue(LanguageLevel.INTERMEDIATE.getValue());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        logger.info("Отправка данных ✅");
        String[] lines = driver.findElement(By.id("output")).getText().split("\n");

        Assertions.assertTrue(lines[0].trim().contains(USERNAME));
        Assertions.assertTrue(lines[1].trim().contains("testers@mail.ru"));
        Assertions.assertTrue(lines[2].trim().contains("2000-02-23"));
        Assertions.assertTrue(lines[3].trim().contains("intermediate"));
        logger.info("Авторизация успешна ✅");
    }
}