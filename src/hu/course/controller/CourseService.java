package hu.course.controller;

import hu.course.model.domain.Course;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseService {

    private final List<Course> courses;

    public CourseService(List<Course> courses) {
        this.courses = courses;
    }

    public int getCourseCount() {
        return courses.size();
    }

    public int getTotalLessonCount() {
        return courses.stream()
                .mapToInt(i -> i.getWeeklyLessonNumber())
                .sum();
    }

    public int getTeacherWeeklyLesson(String teacher) {
        return courses.stream()
                .filter(i -> i.getTeacher().equals(teacher))
                .mapToInt(i -> i.getWeeklyLessonNumber())
                .sum();
    }

    public List<String> getMasterTeachers() {
        return courses.stream()
                .filter(i -> "osztalyfonoki".equals(i.getSubject()))
                .map(i -> i.getClassId() + " - " + i.getTeacher())
                .collect(Collectors.toList());
    }

    public String getCourseStatus(String classId, String subject) {
        return getCertainCourseCount(classId, subject) > 1 ? "Csoportbontásban tanulják" : "Osztályszinten tanulják";
    }

    private long getCertainCourseCount(String classId, String subject) {
        return courses.stream()
                .filter(i -> i.getClassId().equals(classId) && i.getSubject().equals(subject))
                .count();
    }

    public long getTeacherCount() {
        return courses.stream()
                .map(i -> i.getTeacher())
                .distinct()
                .count();
    }

}
