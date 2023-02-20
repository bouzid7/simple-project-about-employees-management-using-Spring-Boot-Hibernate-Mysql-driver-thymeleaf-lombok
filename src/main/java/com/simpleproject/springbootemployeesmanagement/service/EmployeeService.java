package com.simpleproject.springbootemployeesmanagement.service;

import com.simpleproject.springbootemployeesmanagement.core.Employee;
import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
