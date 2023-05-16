package com.anywr.school.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@Table(indexes = @Index(name = "class_name_index",columnList = "name"))
public class SchoolClass {
	@Id
	UUID id;
	@Column(unique = true)
	String name;
	@Default
	@OneToMany(mappedBy = "studentClass")
	List<Student> students = new ArrayList<>();
	@OneToOne
	Teacher teacher;
}
