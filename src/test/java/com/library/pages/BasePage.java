package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Dashboard']")
    public WebElement dashboard;

    @FindBy(xpath = "//span[.='Users']")
    public WebElement users;

    @FindBy(xpath = "//span[.='Books']")
    public WebElement books;

    public void navigateModule(String moduleName){
Driver.getDriver().findElement(By.xpath("//span[.='" + moduleName + "']")).click();
    }

}
