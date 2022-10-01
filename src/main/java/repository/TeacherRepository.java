package repository;

import dto.Course;
import dto.Student;
import dto.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class TeacherRepository {
    private final SessionFactory factory;
    private Session session;
    private Transaction transaction;
    public TeacherRepository(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public String createTeacher(Teacher teacher){
        String message;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(teacher);
            transaction.commit();
            message = "Teacher created successfully";
        }catch (Exception exception){
            if(transaction != null){
                transaction.rollback();
            }
            message = exception.getClass() + ": " + exception.getMessage();
        }finally {
            session.close();
        }

        return message;
    }

    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            teachers = (ArrayList<Teacher>) session.createQuery("FROM teachers", Teacher.class).list();
        }catch (Exception exception){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }finally {
            session.close();
        }

        return teachers;
    }
}
