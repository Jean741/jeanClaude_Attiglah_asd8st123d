package com.anywr.school.dto;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class PaginatedStudentsResponse {
    private List<StudentDto> studentList;
    private Long numberOfStudents;
    private int numberOfPages;
}
