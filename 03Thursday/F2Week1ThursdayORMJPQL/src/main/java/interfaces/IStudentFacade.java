
package interfaces;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import java.util.List;

public interface IStudentFacade {
    
    public List<Student> getAllStudents();
    
    public List<Student> getAllStudentsByFirstName(String name);
    
    public Student createStudent(Student s);
    
    public void AssignStudent(int studentID, int semesterID);
    
    public List<Student> getAllStudentsByLastName(String lastName);
    
    public long countStudents();
    
    public long countStudentsBySemesterName(String name);
    
    public long countStudentsByAllSemesters();
    
    public Teacher getTeacherWithMostSemesters();
    
}
