package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    @FindBy(id = "borrowed_books")
    public WebElement borrowBooksNum;

    @FindBy(xpath = "")
    public WebElement allBooksNum;

    @FindBy(xpath = "")
    public WebElement usersNum;
}
