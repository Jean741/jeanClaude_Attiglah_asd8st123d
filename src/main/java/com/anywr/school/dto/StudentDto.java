package com.anywr.school.dto;

import com.anywr.school.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@DiscriminatorValue("std")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(value = Include.NON_EMPTY)
public class StudentDto  {
	UUID id;
	String lastName;
	String firstName;
	String studentClass;
	public static StudentDto from (Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.lastName(student.getLastName())
				.firstName(student.getFirstName())
				.build();
	}
}
