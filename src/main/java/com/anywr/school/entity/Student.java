package com.anywr.school.entity;

import static lombok.AccessLevel.PRIVATE;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = PRIVATE)
@DiscriminatorValue("std")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student extends SchoolMember {

	public Student(UUID id, String lastName, String firstName, SchoolClass studentClass) {
		super(id, lastName, firstName);
		this.studentClass = studentClass;
	}

	@JoinColumn(nullable = true)
	@ManyToOne
	SchoolClass studentClass; 
	
	@Override
	public boolean equals(Object obj) {
	    if (! super.equals(obj)) {
	      return false;
	    }
	    Student student = (Student) obj;
	    return studentClass.equals(student.getStudentClass());
	  }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(studentClass);
		return result;
	}
}
