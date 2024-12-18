package com.example.LMS.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class LessonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String courseId;
    private String title;

    @ElementCollection
    @CollectionTable(name = "lesson_topics", joinColumns = @JoinColumn(name = "lesson_id"))
    @Column(name = "topic")
    private List<String> topics;

    private String description;
    private String teacherName;
    private LocalDateTime startDate;
    private int durationMinutes;
    private LocalDateTime endDate;
    private String OTP;

    @ManyToOne
    @JoinColumn(name = "courseId", insertable = false, updatable = false)  // Reference to the courseId in CourseModel
    private CourseModel courseModel;

    // Constructor
    public LessonModel(String title, List<String> topics, String description, String teacherName,
                  LocalDateTime startDate, int durationMinutes, LocalDateTime endDate, String OTP) {
        this.title = title;
        this.topics = topics;
        this.description = description;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.durationMinutes = durationMinutes;
        this.endDate = endDate;
        this.OTP = OTP;
    }

    public LessonModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
    }
}
