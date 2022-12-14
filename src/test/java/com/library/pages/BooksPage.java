package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BooksPage extends BasePage{


    @FindBy(id = "book_categories")
    public WebElement bookCategories;

    @FindBy(xpath = "")
    public WebElement allBooksNum;

    @FindBy(xpath = "")
    public WebElement usersNum;
}
