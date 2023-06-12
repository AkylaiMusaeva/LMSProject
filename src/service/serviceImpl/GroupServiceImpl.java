package service.serviceImpl;

import model.Datebase;
import model.Group;
import model.Student;
import service.CheckInfo;
import service.GroupService;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    private List<Group>groups=new ArrayList<>();

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    private  Datebase datebase;
    public GroupServiceImpl(Datebase datebase) {
        this.datebase = datebase;
    }
    public void addNewStudentToGroup(List<Group>groups,String groupName, Student student) {
        boolean b=false;
        try{
        for (Group gr: groups) {
            if (gr.getGroupName().equals(groupName)) {
                gr.getStudents().add(student);
                System.out.println(student);
                System.out.println("Successfully saved student");
                b=true;
                break;
            }else{
                b=false;
            }
        }if(b==false){
            throw new CheckInfo("There is no such group to add students.\nTry again");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
    }
    public Student updateStudent(List<Group>groups,String oldStudentEmail, Student newStudent) {
        boolean b = false;
        try {
            for (Group g:groups) {
                for (Student s : g.getStudents()) {
                    if (s.getEmail().equalsIgnoreCase(oldStudentEmail)) {
                        s.setFirstName(newStudent.getFirstName());
                        s.setLastName(newStudent.getLastName());
                        s.setEmail(newStudent.getEmail());
                        s.setPassword(newStudent.getPassword());
                        s.setGender(newStudent.getGender());
                        b = true;
                        System.out.println("Student successfully has updated");
                        return s;
                    } else {
                        b = false;
                    }
                }
            }
            if (b == false) {
                throw new CheckInfo("There is no such student email in database to update.\nTry again");
            }
        } catch (CheckInfo e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public Group addNewGroup(Group group) {
        try{
            for (int i = 0; i < datebase.getGroups().size(); i++) {
                if(group.getGroupName().equalsIgnoreCase(datebase.getGroups().get(i).getGroupName())){
                    throw  new CheckInfo("Such group name is already exist\nTry again");
                }
            }
            datebase.getGroups().add(group);
            System.out.println(group);
            }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Group getGroupByName(List<Group> groups, String name) {
        boolean found=false;
        try {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getGroupName().equalsIgnoreCase(name)) {
                found = true;
                return groups.get(i);
            } else {
                found = false;
            }
        }
        if(found==false){
                    throw new CheckInfo("There is no such group in database.\nTry again");
                }
            }catch (CheckInfo e){
                System.out.println(e.getMessage());
            }
        return null;
    }
    @Override
    public Group updateGroupName(String oldGroupName, Group newGroup) {
        boolean found=false;
        try {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getGroupName().equalsIgnoreCase(oldGroupName)) {
                    Group oldGroup = groups.get(i);
                    oldGroup.setGroupName(newGroup.getGroupName());
                    oldGroup.setDescription(newGroup.getDescription());
                    oldGroup.setLessons(newGroup.getLessons());
                    oldGroup.setStudents(newGroup.getStudents());
                    System.out.println("Group has successfully updated!");
                    System.out.println(oldGroup);
                    found=true;
                    break;
                } else {
                    found=false;
                }
            }
            if(found==false){
                throw new CheckInfo("There is no such group in database to update.\nTry again");
            }
        } catch (CheckInfo e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Group> getAllGroups() {
        return groups;
    }

    @Override
    public void deleteGroupByName(List<Group> groups, String groupName) {
        boolean found=false;
        try {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getGroupName().equalsIgnoreCase(groupName)) {
                    groups.remove(groups.get(i));
                    System.out.println(groupName + " has successfully deleted");
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }
            if (found == false) {
                throw new CheckInfo("There is no such group in database to delete.\nTry again");
            }
        }catch (CheckInfo e) {
                System.out.println(e.getMessage());
            }
        }
    }

