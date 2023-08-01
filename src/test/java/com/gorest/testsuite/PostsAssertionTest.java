package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }


    //1.Verify the if the total record is 10
    @Test
    public void test001() {
        response.body("size()", equalTo(10));
    }

    //2.Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("find{it.id == 57251}.title", equalTo("Capio cornu arma baiulus vito."));
    }
    //3.Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("id", hasItem(57250));
    }

    //4.Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItem(57248));
    }


    @Test
    public void test005() {
        response.body("find{it.id == 57245}.body", equalTo("Abutor degenero aequus. Angulus sophismata theca. Casso antea amiculum. Claro titulus animi. Sto vigor demum. Tactus contra deleo. Angustus delibero harum. Socius abstergo tero. Defleo spoliatio compono. Autus blanditiis ut. Non ducimus ars. Bis depopulo quibusdam. Urbs antepono auxilium. Cunabula est ut. Triduana cupiditas una. Degenero cupiditate aliquid. Fuga usitas adsuesco."));
    }
}
