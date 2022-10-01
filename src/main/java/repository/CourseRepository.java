package repository;

import dto.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;

public class CourseRepository {
    private final SessionFactory factory;
    private Session session;
    private Transaction transaction;
    public CourseRepository(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public String createCourse(Course course){
        String message;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
            message = "Course created successfully";
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


    public Course findCourseById(Long courseId) {
        Course course = null;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            course = session.find(Course.class, courseId);
            //Query query=session.createQuery("update courses set title='"+ course.getTitle()+"' where id='"+courseId+"' ");
            Query query=session.createQuery("update courses set title=:n where id=:i");
            query.setParameter("n", course.getTitle());
            query.setParameter("i", courseId);

            int result = query.executeUpdate();
            System.out.println(result);
            transaction.commit();
        }catch (Exception exception){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }finally {
            session.close();

        }

        return course;
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            courses = (ArrayList<Course>) session.createQuery("FROM courses", Course.class).list();
        }catch (Exception exception){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }finally {
            session.close();
        }

        return courses;
    }


    public Course deleteCourseById(Long courseId){
        Course course = null;
        String message;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            course = session.find(Course.class, courseId);
            Query query=session.createQuery("DELETE from courses WHERE id="+courseId);
            //query.setParameter("id", course.getId());
            query.executeUpdate();
            //session.delete(courseId);
            //session.persist(course);
            transaction.commit();

        }catch (Exception exception){
            if(transaction != null){
                transaction.rollback();
            }
            message = exception.getClass() + ": " + exception.getMessage();
            System.out.println(message);
        }finally {
            session.close();

        }

        return course;
    }

}

