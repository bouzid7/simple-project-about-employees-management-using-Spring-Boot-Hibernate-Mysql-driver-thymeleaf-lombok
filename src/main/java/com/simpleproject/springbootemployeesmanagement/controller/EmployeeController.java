package com.simpleproject.springbootemployeesmanagement.controller;


import com.simpleproject.springbootemployeesmanagement.core.Employee;
import com.simpleproject.springbootemployeesmanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    //constructor
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // get all employees
    @GetMapping("/employees management")
    public ModelAndView redirectExample() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("employees");
        mav.addObject("employees", employeeService.getAllEmployees());
        return mav;
    }

    //add new employee
    @GetMapping("/employees management/addnew")
    public ModelAndView redirectEmployeeForm(){
        Employee employee=new Employee();
        ModelAndView mav1 = new ModelAndView();
        mav1.setViewName("addemployee");
        mav1.addObject("employee",employee);
        return  mav1;
    }

    //save employee after adding it
    @PostMapping("/employees management/save")
    public RedirectView returnToEmployees(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return new RedirectView("/employees management");
    }

    //update employee
    @GetMapping("/employees management/update/{id}")
    public  ModelAndView updateEmployee(@PathVariable("id") long id){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("updateemployee");
        mv.addObject("employee",employeeService.getEmployeeById(id));
        return mv;
    }

    //save the update employee
    @PostMapping("/employees management/{id}")
    public RedirectView updateEmployees(@PathVariable("id") long id,@ModelAttribute Employee employee){
        employeeService.updateEmployee(employee, id);
        return new RedirectView("/employees management");
    }

    // delete the employee
    @GetMapping("/employees management/delete/{id}")
    public RedirectView deleteEmployees(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);
        return new RedirectView("/employees management");
    }


}
