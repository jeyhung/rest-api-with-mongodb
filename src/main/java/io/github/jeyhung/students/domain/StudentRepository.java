package io.github.jeyhung.students.domain;

import io.github.jeyhung.students.domain.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long> {
}
