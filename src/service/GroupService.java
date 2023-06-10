package service;

import model.Group;

import java.util.List;

public interface GroupService {
    public Group  addNewGroup(Group group);
    public Group getGroupByName(List<Group>groups,String name);
    public Group updateGroupName(String oldGroupName,Group newGroup);
    public List<Group> getAllGroups(List<Group>groups);
    public void deleteGroupByName(List<Group>groups,String groupName);

}
