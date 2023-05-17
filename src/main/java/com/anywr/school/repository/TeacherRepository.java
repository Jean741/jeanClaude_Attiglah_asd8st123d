package com.anywr.school.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anywr.school.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    @Query("from Teacher T where (concat(T.firstName,' ',T.lastName) = :fullName or concat(T.lastName,' ',T.firstName)=:fullName)")
    List<Teacher> findByTeacherFullName(@Param("fullName")String fullName);
}
