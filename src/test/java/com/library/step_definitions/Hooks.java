package com.library.step_definitions;
import com.library.utilities.DB_Utils;

import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    //@Before
    public void setupScenario(){
        System.out.println("Setting up browser using cucumber @Before each scenario");
    }


    @After
    public void teardownScenario(Scenario scenario){
        //if (scenario.isFailed()) {
        byte[] screenShot = ((TakesScreenshot) Driver.getDriverPool()).getScreenshotAs(OutputType.BYTES);
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