package org.example.modules.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.modules.department.Department;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Emp_id", length = 5)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Dept_id", nullable = false)
    private Department department;

    @Column(name = "Emp_name", nullable = false, length = 100)
    private String name;

    @Column(name = "Emp_BirthOfDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Emp_sex", nullable = false)
    private boolean sex;

    @Column(name = "Emp_address", columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(name = "Emp_email", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "Emp_phone", length = 11, nullable = false, unique = true)
    private String phone;

    @Column(name = "Emp_avatar", columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "Emp_status", columnDefinition = "BIT DEFAULT 1")
    private boolean status = true;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", department=" + department +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                '}';
    }
}
