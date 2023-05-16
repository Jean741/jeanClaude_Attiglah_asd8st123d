package com.anywr.school;

import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.SchoolMember;
import com.anywr.school.entity.Student;
import com.anywr.school.entity.Teacher;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.SchoolMemberRepository;
import com.anywr.school.repository.StudentRepository;
import com.anywr.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class JeanClaudeAttiglahAsd8st123dApplication implements CommandLineRunner {
	
	private final SchoolClassRepository schoolClassRepository;
	private final SchoolMemberRepository schoolMemberRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;


	public static void main(String[] args) {
		SpringApplication.run(JeanClaudeAttiglahAsd8st123dApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(".................App Started.................");
		initDataBase();
		List<Student> students = studentRepository.findAll();
		log.info("Number of Students {}", students.size());
	}

	private void initDataBase() {
		SchoolClass firstClass = schoolClassRepository
				.save(SchoolClass.builder().id(UUID.randomUUID()).name("First class").build());

		SchoolClass secondClass = schoolClassRepository
				.save(SchoolClass.builder().id(UUID.randomUUID()).name("Second class").build());

		SchoolMember studentMic = studentRepository.save(new Student(UUID.randomUUID(), "God-Son", "Mick", firstClass));
		Student studentJon = studentRepository.save(new Student(UUID.randomUUID(), "Mickael", "Jon", firstClass));
		Student studentAnderson = schoolMemberRepository
				.save(new Student(UUID.randomUUID(), "Sarah", "Anderson", secondClass));
		log.info(studentMic.getLastName());
		log.info(studentJon.getLastName());
		log.info(studentAnderson.getLastName());

		Teacher teacherAdams = teacherRepository.save(new Teacher(UUID.randomUUID(), "Abigail", "Adams", secondClass));
		SchoolMember teacherHall = schoolMemberRepository
				.save(new Teacher(UUID.randomUUID(), "Emma", "Hall", firstClass));
		log.info(teacherAdams.getLastName() + teacherHall.getFirstName());
	}


}
