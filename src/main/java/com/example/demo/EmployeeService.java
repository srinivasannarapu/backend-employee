package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(String empid) {
        return repository.findById(empid);
    }

    public Employee addEmployee(Employee emp) {
        return repository.save(emp);
    }

    public Employee updateEmployee(String empid, Employee updated) {
        Optional<Employee> optional = repository.findById(empid);
        if (optional.isPresent()) {
            Employee emp = optional.get();
            emp.setFirstname(updated.getFirstname());
            emp.setLastname(updated.getLastname());
            emp.setDesignation(updated.getDesignation());
            emp.setAddress(updated.getAddress());
            return repository.save(emp);
        } else {
            return null;
        }
    }

    public void deleteEmployee(String empid) {
        repository.deleteById(empid);
    }
}
