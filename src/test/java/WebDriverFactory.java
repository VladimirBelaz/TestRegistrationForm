import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver createDriver(BrowserType browserType) {
        return createDriver(browserType, false);
    }

    // Основной метод с поддержкой headless
    public static WebDriver createDriver(BrowserType browserType, boolean headless) {
        switch (browserType) {
            case CHROME:
                return createChromeDriver(headless);
            case FIREFOX:
                return createFirefoxDriver(headless);
            case EDGE:
                return createEdgeDriver(headless);
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    // Для обратной совместимости - если вдруг нужно из строки
    public static WebDriver createDriver(String browserName, boolean headless) {
        try {
            BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());
            return createDriver(browserType, headless);
        } catch (IllegalArgumentException e) {
            System.out.println("Неизвестный браузер: " + browserName + ". Используем Chrome.");
            return createChromeDriver(headless);
        }
    }

    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        configureDriver(driver);
        return driver;
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
        }

        WebDriver driver = new FirefoxDriver(options);
        configureDriver(driver);
        return driver;
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless");
        }

        WebDriver driver = new EdgeDriver(options);
        configureDriver(driver);
        return driver;
    }

    // ВСЕ настройки в одном месте
    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
}
