package com.anywr.school.service;

import com.anywr.school.dto.PaginatedStudentsResponse;
import com.anywr.school.dto.StudentDto;
import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import com.anywr.school.repository.SchoolClassRepository;
import com.anywr.school.repository.StudentRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService{
	
    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;
    
    @Override
    public PaginatedStudentsResponse getStudents(int pageNumber,
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
    
    private Page<Student> getStudentsFilteredAndPaginated(int pageNumber,
											    		int pageSize,
											    		String studentClass,
											    		String teacherFullName) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        if (!StringUtils.isEmpty(studentClass)){
            SchoolClass schoolClass = schoolClassRepository.findByName(studentClass);
            if (!Objects.isNull(schoolClass)){
                return studentRepository.findByStudentClass(pageable,schoolClass);
            }
        }
        //TODO Compleate getting students filtered by fullname feature
        if (!StringUtils.isEmpty(teacherFullName)){
            SchoolClass schoolClass = schoolClassRepository.findByTeacher(teacherFullName);
            if (!Objects.isNull(schoolClass)){
                return studentRepository.findByStudentClass(pageable,schoolClass);
            }
        }
        return studentRepository.findAll(pageable);
    }
}
