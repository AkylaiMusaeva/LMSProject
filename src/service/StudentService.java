package service;

import model.Group;
import model.Lesson;
import model.Student;

import java.util.List;

public interface StudentService {
    public void addNewStudentToGroup(String groupName,Student student);
    public Student updateStudent(String oldStudentEmail,Student newStudent);
    public void findStudentByFirstName(List<Group> groups,String name);
    public List<Student> getAllStudentsByGroupName(List<Group>groups,String groupName);
    public Lesson getAllStudentLesson(List<Group>groups, String studentEmail);
    public void deleteStudentByEmail(List<Group>groups,String studentEmail);




}
