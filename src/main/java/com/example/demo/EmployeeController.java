package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empid) {
        Optional<Employee> optional = service.getEmployeeById(empid);
        return optional.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee emp) {
        return service.addEmployee(emp);
    }

    @PutMapping("/{empid}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String empid, @RequestBody Employee updated) {
        Employee result = service.updateEmployee(empid, updated);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{empid}")
    public void deleteEmployee(@PathVariable String empid) {
        service.deleteEmployee(empid);
    }
}
