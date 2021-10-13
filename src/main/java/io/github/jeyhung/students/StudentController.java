package io.github.jeyhung.students;

import io.github.jeyhung.students.application.StudentService;
import io.github.jeyhung.students.application.dto.StudentCreateDto;
import io.github.jeyhung.students.application.dto.StudentDto;
import io.github.jeyhung.students.application.dto.StudentUpdateDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "students")
@RestController
@RequestMapping(value = "/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    @ResponseBody
    public List<StudentDto> getAll() {
        return studentService.getAll();
    }

    @GetMapping(path = "/{id}/")
    @ResponseBody
    public StudentDto getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping(path = "/")
    public void create(@Validated @RequestBody StudentCreateDto studentCreateDto) {
        studentService.create(studentCreateDto);
    }

    @PutMapping(path = "/")
    public void update(@Validated @RequestBody StudentUpdateDto studentUpdateDto) {
        studentService.update(studentUpdateDto);
    }

    @DeleteMapping(path = "/{id}/")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
