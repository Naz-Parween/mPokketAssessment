package dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dummy.DummyHelper;
import org.example.dummy.pojos.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDummy {
    DummyHelper dummyHelper = new DummyHelper();

    String name = "NAz";
    String sal = "12000";
    String age = "32";

    int employeeId =10; // we can give input here

    @Test
    public void testCreateEmployee() throws JsonProcessingException {

        CreateEmployeeResponse employee = dummyHelper.createEmployee(name, sal, age);
        Assert.assertEquals(employee.getStatus(),"success", "Status is not as expected" );
        EmployeeCreateResponseData data = employee.getData();
        Assert.assertEquals(data.getName(),name, "name is wrong");
        Assert.assertEquals(data.getAge(),age, "age is wrong");
        Assert.assertEquals(data.getSalary(),sal, "sal is wrong");

    }

    @Test
    public void testGetEmployee() throws JsonProcessingException {

        GetEmployeeResponse getEmployeeResponse = dummyHelper.getEmployee(employeeId);
        Assert.assertEquals(getEmployeeResponse.getStatus(),"success", "Status is not as expected" );

        GetEmployeeResponseData data = getEmployeeResponse.getData();
        Assert.assertEquals(data.getId(), String.valueOf(employeeId), "This not the id requested");
        Assert.assertEquals(getEmployeeResponse.getStatus(), "success", "status is not same");

    }
    @Test
    public void testDeleteEmployee() throws JsonProcessingException {


        DummyResponse dummyResponse = dummyHelper.deleteEmployee(employeeId);
        Assert.assertEquals(dummyResponse.getStatus(),"success", "Status is not as expected" );

        dummyResponse.getMessage();
    }

    /**
    @Test
    public void testCreateEmployee() throws JsonProcessingException {

        DummyResponse employee = dummyHelper.createEmployee(name, sal, age);
        Integer employeeId = employee.getData().getId();

        DummyResponse getEmployeeResponse = dummyHelper.getEmployee(employeeId);
        Data data = getEmployeeResponse.getData();
        Assert.assertEquals(data.getEmployeeName(),name, "name is wrong");
        Assert.assertEquals(data.getEmployeeAge(),age, "age is wrong");
        Assert.assertEquals(data.getEmployeeSalary(),sal, "sal is wrong");

        DummyResponse dummyResponse = dummyHelper.deleteEmployee(employeeId);
        dummyResponse.getMessage();
    }
    **/
}
