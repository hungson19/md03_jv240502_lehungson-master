package org.example.modules.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDto {
    private Integer departmentId;

    private String name;

    private String dateOfBirth;

    private Boolean sex;

    private String address;

    private String email;

    private String phone;

    @Override
    public String toString() {
        return "CreateDto{" +
                "departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
