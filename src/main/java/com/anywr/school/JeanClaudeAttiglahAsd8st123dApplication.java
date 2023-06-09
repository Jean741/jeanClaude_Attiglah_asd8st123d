package com.anywr.school;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		log.info(".................JeanClaudeAttiglahAsd8st123dApplication App Started.................");
		initDataBase();
	}

	/*
	 * Init dataBase  for fast Testing
	 */
	private void initDataBase() {
		SchoolClass firstClass = schoolClassRepository
				.save(SchoolClass.builder().id(UUID.randomUUID()).name("First class").build());

		SchoolClass secondClass = schoolClassRepository
				.save(SchoolClass.builder().id(UUID.randomUUID()).name("Second class").build());

		SchoolMember studentMic = studentRepository.save(new Student(UUID.randomUUID(), "God-Son", "Mick", firstClass));
		Student studentJon = studentRepository.save(new Student(UUID.randomUUID(), "Mickael", "Jon", firstClass));
		Student studentAnderson = schoolMemberRepository
				.save(new Student(UUID.randomUUID(), "Sarah", "Anderson", secondClass));
		log.debug(studentMic.getLastName());
		log.debug(studentJon.getLastName());
		log.debug(studentAnderson.getLastName());

		Teacher teacherAdams = teacherRepository.save(new Teacher(UUID.randomUUID(), "Abigail", "Adams", secondClass));
		SchoolMember teacherHall = schoolMemberRepository
				.save(new Teacher(UUID.randomUUID(), "Emma", "Hall", firstClass));
		log.debug(teacherAdams.getLastName() + teacherHall.getFirstName());
	}


}
