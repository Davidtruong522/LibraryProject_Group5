package com.library.pages;

import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LogIn {
    public LogIn(){
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(id = "inputEmail")
        public WebElement login;

    @FindBy(id = "inputPassword")
    public WebElement password;

    @FindBy(tagName = "button")
    public WebElement loginBtn;


    public void loginMethod (){
        String username = ConfigurationReader.getProperty("username");
        String password1 = ConfigurationReader.getProperty("password");

        login.sendKeys(username);
        password.sendKeys(password1);
        loginBtn.click();
    }





    }

