package io.github.jeyhung.students.application;

import io.github.jeyhung.students.application.dto.StudentCreateDto;
import io.github.jeyhung.students.application.dto.StudentDto;
import io.github.jeyhung.students.application.dto.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();

    StudentDto getById(long id);

    void create(StudentCreateDto studentCreateDto);

    void update(StudentUpdateDto studentUpdateDto);

    void delete(long id);
}
