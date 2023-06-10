package service.serviceImpl;

import model.Group;
import model.Lesson;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    @Override
    public Lesson addNewLessonToGroup(String groupName, Lesson lesson) {
        return null;
    }

    @Override
    public Lesson getLessonByName(List<Group> groups, String lessonName) {
        return null;
    }

    @Override
    public List<Lesson> getAllLessonByGroupName(List<Group> groups, String groupName) {
        return null;
    }

    @Override
    public void deleteLessonByName(List<Group> groups, String lessonName) {

    }
}
