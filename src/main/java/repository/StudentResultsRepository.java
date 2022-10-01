package repository;
import dto.Course;
import dto.Student;
import dto.StudentResults;
import dto.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class StudentResultsRepository {
    private final SessionFactory factory;
    private Session session;
    private Transaction transaction;
    public StudentResultsRepository(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
    public String createStudentResults(StudentResults studentResults){
        String message;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(studentResults);
            transaction.commit();
            message = "results created successfully";
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
}



