package controller;

import dto.Course;
import org.hibernate.SessionFactory;
import repository.CourseRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseController {

    private final Scanner scanner;
    private final CourseRepository courseRepository;
    public CourseController(Scanner scanner, SessionFactory sessionFactory) {
        this.scanner = scanner;
        this.courseRepository = new CourseRepository(sessionFactory);
    }

    public void createCourse(){

        Course course = new Course();
        System.out.println("Enter course name :");
        course.setTitle(scanner.nextLine());

        courseRepository.createCourse(course);

        System.out.println(course.getTitle() + " Created successfully");

    }

    public void displayAllCourses() {
        ArrayList<Course> courses = courseRepository.getAllCourses();

        System.out.println("ID | course Title");

        for (Course course: courses){
            System.out.println(course.getId() + " | " + course.getTitle());
        }
    }

    public void findCourseById(){

        Course course = new Course();
        System.out.println("Enter course id :");
        int id = scanner.nextInt();
        System.out.println("Enter the course name to replace: ");
        String replace = scanner.next();
        course.setId((long) id);
        course.setTitle(replace);

        courseRepository.findCourseById(course.getId());

        System.out.println(course.getTitle() + " successfully updated");

    }

    public void deleteCourseById(){

        Course course = new Course();
        System.out.println("Enter course id you want to delete :");
        course.setId(scanner.nextLong());

        courseRepository.deleteCourseById(course.getId());

        System.out.println(course.getId() + " deleted successfully");

    }
}

