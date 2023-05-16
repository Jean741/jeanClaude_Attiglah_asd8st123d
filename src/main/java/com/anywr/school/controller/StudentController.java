package com.anywr.school.controller;

import com.anywr.school.dto.PaginatedStudentsResponse;
import com.anywr.school.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping("/")
    public PaginatedStudentsResponse getStudents(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "") String schoolClassName,
            @RequestParam(defaultValue = "") String teacherFullName) {
        return  studentService.getStudents(
        		pageNumber,
        		pageSize,
        		schoolClassName,
        		teacherFullName
        		);
    }
}
