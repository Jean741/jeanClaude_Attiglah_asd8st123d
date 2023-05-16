package com.anywr.school.entity;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
