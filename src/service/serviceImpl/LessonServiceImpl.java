package service.serviceImpl;

import model.Datebase;
import model.Group;
import model.Lesson;
import model.Student;
import service.CheckInfo;
import service.LessonService;

import java.util.ArrayList;
import java.util.List;

public class LessonServiceImpl implements LessonService {
    private List<Group>groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    private Datebase datebase;

    public LessonServiceImpl(Datebase datebase) {
        this.datebase = datebase;
    }

    @Override
    public Lesson addNewLessonToGroup(List<Group>groups,String groupName, Lesson lesson) {
        boolean b=false;
        try{
            for(Group g:groups){
                if(g.getGroupName().equalsIgnoreCase(groupName)){
                    g.getLessons().add(lesson);
                    System.out.println(lesson);
                    System.out.println("Lesson successfully has created!");
                    b=true;
                    break;
                }else {
                    b=false;
                }
            }
            if(b==false){
                throw new CheckInfo("There is no such group name in data base.\nTry again");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson getLessonByName(List<Group> groups, String lessonName) {
        boolean b=false;
        try{
            for(Group g:groups){
                for(Lesson l:g.getLessons()){
                    if(l.getLessonName().equalsIgnoreCase(lessonName)){
                        b=true;
                        return l;
                    }else {
                        b=false;
                    }
                }
            }if(b==false){
                throw new CheckInfo("Not found lesson with this name.\nTry again");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllLessonByGroupName(List<Group> groups, String groupName) {
        boolean b=false;
        try{
            for(Group g:groups){
                    if(g.getGroupName().equalsIgnoreCase(groupName)){
                        b=true;
                        return g.getLessons();
                    }else {
                        b=false;
                    }
                }
            if(b==false){
                throw new CheckInfo("Not found group with this name.\nTry again");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteLessonByName(List<Group> groups, String lessonName) {
        boolean b=false;
        try{
            for(Group g:groups){
                for(Lesson l:g.getLessons()){
                    if(l.getLessonName().equalsIgnoreCase(lessonName)){
                        g.getLessons().remove(l);
                        System.out.println("Lesson successfully has deleted");
                        b=true;
                        break;
                    }else{
                        b=false;
                    }
                }
            }if(b==false){
                throw new CheckInfo("Not found such lesson name.\nTry again");
            }
        }catch (CheckInfo e){
            System.out.println(e.getMessage());
        }
    }
}
