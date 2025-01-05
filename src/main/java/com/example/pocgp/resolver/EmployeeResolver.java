package com.example.pocgp.resolver;



import com.example.pocgp.entity.Employee;
import com.example.pocgp.repo.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResolver {

    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryMapping
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Employee createEmployee(String name, Long departmentId) {
        Employee employee = new Employee();
        employee.setName(name);
        // set department (You can fetch it based on departmentId)
        // Example: employee.setDepartment(departmentRepository.findById(departmentId).orElse(null));
        return employeeRepository.save(employee);
    }

    @MutationMapping
    public Employee updateEmployee(Long id, String name, Long departmentId) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(name);
        // Update department here
        return employeeRepository.save(employee);
    }

    @MutationMapping
    public boolean deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return true;
    }
}
