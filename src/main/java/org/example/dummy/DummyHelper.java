package org.example.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.dummy.pojos.CreateEmployeeResponse;
import org.example.dummy.pojos.DummyRequest;
import org.example.dummy.pojos.DummyResponse;
import org.example.dummy.pojos.GetEmployeeResponse;

public class DummyHelper {
    public static String baseUrl = "http://dummy.restapiexample.com/";
    String pathParam;
    String finalUrl;

    public GetEmployeeResponse getEmployee(int id) throws JsonProcessingException {
        pathParam = "api/v1/employee/";
        finalUrl = baseUrl+pathParam+id;
        System.out.println("hitting url: "+ finalUrl);

        Response response = RestAssured.get(finalUrl);
        int statusCode = response.getStatusCode();
        System.out.println("status code is : "+ statusCode);
        ObjectMapper objectMapper = new ObjectMapper();
        GetEmployeeResponse getEmployeeResponse = objectMapper.readValue(response.prettyPrint(), GetEmployeeResponse.class);
        return getEmployeeResponse;

    }
    public CreateEmployeeResponse createEmployee(String name, String sal, String age) throws JsonProcessingException {
        pathParam = "api/v1/create";
        finalUrl = baseUrl+pathParam;

        DummyRequest dummyRequest = new DummyRequest();
        dummyRequest.setAge(age);
        dummyRequest.setName(name);
        dummyRequest.setSalary(sal);
        System.out.println("hitting url: "+ finalUrl);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dummyRequest)
                .post(finalUrl);
        int statusCode = response.getStatusCode();
        System.out.println("status code is : "+ statusCode);

        ObjectMapper objectMapper = new ObjectMapper();
        CreateEmployeeResponse createEmployeeResponse = objectMapper.readValue(response.prettyPrint(), CreateEmployeeResponse.class);
        return createEmployeeResponse;

    }

    public DummyResponse deleteEmployee(int id) throws JsonProcessingException {
        pathParam = "public/api/v1/delete/";
        finalUrl = baseUrl+pathParam+id;
        System.out.println("hitting url: "+ finalUrl);

       // Response response = RestAssured.get(finalUrl);  404 not found
        Response response = RestAssured.delete(finalUrl); //successfully deleted
        int statusCode = response.getStatusCode();
  
        System.out.println("status code is : "+ statusCode);
        ObjectMapper objectMapper = new ObjectMapper();
        DummyResponse dummyResponse = objectMapper.readValue(response.prettyPrint(), DummyResponse.class);
        return dummyResponse;

    }

}
