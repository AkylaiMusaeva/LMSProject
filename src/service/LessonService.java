package service;

import model.Group;
import model.Lesson;

import java.util.List;

public interface LessonService {
    public Lesson addNewLessonToGroup(String groupName, Lesson lesson);
    public Lesson getLessonByName(List<Group> groups,String lessonName);
    public List<Lesson> getAllLessonByGroupName (List<Group>groups,String groupName);
    public void deleteLessonByName(List<Group>groups,String lessonName);


}
