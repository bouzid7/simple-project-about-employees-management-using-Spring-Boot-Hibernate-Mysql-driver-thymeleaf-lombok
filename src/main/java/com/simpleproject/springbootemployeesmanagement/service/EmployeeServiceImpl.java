package com.simpleproject.springbootemployeesmanagement.service;


import com.simpleproject.springbootemployeesmanagement.core.Employee;
import com.simpleproject.springbootemployeesmanagement.exception.ResourceNotFoundException;
import com.simpleproject.springbootemployeesmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
	/*	Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
		return employee.get();
	      }
        else {
		throw new ResourceNotFoundException("Employee", "Id", id);
	         }*/
        return employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        // check whether a employee exist in a DB or not
        employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }

}
