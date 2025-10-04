package com.example.demo.service.implementations;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Students;
import com.example.demo.repository.StudentsRepository;
import com.example.demo.service.StudentsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsServiceImplementation implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Students> studentsList=studentsRepository.findAll();

        return studentsList.stream().map(students ->modelMapper.map(students,StudentDto.class)).toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Students students=studentsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found by given id"));

        return modelMapper.map(students,StudentDto.class) ;
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentDto) {
        Students newStudents=modelMapper.map(addStudentDto,Students.class);
        Students students=studentsRepository.save(newStudents);
        return modelMapper.map(students,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id){

        if(!studentsRepository.existsById(id)){
            throw new IllegalArgumentException("Student not found by given id "+id);
        }

        studentsRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id,AddStudentRequestDto addStudentDto) {
        Students students=studentsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found by given id "+id));
        modelMapper.map(addStudentDto,students);
        students=studentsRepository.save(students);
        return modelMapper.map(students,StudentDto.class);
    }

    @Override
    public StudentDto updateStudentPartial(Long id,Map<String, Object> updates) {
        Students students=studentsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found by given id "+id));
        updates.forEach((field,value)->{
            switch(field){
                case "firstName":students.setFirstName((String)value);
                break;
                case "lastName":students.setLastName((String)value);
                break;
                case "email":students.setEmail((String)value);
                break;
                case "phoneNumber":students.setPhoneNumber((String)value);
                break;
                default:throw new IllegalArgumentException("given field is not supporting for update");
            }
        });

        Students updatedStudent=studentsRepository.save(students);
        return modelMapper.map(updatedStudent,StudentDto.class);
    }
}
