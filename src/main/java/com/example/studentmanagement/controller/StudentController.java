
package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private List<Student> students = new ArrayList<>();


    public StudentController() {
        students.add(new Student(1, "Abebe", "Software Engineering"));
        students.add(new Student(2, "Hana", "Computer Science"));
        students.add(new Student(3, "Meron", "Information Systems"));
    }


    // 1.CREATE:
    // Add a new student
    @PostMapping("/students")
    public ResponseEntity<?> addStudent(
            @RequestBody Student student) {

        for (Student existingStudent : students) {
            if (existingStudent.getId() == student.getId()) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("A student with this ID already exists.");
            }
        }

        students.add(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(student);
    }

    //2.READ
    // Get all
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }


    // Get student by ID
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.notFound().build();
    }


    // Get students by name
    @GetMapping("/students/name/{name}")
    public List<Student> getStudentsByName(
            @PathVariable String name) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }

        return result;
    }


    // Get students by department
    @GetMapping("/students/department/{department}")
    public List<Student> getStudentsByDepartment(
            @PathVariable String department) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                result.add(student);
            }
        }

        return result;
    }



    // 3. UPDATE:
    // Update student by ID
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {
            if (student.getId() == id) {

                student.setName(updatedStudent.getName());
                student.setDepartment(updatedStudent.getDepartment());

                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // 4. DELETE
    // Delete student by ID
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(
            @PathVariable int id) {

        for (Student student : students) {
            if (student.getId() == id) {

                students.remove(student);

                return ResponseEntity.ok(
                        "Student deleted successfully."
                );
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Student not found.");
    }

    // Delete students by name
    @DeleteMapping("/students/name/{name}")
    public ResponseEntity<String> deleteStudentsByName(
            @PathVariable String name) {

        boolean removed = students.removeIf(
                student -> student.getName().equalsIgnoreCase(name)
        );

        if (removed) {
            return ResponseEntity.ok(
                    "Matching student(s) deleted successfully."
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Student not found.");
    }
}



