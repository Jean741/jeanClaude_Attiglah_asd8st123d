package com.anywr.school.service;

import com.anywr.school.dto.PaginatedStudentsResponse;

public interface IStudentService {
    PaginatedStudentsResponse getStudents(int pageNumber, int pageSize, String schoolClassName, String teacherFullName);
}
