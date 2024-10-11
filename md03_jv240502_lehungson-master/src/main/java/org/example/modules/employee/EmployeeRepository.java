package org.example.modules.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByOrderByNameAsc();

    List<Employee> findByNameContainingOrAddressContainingOrEmailContainingOrPhoneContaining(String name, String address, String email, String phone);

}

