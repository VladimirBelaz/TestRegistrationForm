package assertions;

import org.junit.jupiter.api.Assertions;
import pages.RegistrationPage;

public class RegistrationPageAssertions {

    private final RegistrationPage page;

    private RegistrationPageAssertions(RegistrationPage page) {
        this.page = page;
    }

    public static RegistrationPageAssertions assertThat(RegistrationPage page) {
        return new RegistrationPageAssertions(page);
    }

    public RegistrationPageAssertions hasUsername(String expectedUsername) {
        String actual = page.getOutputLine(0);
        Assertions.assertTrue(actual.contains(expectedUsername),
                "Ожидалось, что username содержит: " + expectedUsername + ", но получено: " + actual);
        return this;
    }

    public RegistrationPageAssertions hasEmail(String expectedEmail) {
        String actual = page.getOutputLine(1);
        Assertions.assertTrue(actual.contains(expectedEmail),
                "Ожидалось, что email содержит: " + expectedEmail + ", но получено: " + actual);
        return this;
    }

    public RegistrationPageAssertions hasBirthdate(String expectedBirthdate) {
        String actual = page.getOutputLine(2);
        Assertions.assertTrue(actual.contains(expectedBirthdate),
                "Ожидалось, что birthdate содержит: " + expectedBirthdate + ", но получено: " + actual);
        return this;
    }

    public RegistrationPageAssertions hasLanguageLevel(String expectedLevel) {
        String actual = page.getOutputLine(3);
        Assertions.assertTrue(actual.contains(expectedLevel),
                "Ожидалось, что language level содержит: " + expectedLevel + ", но получено: " + actual);
        return this;
    }

    public RegistrationPageAssertions hasMatchingPasswords() {
        Assertions.assertTrue(page.passwordsMatch(),
                "Пароли не совпадают: введённые значения различаются");
        return this;
    }
}