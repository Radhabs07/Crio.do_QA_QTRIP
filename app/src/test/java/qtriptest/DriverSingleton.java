package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {


    RemoteWebDriver driver;

    private static DriverSingleton instanceOfSingleton;

    private DriverSingleton() throws MalformedURLException{

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

        System.out.println("Create Driver");

        driver.manage().window().maximize();
    }

    public static DriverSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException{
       
        // null check
        if(instanceOfSingleton == null){

            instanceOfSingleton = new DriverSingleton();
        }
       
       
        return instanceOfSingleton;
        
    }

    public RemoteWebDriver getDriver(){

        return driver;
    }
}


