package io.github.jeyhung.students.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private long id;
    private String identity;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}
