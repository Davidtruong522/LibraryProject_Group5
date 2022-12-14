package com.library.step_definitions;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.DB_Utils;

import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setupScenario(){
        System.out.println("this is coming from BEFORE");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
    }


    @After
    public void teardownScenario(Scenario scenario){
        //if (scenario.isFailed()) {
        byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenShot, "image/png", scenario.getName());
        // }


        Driver.closeDriver();
    }

    @Before("@db")
    public void setupDB(){
        DB_Utils.createConnection();
        System.out.println("Connecting to DB . . . . ");



    }

    @After("@db")
    public void destroyDB(){
        DB_Utils.destroy();
        System.out.println("Connection to DB Destroyed . . . . ");
    }




}