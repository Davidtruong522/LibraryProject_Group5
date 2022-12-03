package com.library.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    // a Singleton driver
    // creating a private constructor, so we are closing access to the object of this class. from outside any classes
    private Driver(){}

    // making our 'driver' instance private because we do not want it to be reachable from outside any class
    // we make it static because we dont want to create an object and for it to be used before anything else. not to mention we will use it in a static method
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();


    // creating a reusable method which will return the same driver instance when we call it
    public static WebDriver getDriverPool(){


        /*
        what do we need to run SG?

            - remote browser

            - add system env to ready browser type
                String browserType = "";
            if (System.getProperty("BROWSER") == null){
                browserType = ConfigurationReader.getProperty("browser");
            }else {
                browserType = System.getProperty("BROWSER");
            }
         */




        if (driverPool.get() == null){
            String browserType = "";
            if (System.getProperty("BROWSER") == null){
                browserType = ConfigurationReader.getProperty("browser");
            }else {
                browserType = System.getProperty("BROWSER");
            }



            switch (browserType.toLowerCase()){
                case "remote-chrome":
                    try {
                        // assign your grid server address
                        String gridAddress = "3.93.188.212"; //TODO note that if the AWS instance is terminated this ip will not work since it doesnt not exist anymore
                        URL url = new URL("http://"+ gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                        //driverPool.set(new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"),desiredCapabilities));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                case "remote-firefox":
                    try {
                        // assign your grid server address
                        String gridAddress = "3.93.188.212"; //TODO note that if the AWS instance is terminated this ip will not work since it doesnt not exist anymore
                        URL url = new URL("http://"+ gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("firefox");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                        //driverPool.set(new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"),desiredCapabilities));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                default:
                    System.out.println("Driver does not exist, Options are: " + "\nchrome" + "\nfirefox" + "\nedge" + "\nsafari");
            }
        }

        // same driver instance will return everytime we call driver
        return driverPool.get();
    }


    public static void closeDriver(){
        if (driverPool.get() !=null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}