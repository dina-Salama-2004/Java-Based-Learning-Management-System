package com.example.LMS.controllers;

import com.example.LMS.DTOs.CourseDTO;
import com.example.LMS.models.CourseModel;
import com.example.LMS.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private static final String UPLOAD_DIRECTORY = "C:/uploads/";

    @PostMapping("/createCourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseModel course) {
        if (course.getListLessons() == null) {
            course.setListLessons(new ArrayList<>());
        }
        if (course.getMediaFiles() == null) {
            course.setMediaFiles(new ArrayList<>());
        }

        courseService.createCourse(course);  // Create the course using the service
        return ResponseEntity.ok("Course created successfully");
    }

    @PostMapping("/{courseId}/upload-media")
    public ResponseEntity<String> uploadMedia(@PathVariable String courseId, @RequestParam("file") MultipartFile file) {
        try {

            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                if (!uploadDir.mkdirs()) {
                    return ResponseEntity.status(500).body("Failed to create upload directory.");
                }
            }

            // Save the file to the Path
            String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // Add the file path
            courseService.addMediaFile(courseId, filePath);

            return ResponseEntity.ok("Media file uploaded successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("File upload failed: " + e.getMessage());
        }
    }


    @GetMapping("/displayCourses")
    public ResponseEntity<List<CourseDTO>> displayCourses() {
        List<CourseDTO> courses = courseService.displayCourses();
        return ResponseEntity.ok(courses);
    }
}
