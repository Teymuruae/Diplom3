package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class UserMethods {

    @Step
    public ValidatableResponse deleteUser(String accessToken, int statusCode) {
        Spec.install(statusCode);
        return RestAssured
                .given()
                .header("Authorization", accessToken)
                .when()
                .delete(EndPoints.getGetUserInfo()).then();
    }

    @Step
    public ValidatableResponse createUser(String email, String password, String name, int statusCode) {
        CreateUserRequest userRequest = new CreateUserRequest(email, password, name);
        Spec.install(statusCode);
        return RestAssured
                .given()
                .body(userRequest)
                .when()
                .post(EndPoints.getCreateUser()).then();

    }
}