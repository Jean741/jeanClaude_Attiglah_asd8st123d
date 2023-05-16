package com.anywr.school.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class PaginatedStudentsResponse {
    private List<StudentDto> studentList;
    private Long numberOfStudents;
    private int numberOfPages;
}
