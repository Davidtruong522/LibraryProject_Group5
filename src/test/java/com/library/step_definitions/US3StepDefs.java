package com.library.step_definitions;

import com.library.pages.BasePage;
import com.library.pages.BooksPage;
import com.library.pages.LogIn;
import com.library.utilities.DB_Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class US3StepDefs {

    ArrayList<String> dbList = new ArrayList<>();
    ArrayList<String> allCategorList = new ArrayList<>();
LogIn logIn = new LogIn();
BasePage basePage = new BasePage();
BooksPage booksPage = new BooksPage();


    @Given("I login as a librarian")
    public void i_login_as_a_librarian() {
        logIn.loginMethod();

    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String string) {
        basePage.navigateModule(string);
    }
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        Select select = new Select(booksPage.bookCategories);
        List<WebElement> categoriesList = select.getOptions();
        for (WebElement each : categoriesList){
            allCategorList.add(each.getText());
        }
        allCategorList.remove(0);
    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        String query = "select name from book_categories";
        DB_Utils.createConnection();
        DB_Utils.runQuery(query);
        dbList = (ArrayList<String>) DB_Utils.getColumnDataAsList(1);
        System.out.println("= = == == == = == == = == = = == = == = ="+dbList);
    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        for( String each1 : allCategorList){
            int count = 0;
            for(String each2 : dbList){
                if(each1.equals(each2)){
                    count++;
                }
            }
            Assert.assertTrue(count == 1);
        }

    }




}
