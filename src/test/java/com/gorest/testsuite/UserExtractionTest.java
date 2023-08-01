package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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


    //1. Extract the All Ids
    @Test
    public void extractAllIds() {
        List<Integer> userIDs = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all Ids are : " + userIDs);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void extractAllNames() {
        List<String> userNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all Names are : " + userNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5 object
    @Test
    public void NameOfObject() {
        String userName = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of object is : " + userName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void GenderOfStatusActive() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void GenderFemale() {
        List<String> name = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void EmailsOfStatusInactive() {
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = inactive : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void IdsOfGenderMale() {
        List<Object> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender = male : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void AllStatus() {
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void extractEmail() {
        List<String> email = response.extract().path("findAll{it.name == 'Akshita Sharma'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Sushil Johar : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 2272492
    @Test
    public void GenderOfId() {
        List<String> gender = response.extract().path("findAll{it.id == 4104739}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of id = 2272492 : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
