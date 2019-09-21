package misc;

import facades.StudentFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Henning
 */
public class test {

    static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    static StudentFacade facade = StudentFacade.getStudentFacade(emf);

    public static void main(String[] args) {
        System.out.println(facade.getAllStudents().size());
    }
}
