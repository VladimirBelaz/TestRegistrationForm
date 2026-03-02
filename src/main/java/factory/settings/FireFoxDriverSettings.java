package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class FireFoxDriverSettings implements ISettings {

    public AbstractDriverOptions settings(String... args){

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(args);

        return options;
    }
}
