package uz.pdp.appspringBootlesson.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringBootlesson.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {

    List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Abror", "Ergashev", new Date(), "+998999999999"),
            new Student(2, "Iskandar", "Mamashayev", new Date(), "+998988888888"),
            new Student(3, "Eldor", "Shomurodov", new Date(), "+998977777777"),
            new Student(4, "Bahrom", "Qodirov", new Date(), "+998966666666")));

    // Barcha studentlar ro'yxati
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return students;
    }

    // Bitta student ro'yxati
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return new Student();
    }

    // Student qo'shish
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student) {
        student.setId(students.get(students.size() - 1).getId() + 1);
        students.add(student);
        return "Successfully added !";
    }

    // Studentni o'chirish
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id) {
        boolean deleted = false;
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                deleted = true;
                break;
            }
        }
        return deleted ? "Student deleted !" : "Student not found !";
    }

    // Studentni tahrirlash
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        boolean edited = false;
        for (Student currentStudent : students) {
            if (currentStudent.getId() == id) {
                currentStudent.setFirstName(student.getFirstName());
                currentStudent.setLastName(student.getLastName());
                currentStudent.setBirthDate(student.getBirthDate());
                currentStudent.setPhoneNumber(student.getPhoneNumber());
                edited = true;
                break;
            }
        }
        return edited ? "Student successfully edited !" : "Student not found !";
    }
}
