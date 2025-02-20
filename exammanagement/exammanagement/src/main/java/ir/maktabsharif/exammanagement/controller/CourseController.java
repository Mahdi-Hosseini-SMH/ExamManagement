package ir.maktabsharif.exammanagement.controller;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherRequestDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
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

    @PostMapping("/add-teacher/{identifier}/teachers/{nationalCode}")
    public ResponseEntity<String> addTeacherToCourse(@PathVariable String identifier, @PathVariable String nationalCode) {

        Course updatedCourse = courseService.addTeacherToCourse(identifier, nationalCode);
        return ResponseEntity.ok("استاد برای دوره تعریف شد");

    }

    @PutMapping("/update/teacher/{identifier}")
    public ResponseEntity<Course> updateTeacher(@PathVariable String identifier, @RequestBody Teacher teacher) {
        Course updatedCourse = courseService.updateTeacherInCourse(identifier, teacher);
        return ResponseEntity.ok(updatedCourse);
    }

    @PostMapping("/add-student/{identifier}/students/{nationalCode}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable String identifier, @PathVariable String nationalCode) {

        Course updatedCourse = courseService.addStudentToCourse(identifier, nationalCode);
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
            return ResponseEntity.ok("آپدیت با موفقیت انجام شد");
        } else {
            return ResponseEntity.status(404).body("ایدی یافت نشد");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID id) {
        courseService.delete(id);
        return ResponseEntity.ok("دوره حذف شد");
    }

    @DeleteMapping("delete/teacher/{identifier}")
    public ResponseEntity<String> removeTeacher(@PathVariable String identifier) {
        courseService.removeTeacherFromCourse(identifier);
        return ResponseEntity.ok("استاد از دوره حذف شد");
    }

    @DeleteMapping("/delete-student/{courseID}/students/{studentID}")
    public ResponseEntity<String> removeStudent(@PathVariable UUID courseID, @PathVariable UUID studentID) {
        courseService.removeStudentFromCourse(courseID, studentID);
        return ResponseEntity.ok("دانشجو از دوره حذف شد");
    }

    @GetMapping("/participants/{courseID}")
    public ResponseEntity<Course> getCourseWithParticipants(@PathVariable UUID courseID) {
        Course course = courseService.getCourseWithParticipants(courseID);
        return ResponseEntity.ok(course);
    }

}
