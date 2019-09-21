package tests;

import entities.Student;
import facades.StudentFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Henning
 */
//@Disabled
public class facadesTest {

    static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    static StudentFacade facade = StudentFacade.getStudentFacade(emf);

    @Test
    public void getAllStudentsTest() {
        assertEquals("Jens", facade.getAllStudents().get(0).getFirstname());
    }

    @Test
    public void getAllStudentsByFirstNameTest() {
        assertEquals(1, facade.getAllStudentsByFirstName("Anders").size());
    }

    @Test
    public void createStudentTest() {
        Student s = new Student();
        s.setFirstname("TestFirstName");
        s.setLastname("TestLastName");
        facade.createStudent(s);
        assertEquals(true, facade.getAllStudents().stream().anyMatch(o -> o.getFirstname().equals("TestFirstName")));
    }

    @Test
    public void AssignStudentTest() {
        facade.AssignStudent(7, 1);
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        System.out.println("getidstuff" + facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId());
        assertEquals(true, facade.getAllStudentsByFirstName("TestFirstName").get(0).getSemester().getId()==1);
    }
    
    @Test
    public void getAllStudentsByLastNameTest() {
        assertEquals(2, facade.getAllStudentsByLastName("Doe").size());

    }

    @Test
    public void countStudentsTest() {
        assertEquals(facade.getAllStudents().size(), facade.countStudents());
    }

    //@Test
    //public void countStudentsBySemesterNameTest() {
    //    assertEquals(2,facade.countStudentsBySemesterName("Computer Science 3.sem"));
    //}
//
    //@Test
    //public void countStudentsByAllSemestersTest() {
    //    System.out.println("Facade Count students by all semesters: " + facade.countStudentsByAllSemesters());
    //}
    //
    //@Test
    //public void getTeacherWithMostSemestersTest() {
    //    assertEquals(3, facade.getTeacherWithMostSemesters());
//
    //}
}
