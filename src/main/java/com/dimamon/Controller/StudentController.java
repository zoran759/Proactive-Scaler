package com.dimamon.Controller;

import com.dimamon.Entity.Student;
import com.dimamon.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by dimamon on 11/15/16.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    //Берем id из url и отправлеям в метод
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return this.studentService.getStudentById(id);
    }

    //Удаление по id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Student removeStudentById(@PathVariable("id") int id){
        return this.studentService.removeStudentById(id);
    }

    //consumes = MediaType.APPLICATION_JSON_VALUE
    //Обновление информации, передается JSON
    @RequestMapping(method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Student student) {
        this.studentService.updateStudent(student);
    }

    //Добавить студента
    @RequestMapping(method = RequestMethod.POST)
    public void insertStudent(@RequestBody Student student) {
        this.studentService.insertStudent(student);
    }


}
