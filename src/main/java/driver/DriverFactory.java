package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private WebDriver driver;
    public static WebDriver getChromeDriver (){
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation ;

        if (OS.isFamilyMac()){
            chromeDriverLocation = "/src/main/resources/drivers/chromedriver.exe";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = "\\src\\main\\resources\\drivers\\chromedriver.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }

        String chromeAbsoluteLocation = currentProjectLocation.concat(chromeDriverLocation);
        System.setProperty("webdriver.chrome.driver", chromeAbsoluteLocation);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriver driver =  new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));

        return driver;
    }

    public static WebDriver getFirefoxDriver (){
        String currentProjectLocation = System.getProperty("user.dir");
        String firefoxDriverLocation ;

        if (OS.isFamilyMac()){
            firefoxDriverLocation = "/src/main/resources/drivers/geckodriver.exe";
        } else if (OS.isFamilyWindows()) {
            firefoxDriverLocation = "\\src\\main\\resources\\drivers\\geckodriver.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }

        String firefoxAbsoluteLocation = currentProjectLocation.concat(firefoxDriverLocation);
        System.setProperty("webdriver.gecko.driver", firefoxAbsoluteLocation);

        WebDriver driver =  new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));

        return driver;
    }

    public WebDriver getDriver (String browserName){
        boolean isRemoteRunning = System.getProperty("hub") != null;
        if (isRemoteRunning) return getRemoteWebDriver(browserName, System.getProperty("hub"));
        else return getLocalDriver(browserName);
    }

    private WebDriver getLocalDriver(String browserName){
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation ;
        if (OS.isFamilyMac()){
            chromeDriverLocation = "/src/main/resources/drivers/chromedriver.exe";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = "\\src\\main\\resources\\drivers\\chromedriver.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }
        String chromeAbsoluteLocation = currentProjectLocation.concat(chromeDriverLocation);
        String firefoxDriverLocation ;
        if (OS.isFamilyMac()){
            firefoxDriverLocation = "/src/main/resources/drivers/geckodriver.exe";
        } else if (OS.isFamilyWindows()) {
            firefoxDriverLocation = "\\src\\main\\resources\\drivers\\geckodriver.exe";
        } else {
            throw new RuntimeException("[ERR] Couldn't detect the OS");
        }
        String firefoxAbsoluteLocation = currentProjectLocation.concat(firefoxDriverLocation);

        if (driver == null) {
            switch (browserName) {
                case ("chrome"):
                    System.setProperty("webdriver.chrome.driver", chromeAbsoluteLocation);
                    driver = new ChromeDriver();
                    break;
                case ("firefox"):
                    System.setProperty("webdriver.gecko.driver", firefoxAbsoluteLocation);
                    driver = new FirefoxDriver();
                    break;
                case ("safari"):
                    driver = new SafariDriver();
                    break;
                default:
                    throw new IllegalArgumentException(browserName + " is not supported!");
            }
        }

        return driver;
    }

    private WebDriver getRemoteWebDriver(String browserName, String hub){
        if (driver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.ANY);
            switch (browserName) {
                case ("chrome"):
                    desiredCapabilities.setBrowserName("chrome");
                    break;
                case ("firefox"):
                    desiredCapabilities.setBrowserName("firefox");
                    break;
                case ("safari"):
                    desiredCapabilities.setBrowserName("safari");
                    break;
                default:
                    throw new IllegalArgumentException(browserName + " is not supported!");
            }

            try {
                URL hubUrl = new URL(hub.concat("/wd/hub"));
                driver = new RemoteWebDriver(hubUrl, desiredCapabilities);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return driver;
    }

    public void closeBrowserSession(){
        if (driver != null){
            driver.quit();
        }
    }
}
