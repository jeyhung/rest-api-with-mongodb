package io.github.jeyhung.students.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {
    @Transient
    public static final String SEQUENCE_NAME = "students_sequence";

    @Id
    private long id;

    @Field(name = "identity_number")
    @Indexed(unique = true)
    private String identity;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    @Field(name = "phone_number")
    private String phone;

    @Field(name = "address")
    private String address;

}
