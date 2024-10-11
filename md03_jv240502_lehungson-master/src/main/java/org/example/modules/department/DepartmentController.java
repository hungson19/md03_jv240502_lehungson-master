package org.example.modules.department;

import org.example.modules.department.dto.CreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("")
    public String list(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/department";
    }

    @RequestMapping("create")
    public String create(Model model) {
        model.addAttribute("department", new CreateDto());
        return "department/create";
    }

    @PostMapping("create")
    public String create(@ModelAttribute CreateDto createDto) {
        try {
            System.out.println("createDto" + createDto);
            Department newDepartment = new Department();
            newDepartment.setName(createDto.getName());
            newDepartment.setDescription(createDto.getDescription());
            departmentService.save(newDepartment);
            return "redirect:/department";
        } catch (Exception e) {
            System.out.println("e" + e.getMessage());
            return "department/create";
        }
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Department department, Model model) {
        try {
            departmentService.update(department);
            return "redirect:/department";
        }catch (Exception e) {
            System.out.println("phat sinh loi +>>>>" + e.getMessage());
            model.addAttribute("error", "Lỗi du lieu");
            return "department/department";
        }
    }
    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable("id") int id, @ModelAttribute Department department) {
        department.setId(id);
        departmentService.update(department);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        Department department = departmentService.findById(id);
        if (department != null) {
            department.setStatus(false);
            departmentService.update(department);
        }
        return "redirect:/department";
    }
    @RequestMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer departmentId, Model model) {
        String result = departmentService.deleteDepartment(departmentId);
        model.addAttribute("message", result);
        return "redirect:/department";
    }

}
