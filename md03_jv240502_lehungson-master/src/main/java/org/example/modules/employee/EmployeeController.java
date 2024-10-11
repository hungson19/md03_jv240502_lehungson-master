package org.example.modules.employee;

import org.example.modules.department.Department;
import org.example.modules.department.DepartmentService;
import org.example.modules.employee.dto.CreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

        @RequestMapping("")
        public String list(Model model) {
            List<Employee> employees = employeeService.findAll();
            model.addAttribute("employees", employees);
            return "employee/employee";
        }


        @RequestMapping("/create")
        public String create(Model model) {
            model.addAttribute("departmentList", departmentService.findAll());
            model.addAttribute("employee", new CreateDto());
            return "employee/create";
        }

        @PostMapping("/create")
        public String save(@ModelAttribute CreateDto createDto, @RequestParam("avatar") MultipartFile file) {
            try {
                if(file.isEmpty()) {
                    System.out.println("file chua duoc lay");
                }
                Department department = departmentService.findById(createDto.getDepartmentId());
                Employee newEmployee = new Employee();
                newEmployee.setDepartment(department);
                newEmployee.setName(createDto.getName());
                // Convert String to Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = dateFormat.parse(createDto.getDateOfBirth());
                newEmployee.setDateOfBirth(dateOfBirth);
                newEmployee.setSex(createDto.getSex());
                newEmployee.setAddress(createDto.getAddress());
                newEmployee.setEmail(createDto.getEmail());
                newEmployee.setPhone(createDto.getPhone());
                String fileName = file.getOriginalFilename();
                String path = "C:\\Users\\Admin\\Desktop\\JV240502_xxx\\JV240502_NguyenVanLong\\public\\";
                file.transferTo(new File(path + fileName));
                newEmployee.setAvatar("http://localhost:8080/public/" + fileName);
                employeeService.save(newEmployee);
                return "redirect:/employee";
            }catch (Exception e) {
                System.out.println("e" + e.getMessage());
                return "/employee/create";
            }
        }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            employee.setStatus(false);
            employeeService.save(employee);
        }
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            model.addAttribute("departmentList", departmentService.findAll());
            return "employee/edit";
        }
        return "redirect:/employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") int id,
                                 @ModelAttribute Employee updatedEmployee,
                                 @RequestParam("avatar") MultipartFile file) {
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee != null) {
            if (!file.isEmpty()) {
                try {
                    String filePath = "C:\\Users\\Admin\\Desktop\\JV240502_xxx\\JV240502_NguyenVanLong\\public\\" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    existingEmployee.setAvatar("http://localhost:8080/public/" + filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "employee/edit";
                }
            }
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setAddress(updatedEmployee.getAddress());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setPhone(updatedEmployee.getPhone());
            existingEmployee.setDateOfBirth(updatedEmployee.getDateOfBirth());
            existingEmployee.setSex(updatedEmployee.isSex());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());

            employeeService.save(existingEmployee);
            return "redirect:/employee";
        } else {

            return "error/employeeNotFound";
        }
    }
    @GetMapping("/search")
    public String searchEmployee(@RequestParam("keyword") String keyword, Model model) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeService.searchEmployees(keyword);
        } else {
            employees = employeeService.findAll();
        }
        model.addAttribute("employees", employees);
        return "employee/employee";
    }



}
