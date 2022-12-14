package com.library.step_definitions;

import com.library.utilities.DB_Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US1StepDefs {
    String expResult = "";
    String actResult = "";
    List<String> allColumnNamesAsList;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Utils.createConnection();
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
String query1 = "select count(id) as 'number of users' from users";
DB_Utils.runQuery(query1);
actResult = DB_Utils.getFirstRowFirstColumn();

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query2 = "select count(distinct id) as 'number of users' from users";
        DB_Utils.runQuery(query2);
        expResult = DB_Utils.getCellValue(1, 1);
        Assert.assertEquals(expResult, actResult);
    }
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
String query3 = "select * from users";
DB_Utils.runQuery(query3);
        allColumnNamesAsList = DB_Utils.getAllColumnNamesAsList();
        System.out.println("allColumnNamesAsList = " + allColumnNamesAsList);

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(io.cucumber.datatable.DataTable expList) {
for (String each : allColumnNamesAsList){
    Assert.assertTrue(expList.toString().contains(each));
}

    }
}
