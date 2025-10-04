package com.example.demo.controller;


import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api")
public class StudentController {

    private final StudentsService studentsService;
    public StudentController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentsService.getAllStudents());
//      new StudentDto("vikash","kumar","vvikash157@gmail.com","9266429225");
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentsService.getStudentById(id));
    }

    @PostMapping("/students/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid AddStudentRequestDto addStudentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentsService.createNewStudent(addStudentDto));
    }

    @DeleteMapping("students/delete/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentsService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/students/update/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentsService.updateStudent(id,addStudentDto));
    }

    @PatchMapping("/students/partial-updates/{id}")
    public ResponseEntity<StudentDto>studentPartialUpdate(@PathVariable Long id,@RequestBody Map<String,Object> updates){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentsService.updateStudentPartial(id,updates));
    }
}
