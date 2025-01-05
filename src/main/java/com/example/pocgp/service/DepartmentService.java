package com.example.pocgp.service;



import com.example.pocgp.entity.Department;
import com.example.pocgp.repo.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Create a new department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get a department by ID
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // Update an existing department
    public Department updateDepartment(Long id, Department updatedDepartment) {
        if (departmentRepository.existsById(id)) {
            updatedDepartment.setId(id);  // Make sure to keep the existing ID
            return departmentRepository.save(updatedDepartment);
        }
        return null; // Return null or throw an exception if not found
    }

    // Delete a department
    public boolean deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
