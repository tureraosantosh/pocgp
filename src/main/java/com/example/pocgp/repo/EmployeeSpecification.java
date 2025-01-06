package com.example.pocgp.repo;


import com.example.pocgp.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeSpecification {

    public static Specification<Employee> createdBy(String createdBy) {
        return (root, query, criteriaBuilder) ->
                createdBy == null ? null : criteriaBuilder.equal(root.get("createdBy"), createdBy);
    }

    public static Specification<Employee> createdOn(String createdOn) {
        return (root, query, criteriaBuilder) -> {
            if (createdOn == null) return null;
            LocalDate date = LocalDate.parse(createdOn, DateTimeFormatter.ISO_DATE);
            return criteriaBuilder.equal(root.get("createdOn").as(LocalDate.class), date);
        };
    }

    public static Specification<Employee> departmentName(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            if (departmentName == null) return null;
            return criteriaBuilder.equal(root.join("department").get("name"), departmentName);
        };
    }

    public static Specification<Employee> addressCity(String addressCity) {
        return (root, query, criteriaBuilder) -> {
            if (addressCity == null) return null;
            return criteriaBuilder.equal(root.join("address").get("city"), addressCity);
        };
    }
}
