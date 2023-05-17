package com.anywr.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.anywr.school.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.anywr.school.dto.PaginatedStudentsResponse;
import com.anywr.school.dto.StudentDto;
import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.entity.Teacher;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.StudentRepository;
import com.anywr.school.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private SchoolClassRepository schoolClassRepository;
    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    void getStudentsByValidStudentClassNameAndvalidTeacherFullNameTest(){
        int pageNumber = 1;
        int pageSize = 5;
        String studentClassA="Class A";
        SchoolClass classA =new SchoolClass(UUID.randomUUID(),"Class A",null,null) ;
        String teacherFullName = "Nicolas DuPon";

        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(UUID.randomUUID(), "Hope", "Malik", classA);
        Student student2 = new Student(UUID.randomUUID(), "Jane", "Smith", classA);
        studentList.add(student1);
        studentList.add(student2);

        Pageable pageable = mock(Pageable.class);
        Page<Student> studentPage = new PageImpl<>(studentList, pageable, studentList.size());
        Teacher teacherA = new Teacher(UUID.randomUUID(), "DuPon", "Nicolas", classA);

        when(studentRepository.findByStudentClass(any(Pageable.class), any(SchoolClass.class))).thenReturn(studentPage);
        when(teacherRepository.findByTeacherFullName(teacherFullName)).thenReturn(List.of(teacherA));
        when(schoolClassRepository.findByName(studentClassA)).thenReturn(null);

        PaginatedStudentsResponse response = studentService.getStudents(pageNumber, pageSize, studentClassA, teacherFullName);

        assertEquals(studentList.size(), response.getNumberOfStudents());
        assertEquals(1, response.getNumberOfPages());

        List<StudentDto> expectedStudentDtoList = new ArrayList<>();
        expectedStudentDtoList.add(StudentDto.from(student1));
        expectedStudentDtoList.add(StudentDto.from(student2));

        assertEquals(expectedStudentDtoList, response.getStudentList());

        verify(studentRepository, times(1)).findByStudentClass(any(Pageable.class), any(SchoolClass.class));
        verify(teacherRepository, times(1)).findByTeacherFullName(teacherFullName);
        verify(schoolClassRepository, times(1)).findByName(anyString());
        verify(studentRepository, never()).findAll(pageable);
    }

    @Test
    void getStudentsByInvalidValidStudentClassNameAndInvalidTeacherFullNameTest(){
    	int pageNumber = 1;
    	int pageSize = 5;
    	String studentClassA="Class C";
    	SchoolClass classA =new SchoolClass(UUID.randomUUID(),"Class A",null,null) ;
    	String teacherFullName = "";
    	
    	List<Student> studentList = new ArrayList<>();
    	Student student1 = new Student(UUID.randomUUID(), "Hope", "Malik", classA);
    	Student student2 = new Student(UUID.randomUUID(), "Jane", "Smith", classA);
    	Student student3 = new Student(UUID.randomUUID(), "Gomes", "Mils", classA);
    	studentList.add(student1);
    	studentList.add(student2);
    	studentList.add(student3);
    	
    	Pageable pageable = mock(Pageable.class);
    	Page<Student> studentPage = new PageImpl<>(studentList, pageable, studentList.size());
    	when(studentRepository.findAll(any(Pageable.class))).thenReturn(studentPage);
    	when(schoolClassRepository.findByName(studentClassA)).thenReturn(null);
    	
    	
    	PaginatedStudentsResponse response = studentService.getStudents(pageNumber, pageSize, studentClassA, teacherFullName);
    	
    	assertEquals(studentList.size(), response.getNumberOfStudents());
    	assertEquals(1, response.getNumberOfPages());
    	
    	List<StudentDto> expectedStudentDtoList = new ArrayList<>();
    	expectedStudentDtoList.add(StudentDto.from(student1));
    	expectedStudentDtoList.add(StudentDto.from(student2));
    	expectedStudentDtoList.add(StudentDto.from(student3));
    	
    	assertEquals(expectedStudentDtoList, response.getStudentList());
    	
    	verify(studentRepository, never()).findByStudentClass(any(Pageable.class), any(SchoolClass.class));
    	verify(teacherRepository, never()).findByTeacherFullName(teacherFullName);
    	verify(schoolClassRepository, times(1)).findByName(anyString());
    	verify(studentRepository, never()).findAll(pageable);
    }
    @Test
    void getStudentsByValidStudentClassNameAndInvalidTeacherFullNameTest(){
        int pageNumber = 1;
        int pageSize = 5;
        String studentClassA="Class A";
        SchoolClass classA =new SchoolClass(UUID.randomUUID(),"Class A",null,null) ;
        String teacherFullName = "";

        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(UUID.randomUUID(), "Hope", "Malik", classA);
        Student student2 = new Student(UUID.randomUUID(), "Jane", "Smith", classA);
        studentList.add(student1);
        studentList.add(student2);

        Pageable pageable = mock(Pageable.class);
        Page<Student> studentPage = new PageImpl<>(studentList, pageable, studentList.size());

        when(studentRepository.findByStudentClass(any(Pageable.class), any(SchoolClass.class))).thenReturn(studentPage);
        when(schoolClassRepository.findByName(studentClassA)).thenReturn(classA);

        PaginatedStudentsResponse response = studentService.getStudents(pageNumber, pageSize, studentClassA, teacherFullName);

        assertEquals(studentList.size(), response.getNumberOfStudents());
        assertEquals(1, response.getNumberOfPages());

        List<StudentDto> expectedStudentDtoList = new ArrayList<>();
        expectedStudentDtoList.add(StudentDto.from(student1));
        expectedStudentDtoList.add(StudentDto.from(student2));

        assertEquals(expectedStudentDtoList, response.getStudentList());

        verify(studentRepository, times(1)).findByStudentClass(any(Pageable.class), any(SchoolClass.class));
        verify(teacherRepository, never()).findByTeacherFullName(teacherFullName);
        verify(schoolClassRepository, times(1)).findByName(anyString());
        verify(studentRepository, never()).findAll(pageable);
    }

}
