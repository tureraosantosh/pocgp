package com.example.pocgp.resolver;



import com.example.pocgp.entity.Employee;
import com.example.pocgp.repo.EmployeeRepository;
import com.example.pocgp.service.EmployeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeResolver {



    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;
    @QueryMapping
    public Employee getEmployee(@Argument Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee;
    }
    @QueryMapping
    public List<Employee> employees(@Argument String createdBy,
                                    @Argument String createdOn,
                                    @Argument String departmentName,
                                    @Argument String addressCity,
                                    @Argument String sortField,
                                    @Argument String sortDirection) {
        return employeeService.getFilteredAndSortedEmployees(
                createdBy, createdOn, departmentName, addressCity,
                sortField != null ? sortField : "id",
                sortDirection != null ? sortDirection : "ASC");
    }
    @MutationMapping
    public Employee createEmployee(@Argument String name,@Argument Long departmentId) {
        Employee employee = new Employee();
        employee.setName(name);
        // set department (You can fetch it based on departmentId)
        // Example: employee.setDepartment(departmentRepository.findById(departmentId).orElse(null));
        return employeeRepository.save(employee);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id,@Argument String name, @Argument Long departmentId) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(name);
        // Update department here
        return employeeRepository.save(employee);
    }

    @MutationMapping
    public boolean deleteEmployee(@Argument Long id) {
        employeeRepository.deleteById(id);
        return true;
    }
}
