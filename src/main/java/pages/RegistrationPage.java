package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class RegistrationPage extends AbsBasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver, "/form.html");
    }

    @FindBy(id = "username")
    private WebElement usernameInput;

    // Замена By.id("email")
    @FindBy(id = "email")
    private WebElement emailInput;

    // Замена By.id("password")
    @FindBy(id = "password")
    private WebElement passwordInput;

    // Замена By.id("confirm_password")
    @FindBy(id = "confirm_password")
    private WebElement confirmPasswordInput;

    // Замена By.id("birthdate")
    @FindBy(id = "birthdate")
    private WebElement birthdateInput;

    // Замена By.id("language_level")
    @FindBy(id = "language_level")
    private WebElement languageLevelSelect;

    // Замена By.cssSelector("input[type='submit']")
    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;

    // Замена By.id("output")
    @FindBy(id = "output")
    private WebElement outputBlock;

    public void fillUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void fillConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void setBirthdate(String date) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", birthdateInput, date);
    }

    public void selectLanguageLevel(String level) {
        Select select = new Select(languageLevelSelect);
        select.selectByValue(level);
    }

    public void submit() {
        submitButton.click();
    }

    public String getOutputText() {
        return outputBlock.getText();
    }

    public String getOutputLine(int index) {
        String[] lines = getOutputText().split("\n");
        if (index >= 0 && index < lines.length) {
            return lines[index].trim();
        }
        return "";
    }

    // Проверить совпадения паролей
    public boolean passwordsMatch() {
        String pwd = passwordInput.getAttribute("value");
        String confirm = confirmPasswordInput.getAttribute("value");
        return pwd.equals(confirm);
    }
}