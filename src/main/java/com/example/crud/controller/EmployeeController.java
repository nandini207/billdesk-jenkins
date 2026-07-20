package com.example.crud.controller;


import com.example.crud.entity.Employee;
import com.example.crud.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService service;


    public EmployeeController(EmployeeService service){
        this.service=service;
    }


    @PostMapping
    public Employee addEmployee(
            @ModelAttribute Employee employee){

        return service.save(employee);
    }


    @GetMapping
    public List<Employee> getEmployees(){

        return service.getAll();
    }


    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee){

        return service.update(id, employee);
    }


    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id){

        service.delete(id);

        return "Deleted Successfully";
    }
}