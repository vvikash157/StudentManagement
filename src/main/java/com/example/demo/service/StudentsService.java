package com.example.demo.service;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Map;

public interface StudentsService {
    public List<StudentDto> getAllStudents();

    public StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id,AddStudentRequestDto addStudentDto);

    StudentDto updateStudentPartial(Long id,Map<String, Object> updates);
}
