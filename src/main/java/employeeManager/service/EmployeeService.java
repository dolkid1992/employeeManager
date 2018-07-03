package employeeManager.service;

import employeeManager.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Employee findById(Long id);

    void save(Employee employee);

    void delete(Long id);

    Page<Employee> findAllByNameContains(String name, Pageable pageable);
}
