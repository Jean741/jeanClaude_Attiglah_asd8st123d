package com.anywr.school.dto;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@JsonPropertyOrder(value = {
        "studentList",
        "numberOfStudents",
        "numberOfPages",
})
public class PaginatedStudentsResponse {
    @JsonProperty("StudentList")
    private List<StudentDto> studentList;
    private Long numberOfStudents;
    private int numberOfPages;
}
