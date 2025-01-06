package com.example.pocgp.service;

import com.example.pocgp.entity.Employee;
import com.example.pocgp.repo.EmployeeRepository;
import com.example.pocgp.repo.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        if (employeeRepository.existsById(id)) {
            updatedEmployee.setId(id);  // Make sure to keep the existing ID
            return employeeRepository.save(updatedEmployee);
        }
        return null; // Return null or throw an exception if not found
    }

    public List<Employee> getFilteredAndSortedEmployees(String createdBy, String createdOn,
                                                        String departmentName, String addressCity,
                                                        String sortField, String sortDirection) {
        Specification<Employee> spec = Specification.where(EmployeeSpecification.createdBy(createdBy))
                .and(EmployeeSpecification.createdOn(createdOn))
                .and(EmployeeSpecification.departmentName(departmentName))
                .and(EmployeeSpecification.addressCity(addressCity));

        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        return employeeRepository.findAll(spec, sort);

}
    // Delete an employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
