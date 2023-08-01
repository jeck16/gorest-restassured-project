package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void verifyTotalRecord() {
        response.body("size", equalTo(10));
    }

    // 2. Verify the if the name of id =  4104736 is equal to "Kamalesh Naik"
    @Test
    public void verifyNameOfId() {
        response.body("findAll{it.id == 4104736}",  hasItem(hasEntry("name","Kamalesh Naik")));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void verifySingleName() {
        response.body("name[1]", equalTo("Chandrakin Shukla I"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void verifyMultipleNames() {
        response.body("name", hasItems("Hiranmay Prajapat", "Chandrakin Shukla I", "Chandrakin Shukla I"));
    }

    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void verifyEmailOfUserId() {
        response.body("findAll{it.id == 4104739}.email",hasItem (equalTo("iyengar_giri_miss@gerhold.example")));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void verifyStatusIsActive() {
        response.body("findAll{it.name =='Bhima Chaturvedi'}.status", hasItem(equalTo("active")));
    }

    //7. Verify the Gender = male of user name is “Niro Prajapat
    @Test
    public void verifyGender() {
        response.body("findAll{it.name == 'Shanti Bhattacharya'}.gender", hasItem(equalTo("female")));
    }
}
