package org.example.modules.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.modules.employee.Employee;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Dept_id")
    private int id;

    @Column(name = "Dept_name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "Dept_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Dept_status", columnDefinition = "BIT DEFAULT 1")
    private boolean status = true;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public String getStatusText() {
        return status ? "Active" : "Inactive";
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
