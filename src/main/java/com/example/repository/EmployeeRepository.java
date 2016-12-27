package com.example.repository;

import com.example.vo.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kimyongyeon on 2016-12-27.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
