package com.anywr.school.entity;

import static lombok.AccessLevel.PRIVATE;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = PRIVATE)
@DiscriminatorValue("tch")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends SchoolMember {
	
	public Teacher(UUID id, String lastName, String firstName,SchoolClass teacherClass) {
		super(id, lastName, firstName);
		this.teacherClass=teacherClass;
	}
	@JoinColumn(nullable = true)
	@OneToOne
	SchoolClass teacherClass;

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		Teacher student = (Teacher) obj;
		return teacherClass.equals(student.getTeacherClass());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(teacherClass);
		return result;
	}
}
