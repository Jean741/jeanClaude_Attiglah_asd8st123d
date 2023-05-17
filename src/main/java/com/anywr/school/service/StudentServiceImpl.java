package com.anywr.school.service;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anywr.school.dto.PaginatedStudentsResponse;
import com.anywr.school.dto.StudentDto;
import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.entity.Teacher;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.StudentRepository;
import com.anywr.school.repository.TeacherRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService{
	
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    
    @Override
    public PaginatedStudentsResponse getStudents(
            int pageNumber,
            int pageSize,
            String studentClass,
            String teacherFullName) {
        Page<Student> studentsFilteredAndPaginated = getStudentsFilteredAndPaginated(
        		pageNumber,
        		pageSize,
        		studentClass.trim(),
        		teacherFullName.trim());
        return PaginatedStudentsResponse.builder()
                .studentList(studentsFilteredAndPaginated.getContent()
                		.stream()
                			.map(StudentDto::from).toList())
                .numberOfStudents(studentsFilteredAndPaginated.getTotalElements())
                .numberOfPages(studentsFilteredAndPaginated.getTotalPages())
                .build();
    }
    
    private Page<Student> getStudentsFilteredAndPaginated(
            int pageNumber,
            int pageSize,
            String studentClass,
            String teacherFullName) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        if (!StringUtils.isEmpty(studentClass)){
            SchoolClass schoolClass = schoolClassRepository.findByName(studentClass);
            if (Objects.nonNull(schoolClass)){
                return studentRepository.findByStudentClass(pageable,schoolClass);
            }
        }
        if (!StringUtils.isEmpty(teacherFullName)){
			var schoolClass = teacherRepository.findByTeacherFullName(teacherFullName)
                    .stream().findFirst()
                        .orElse(new Teacher()).getTeacherClass();
            if (Objects.nonNull(schoolClass)){
                return studentRepository.findByStudentClass(pageable,schoolClass);
            }
        }
        return studentRepository.findAll(pageable);
    }
}
