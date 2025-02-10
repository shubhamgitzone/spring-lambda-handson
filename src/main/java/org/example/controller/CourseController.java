package org.example.controller;

import org.example.dto.Course;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Course>> fetchAllCourse(){
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Course> fetchById(@PathVariable int id){
        Optional<Course> course = courseService.getCourseById(id);
        if(course!=null){
            return new ResponseEntity<>(course.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course){
        boolean updated = courseService.updateCourse(id, course);
        if(updated)
            return new ResponseEntity<>(course,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCourses(@PathVariable int id){
        boolean deleted = courseService.deleteCourse(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
