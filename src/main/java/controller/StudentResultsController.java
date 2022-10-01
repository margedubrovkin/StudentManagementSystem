package controller;
import dto.Course;
import dto.StudentResults;
import dto.Teacher;
import org.hibernate.SessionFactory;
import repository.CourseRepository;
import repository.StudentResultsRepository;
import repository.TeacherRepository;

import java.util.Scanner;

public class StudentResultsController { private final StudentResultsRepository studentResultsRepository;
    private final CourseRepository courseRepository;

    private Scanner scanner;

    public StudentResultsController(Scanner scanner, SessionFactory sessionFactory) {
        this.scanner = scanner;
        this.studentResultsRepository = new StudentResultsRepository(sessionFactory);
        this.courseRepository = new CourseRepository(sessionFactory);
    }

    public void createStudentResults(){
        StudentResults studentResults = new StudentResults();
        System.out.println("Enter student result :");
        studentResults.setStudentresults(Long.parseLong(scanner.nextLine()));



        studentResultsRepository.createStudentResults(studentResults);

        System.out.println(studentResults.getStudentresults() + " Created successfully");

    }
}




