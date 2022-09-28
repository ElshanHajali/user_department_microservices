package com.example.department.controller;

import com.example.department.dto.Department;
import com.example.department.service.IDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@Slf4j
public class DepartmentController {
    private final IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        log.info("New department: " + department);
        return departmentService.save(department);
    }

    @GetMapping("/id/{id}")
    public Department getDepartmentById(@PathVariable long id) {
        Department tmpDep = departmentService.fetchById(id);
        log.info("Department: "+tmpDep);
        return tmpDep;
    }
}
