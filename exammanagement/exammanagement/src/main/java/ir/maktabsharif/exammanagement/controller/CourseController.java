package ir.maktabsharif.exammanagement.controller;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/definition")
    public ResponseEntity<Course> courseDefinition(@RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok(courseService.register(courseRequestDTO));
    }

    @PostMapping("/add-teacher/{identifier}/teachers/{teacherId}")
    public ResponseEntity<Course> addTeacherToCourse(@PathVariable String identifier, @PathVariable UUID teacherId) {

        Course updatedCourse = courseService.addTeacherToCourse(identifier, teacherId);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);

    }

    @PostMapping("/add-student/{courseId}/students/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable UUID courseId, @PathVariable UUID studentId) {

        Course updatedCourse = courseService.addStudentToCourse(courseId, studentId);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);

    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllTeachers();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/without-teacher")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesWithoutTeacher() {
        List<CourseResponseDTO> courses = courseService.getCoursesWithoutTeacher();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable UUID id, @RequestBody CourseRequestDTO courseRequestDTO) {
        boolean isUpdated = courseService.update(id, courseRequestDTO);
        if (isUpdated) {
            return  ResponseEntity.ok("آپدیت با موفقیت انجام شد");
        } else {
            return ResponseEntity.status(404).body("ایدی یافت نشد");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID id) {
        courseService.delete(id);
        return  ResponseEntity.ok("دوره حذف شد");
    }
}
