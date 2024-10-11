package org.example.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {

        return employeeRepository.findAllByOrderByNameAsc();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByNameContainingOrAddressContainingOrEmailContainingOrPhoneContaining(keyword, keyword, keyword, keyword);
    }

}
