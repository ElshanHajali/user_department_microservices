package com.example.department.service;

import com.example.department.dto.Department;
import com.example.department.repository.IDepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository depRepository;

    public DepartmentServiceImpl(IDepartmentRepository depRepository) {
        this.depRepository = depRepository;
    }

    @Override
    public List<Department> findAll() {
        return depRepository.findAll();
    }

    public Department fetchById(long id) {
        Optional<Department> department = depRepository.findById(id);
        return department.orElse(null);
    }

    @Override
    public ResponseEntity<Department> save(Department department) {
        if (depRepository.save(department).getDepartmentId() != 0) {
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(department, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Department> update(Department department, long id) {
        Department tmpDep = fetchById(id);

        if (tmpDep != null) {

            String departmentCode = department.getDepartmentCode();
            String departmentAddress = department.getDepartmentAddress();
            String departmentName = department.getDepartmentName();

            if (!departmentName.isEmpty() &&
                !"".equalsIgnoreCase(departmentName)) {
                tmpDep.setDepartmentName(department.getDepartmentName());
            }
            if (!departmentAddress.isEmpty() &&
                !"".equalsIgnoreCase(departmentAddress)){
                tmpDep.setDepartmentAddress(department.getDepartmentAddress());
            }
            if (!departmentCode.isEmpty() &
                !"".equalsIgnoreCase(departmentCode)){
                tmpDep.setDepartmentCode(department.getDepartmentCode());
            }

            return new ResponseEntity<>(tmpDep, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    public ResponseEntity<Department> delete(long id) {
        Department tmpDep = fetchById(id);

        if (tmpDep != null) {
            depRepository.deleteById(id);
            return new ResponseEntity<>(tmpDep, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
