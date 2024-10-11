package org.example.modules.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private  DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Integer departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }
    public Department update(Department department) {
        System.out.println("DepartmentService.update" + department);
        return departmentRepository.save(department);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public String deleteDepartment(Integer departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null && !department.getEmployees().isEmpty()) {
            return "Cannot delete";
        }
        departmentRepository.deleteById(departmentId);
        return "Department deleted";
    }


}


