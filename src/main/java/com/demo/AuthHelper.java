package com.demo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import static com.demo.utils.Constants.*;
public class AuthHelper {
    public static String[][] authenticateAndGetTokens() {
        // Формируем JSON тело запроса для аутентификации
        JSONObject requestParams = new JSONObject();
        requestParams.put("login", LOGIN);
        requestParams.put("password", PASSWORD);
        requestParams.put("rememberMe", false);
        requestParams.put("deviceName", DEVICE_NAME);
        // Выполняем запрос аутентификации
        Response authResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString())
                .when()
                .post("/auth/api/auth");
        if (authResponse.getStatusCode() != 200) {
            throw new RuntimeException("Authentication failed with status code: " + authResponse.getStatusCode());
        }
        String accessToken = authResponse.jsonPath().getString("accessToken");
        String sessionId = authResponse.getSessionId();
        return new String[][] { {"accessToken", accessToken}, {"sessionId", sessionId} };
    }
}