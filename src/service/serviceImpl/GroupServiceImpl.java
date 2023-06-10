package service.serviceImpl;

import model.Datebase;
import model.Group;
import service.CheckInfo;
import service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    private Datebase datebase = new Datebase();
    public GroupServiceImpl(Datebase datebase) {
        this.datebase = datebase;
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
                    throw new CheckInfo("There is no such group in database");
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
            for (int i = 0; i < datebase.getGroups().size(); i++) {
                if (datebase.getGroups().get(i).getGroupName().equalsIgnoreCase(oldGroupName)) {
                    Group oldGroup = datebase.getGroups().get(i);
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
    public List<Group> getAllGroups(List<Group> groups) {
        return datebase.getGroups();
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
                throw new CheckInfo("There is no such group in database to delete ");
            }
        }catch (CheckInfo e) {
                System.out.println(e.getMessage());
            }
        }
    }

