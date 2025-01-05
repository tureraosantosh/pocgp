package com.example.pocgp.resolver;


import com.example.pocgp.entity.Department;
import com.example.pocgp.repo.DepartmentRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class DepartmentResolver {

    @Autowired
    private DepartmentRepository departmentRepository;

    @QueryMapping
    public Department getDepartment(@Argument Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Department createDepartment(@Argument String name) {
        Department department = new Department();
        department.setName(name);
        return departmentRepository.save(department);
    }

    @MutationMapping
    public Department updateDepartment(@Argument Long id,@Argument String name) {
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setName(name);
        return departmentRepository.save(department);
    }

    @MutationMapping
    public boolean deleteDepartment(@Argument Long id) {
        departmentRepository.deleteById(id);
        return true;
    }
}
