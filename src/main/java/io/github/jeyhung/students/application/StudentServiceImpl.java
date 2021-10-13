package io.github.jeyhung.students.application;

import io.github.jeyhung.sequences.application.SequenceService;
import io.github.jeyhung.students.application.dto.StudentCreateDto;
import io.github.jeyhung.students.application.dto.StudentDto;
import io.github.jeyhung.students.application.dto.StudentUpdateDto;
import io.github.jeyhung.students.application.exception.StudentNotFoundException;
import io.github.jeyhung.students.domain.StudentRepository;
import io.github.jeyhung.students.domain.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SequenceService sequenceService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              ModelMapper modelMapper,
                              SequenceService sequenceService) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.sequenceService = sequenceService;
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getById(long id) {
        Student student = getByIdInternal(id);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void create(StudentCreateDto studentCreateDto) {
        Student student = modelMapper.map(studentCreateDto, Student.class);
        student.setId(sequenceService.next(Student.SEQUENCE_NAME));
        student.setIdentity(UUID.randomUUID().toString());
        studentRepository.save(student);
    }

    @Override
    public void update(StudentUpdateDto studentUpdateDto) {
        Student student = getByIdInternal(studentUpdateDto.getId());
        modelMapper.map(studentUpdateDto, student);
        studentRepository.save(student);
    }

    @Override
    public void delete(long id) {
        Student student = getByIdInternal(id);
        studentRepository.delete(student);
    }

    private Student getByIdInternal(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found!"));
    }
}
