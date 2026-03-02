package registrationpage;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import assertions.RegistrationPageAssertions;

public class RegistrationFormTest extends AbsBaseTest {
    protected static final String USERNAME = System.getProperty("USERNAME");
    protected static final String PASSWORD = System.getProperty("PASSWORD");
    private static final String EMAIL = "testers@mail.ru";
    private static final String BIRTHDATE = "2000-02-23";
    private static final String LANGUAGE_LEVEL = "intermediate";

    @Test
    public void testRegistrationForm() {
        logger.info("Начало теста регистрации");

        RegistrationPage page = new RegistrationPage(driver);
        page.open();

        page.fillUsername(USERNAME);
        page.fillEmail(EMAIL);
        page.fillPassword(PASSWORD);
        page.fillConfirmPassword(PASSWORD);
        page.setBirthdate(BIRTHDATE);
        page.selectLanguageLevel(LANGUAGE_LEVEL);
        page.submit();

        logger.info("Форма отправлена ✅");

        RegistrationPageAssertions.assertThat(page)
                .hasMatchingPasswords() //проверка, что пароли совпадают
                .hasUsername(USERNAME)
                .hasEmail(EMAIL)
                .hasBirthdate(BIRTHDATE)
                .hasLanguageLevel(LANGUAGE_LEVEL);

        logger.info("Все проверки пройдены успешно ✅");
    }
}