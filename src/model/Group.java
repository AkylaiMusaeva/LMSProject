package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Long id;
    private String groupName;
    private String description;
    private List<Lesson>lessons;
    private List<Student> students;

    public Group(String groupName, String description, List<Lesson> lessons, List<Student> students) {
        this.groupName = groupName;
        this.description = description;
        this.lessons = lessons;
        this.students = students;
    }

    public Group(Long id, String groupName, String description) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
    }

    public Group(Long id, String groupName, String description, List<Lesson> lessons, List<Student> students) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.lessons = lessons;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void setStudent(Student student){
        if(this.students==null){
            this.students=new ArrayList<>();
        }
        this.students.add(student);
    }
    @Override
    public String toString() {
        return "Group:" +
                "\nid=" + id +
                "\ngroupName='" + groupName +
                "\ndescription='" + description +
                "\nlessons=" + lessons +
                "\nstudents=" + students+"\n";
    }
}
