package com.library.step_definitions;

import com.library.pages.BasePage;
import com.library.pages.BooksPage;
import com.library.pages.DashboardPage;
import com.library.pages.LogIn;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DB_Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2StepDefs {
    String allBooksNum = "";
    LogIn logIn = new LogIn();
    BasePage basePage = new BasePage();
    DashboardPage dashboard = new DashboardPage();


    @Given("user login as a librarian")
    public void user_login_as_a_librarian() {
logIn.loginMethod();
 BrowserUtils.waitForClickability(dashboard.borrowBooksNum, 4);

    }

    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
        allBooksNum = String.valueOf(dashboard.borrowBooksNum.getText());
        System.out.println();
        System.out.println("allBooksNum = = = = = = = = = = = = = = = = = = " + allBooksNum);
        System.out.println();
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query = "select count(*) from book_borrow where is_returned = 0";
        DB_Utils.createConnection();
        DB_Utils.runQuery(query);
        String dbBorrowedBooks = DB_Utils.getCellValue(1, 1);
       // Assert.assertEquals(allBooksNum, dbBorrowedBooks);
        Assert.assertEquals(dbBorrowedBooks, allBooksNum);
    }

}

