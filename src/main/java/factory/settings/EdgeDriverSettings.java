package factory.settings;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class EdgeDriverSettings implements ISettings{

    public AbstractDriverOptions settings(String... args){

        EdgeOptions options = new EdgeOptions();
        options.addArguments(args);

        return options;
    }

}
