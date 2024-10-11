package org.example.modules.department.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateDto {
    private String name;
    private String description;

    @Override
    public String toString() {
        return "CreateDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
