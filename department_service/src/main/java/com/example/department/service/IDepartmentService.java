package com.example.department.service;

import com.example.department.dto.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();
    Department fetchById(long id);

    ResponseEntity<Department> save(Department department);
    ResponseEntity<Department> update(Department department, long id);
    ResponseEntity<Department> delete(long id);

}
