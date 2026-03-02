package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeDriverSettings implements ISettings {

    public AbstractDriverOptions settings(String... args){

        ChromeOptions options = new ChromeOptions();
        options.addArguments(args);
        return options;
    }

}
