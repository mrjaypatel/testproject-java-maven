package Utils;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private static Driver driver = null;
    private ChromeDriver crmDriver = null;


    private Driver(){

    }

    public static Driver getInstance(){
        if(driver == null) {
            driver = new Driver();
        }
        return driver;
    }

    public ChromeDriver openBrowser()  {
        if(crmDriver == null){
            try {
                crmDriver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
                        .withRemoteAddress(new URL(Global.TPUrl))
                        .withToken(Global.TPToken)
                        .build(ChromeDriver.class);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return crmDriver;
    }
}
