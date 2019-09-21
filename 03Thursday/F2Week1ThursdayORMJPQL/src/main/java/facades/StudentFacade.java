package facades;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import interfaces.IStudentFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class StudentFacade implements IStudentFacade {

    private static StudentFacade instance;
    private static EntityManagerFactory emf;

    private StudentFacade() {
    }

    public static StudentFacade getStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<Student> getAllStudents() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findAll")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> getAllStudentsByFirstName(String firstName) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findByFirstname")
                    .setParameter("firstname", firstName)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Student createStudent(Student s) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return s;
    }

    @Override
    public void AssignStudent(int studentID, int semesterID) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Student s = (Student) em.createNamedQuery("Student.findById")
                    .setParameter("id", studentID)
                    .getSingleResult();
            Semester sem = (Semester) em.createNamedQuery("Semester.findById")
                    .setParameter("id", semesterID)
                    .getSingleResult();
            sem.getStudentCollection().add(s);
            s.setSemester(sem);
            em.merge(s);
            em.merge(sem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> getAllStudentsByLastName(String lastName) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findByLastname")
                    .setParameter("lastname", lastName)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public long countStudents() {
        EntityManager em = getEntityManager();
        try {
            return (long) em.createQuery("Select COUNT(s) from Student s").getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public long countStudentsBySemesterName(String semName) {
      //  EntityManager em = getEntityManager();
      //  try {
      //      return (long) em.createQuery("SELECT COUNT(s) FROM Student s JOIN s.CURRENTSEMESTER_ID e on e.id WHERE e.name = :name")
      //              .setParameter("name", semName)
      //              .getSingleResult();
      //  } finally {
      //      em.close();
      //  }
      throw new UnsupportedOperationException();

    }

    @Override
    public long countStudentsByAllSemesters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher getTeacherWithMostSemesters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
