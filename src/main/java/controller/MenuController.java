package controller;

import dto.Course;
import dto.Student;
import dto.StudentResults;
import dto.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MenuController {
    private final SessionFactory sessionFactory;
    private final CourseController courseController;
    private final TeacherController teacherController;
    private final StudentController studentController;
    private final StudentResultsController studentResultsController;
    Scanner scanner = new Scanner(System.in);
    public MenuController(){
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(StudentResults.class)
                .buildSessionFactory();

        this.courseController = new CourseController(scanner, sessionFactory);
        this.teacherController = new TeacherController(scanner, sessionFactory);
        this.studentController = new StudentController(scanner, sessionFactory);
        this.studentResultsController = new StudentResultsController(scanner, sessionFactory);
    }
    public void start(){
        System.out.println("Welcome to student management system: " +
                "choose an option below:" +
                "1.create course" +
                "2. Create teacher" +
                "3. create student" +
                "4. Enter Exam Result" +
                "5. Register student to course" +
                "6. view Courses" +
                "7.View Teachers" +
                "8. View Students" +
                "9. Update Course" +
                "10. Update Teacher+" +
                "11. Update Student" +
                "12. Delete Course" +
                "13.Delete teacher" +
                "14. Delete Student" +
                "15. Exit/end program");

        String userChoice = scanner.nextLine();
        this.handleUserChoice(userChoice);
        this.start();
    }

    private void handleUserChoice(String userChoice) {
        switch (userChoice){
            case "1":
                System.out.println("==== Creating Course =====");
                this.courseController.createCourse();
                break;
            case "2":
                System.out.println("==== Creating Teacher =====");
                this.teacherController.createTeacher();
                break;
            case "3":
                System.out.println("==== Creating student =====");
                this.studentController.createStudent();
                break;
            case "4":
                System.out.println("==== Enter Exam Score =====");
                this.studentResultsController.createStudentResults();
                break;

            case "5":
                System.out.println("==== Register student to course =====");
                //;
                break;

            case "6":
                System.out.println("==== All Courses =====");
                courseController.displayAllCourses();
                break;

            case "7":
                System.out.println("==== All Teachers =====");
                teacherController.displayAllTeachers();
                break;

            case "8":
                System.out.println("==== All Students =====");
                studentController.displayAllStudents();
                break;

            case "9":
                System.out.println("==== Update Course =====");
                courseController.findCourseById();
                break;

            case "10":
                System.out.println("==== Update Teacher =====");
                //;
                break;

            case "12":
                System.out.println("==== Delete Course =====");
                courseController.deleteCourseById();
                break;

            case "15":
                System.out.println("==== Good Bye =====");
                System.exit(0);
                break;
            default:
                System.out.println("Please choose one of available options");
        }
    }
}
