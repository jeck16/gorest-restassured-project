package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {
    static String name = "ankur" + TestUtils.getRandomValue();
    static String email = "ankur" + TestUtils.getRandomValue() + "@gmail.com";
    static String updatedEmail = "ankur01" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;

    static String token = "d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7";


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
    }

    @Test()
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .post("/public/v2/users");
        response.then().statusCode(201);

    }

    @Test
    public void verifyUserUpdateSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .patch("/public/v2/users/" + userId);
        response.then().statusCode(201);

    }

    @Test
    public void verifyUserDeleteSuccessfully() {

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .pathParam("id", userId)
                .when()
                .delete("/public/v2/users/{id}");
        response.then().statusCode(204);
    }
}
