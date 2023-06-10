package service.serviceImpl;

import model.Datebase;
import model.Group;
import model.Lesson;
import model.Student;
import service.CheckInfo;
import service.GroupService;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    //    private final GroupService groupService;
//    public StudentServiceImpl(GroupService groupService){
//        this.groupService=groupService;
//    }
    private Datebase datebase = new Datebase();

    public StudentServiceImpl(Datebase datebase) {
        this.datebase = datebase;
    }

    @Override
    public void addNewStudentToGroup(List<Group> groups, String groupName, Student student) {
//        Group group=groupService.getGroupByName(groups,groupName);
//        group.setStudent(student);
//        System.out.println(student);
//        System.out.println("Successfully saved a new student");

        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            for (Group gr : datebase.getGroups()) {
                if (gr.getGroupName().equalsIgnoreCase(groupName)) {

//            if (groups.get(i).getGroupName().equalsIgnoreCase(groupName)) {
                    datebase.getStudents().add(student);
                    count++;
                    groups.get(i).getStudents().add(student);
                    System.out.println(student);
                    System.out.println("ok");
                }
                System.out.println("Successfully saved a new student");
                System.out.println(count);
                System.out.println(i + "index1");
            }
        }
    }

    @Override
    public Student updateStudent(String oldStudentEmail, Student newStudent) {
        boolean b = false;
        try {
            for (int i = 0; i < datebase.getStudents().size(); i++) {
                Student oldStudent = datebase.getStudents().get(i);
                if (datebase.getStudents().get(i).getEmail().equalsIgnoreCase(oldStudentEmail)) {
                    oldStudent.setFirstName(newStudent.getFirstName());
                    oldStudent.setLastName(newStudent.getLastName());
                    oldStudent.setEmail(newStudent.getEmail());
                    oldStudent.setPassword(newStudent.getPassword());
                    oldStudent.setGender(newStudent.getGender());
                    b = true;
                    return oldStudent;
                } else {
                    b = false;
                }
            }
            if (b == false) {
                throw new CheckInfo("There is no such student email in database to update");
            }
        } catch (CheckInfo e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void findStudentByFirstName(List<Group> groups, String name) {
        boolean b = false;
        try {
            for (Group g : groups) {
                for (Student s : g.getStudents())
                    if (name.equalsIgnoreCase(s.getFirstName())) {
                        System.out.println(g);
                        b = true;
                        break;
                    } else {
                        b = false;
                    }
            }
            if (b == false) {
                throw new CheckInfo("There is no student with such name");
            }
        } catch (CheckInfo e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentsByGroupName(List<Group> groups, String groupName) {
        boolean b = false;
        try {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getGroupName().equalsIgnoreCase(groupName)) {
                    b = true;
                    return groups.get(i).getStudents();
                } else {
                    b = false;
                }
            }
            if (b == false) {
                throw new CheckInfo("Not found group with inputed name");
            }
        } catch (CheckInfo e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public Lesson getAllStudentLesson(List<Group> groups, String studentEmail) {
        boolean b=false;
        try{
            for(Group g:groups){
                for(Student s:g.getStudents()){
                    if(s.getEmail().equalsIgnoreCase(studentEmail)){
                        System.out.println(g.getLessons());
                        b=true;
                        break;
                    }
                    else {
                        b=false;
                    }
                }
            }
            if(b==false){
                throw new CheckInfo("There is no a student with such email");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteStudentByEmail(List<Group> groups, String studentEmail) {
        boolean b=false;
        try{
            for(Group g:groups){
                for(Student s:g.getStudents()){
                    if(s.getEmail().equalsIgnoreCase(studentEmail)){
                        g.getStudents().remove(s);
                        System.out.printf("Student with email %s has successfully deleted",s.getEmail());
                        b=true;
                        break;
                    }else {
                        b=false;
                    }
                }
            }
            if(b==false){
                throw new CheckInfo("There is no a student with such email");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
    }
}

