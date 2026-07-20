package com.example.crud.service;

import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {


    private final EmployeeRepository repository;


    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;
    }


    // CREATE
    public Employee save(Employee employee){
        return repository.save(employee);
    }


    // READ
    public List<Employee> getAll(){
        return repository.findAll();
    }


    // UPDATE
    public Employee update(Long id, Employee employee){

        Employee existing =
                repository.findById(id).orElseThrow();

        existing.setName(employee.getName());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }


    // DELETE
    public void delete(Long id){
        repository.deleteById(id);
    }
}
