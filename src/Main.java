import Enums.Gender;
import model.*;
import service.serviceImpl.GroupServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Enums.Gender;
import model.Datebase;
import model.Group;
import model.Person;
import service.CheckInfo;
import service.GroupService;
import service.serviceImpl.GroupServiceImpl;
import service.serviceImpl.LessonServiceImpl;
import service.serviceImpl.StudentServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerNum=new Scanner(System.in);
        Scanner scannerWord=new Scanner(System.in);
        Datebase datebase=new Datebase();

        GroupServiceImpl groupService=new GroupServiceImpl(datebase);
        StudentServiceImpl studentService=new StudentServiceImpl(datebase,groupService);
        LessonServiceImpl lessonService=new LessonServiceImpl(datebase);

        int num,num1;
        Long groupCounter=0L;
        Long studentCounter=0L;
        Long lessonCounter=0L;

        Person person=new Person(1L,"admin","admonova","a","a", Gender.FEMALE);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        if (time.getHour() > 6 && time.getHour() < 12) {
            System.out.println(date + "\nGood morning. Time is -> " + time.getHour() + ":" + time.getMinute());
        } else if (time.getHour() > 12 && time.getHour() < 18) {
            System.out.println(date + "\nGood afternoon. Time is -> " + time.getHour() + ":" + time.getMinute());
        } else if(time.getHour() > 18 && time.getHour() < 21){
            System.out.println(date + "\nGood evening. Time is -> " + time.getHour() + ":" + time.getMinute());
        }else{
            System.out.println(date + "\nGood night. Time is -> " + time.getHour() + ":" + time.getMinute());
        }

        while (true) {
            System.out.println("\nPress 1 if you have an account\nPress 2 if you forget your password and want to change");
            try {
                switch (num = scannerNum.nextInt()) {
                    case 1:
                        while (true) {
                            try {
                                System.out.println("Input email and password!");
                                System.out.println("Input email");
                                String emailScan = scannerWord.nextLine();
                                System.out.println("Input password");
                                String passwordScan = scannerWord.nextLine();
                                if (!person.getEmail().equalsIgnoreCase(emailScan) || !person.getPassword().equalsIgnoreCase(passwordScan)) {
                                    throw new CheckInfo("Wrong email or password input.\nTry again");
                                } else {
                                    while (true) {
                                        System.out.println("""
                                        ---------------------------------------------------------------------------------------
                                        -----------------------------------Choose operation------------------------------------
                                        ---------------------------------------------------------------------------------------
                                        1 -> Add new group                              9  -> Get all student's lesson
                                        2 -> Get group by name                          10 -> Delete student
                                        3 -> Update group name                          11 -> Add new lesson to group
                                        4 -> Get all groups                             12 -> Get lesson by name
                                        5 -> Add new student to group                   13 -> Get all lesson by group name
                                        6 -> Update student                             14 -> Delete lesson
                                        7 -> Find student by first name                 15 -> Delete group
                                        8 -> Get all students by group name             16 -> Exit
                                        """);


                                        switch (num1 = scannerNum.nextInt()) {
                                            case 1:
                                                System.out.println("Input group name");
                                                String name = scannerWord.nextLine();
                                                System.out.println("Input description");
                                                String description = scannerWord.nextLine();
                                                Group group=new Group(++groupCounter, name, description, new ArrayList<>(), new ArrayList<>());
                                                groupService.getGroups().add(group);
                                                System.out.println("Group is successfully created\n"+group);
                                                break;
                                            case 2:
                                                System.out.println("Input group name you want to find");
                                                String grName = scannerWord.nextLine();
                                                System.out.println(groupService.getGroupByName(groupService.getGroups(), grName));
                                                break;
                                            case 3:
                                                try {
                                                    System.out.println("Input a group name you want to update");
                                                    String oldGroupName = scannerWord.nextLine();
                                                    System.out.println("Create a new name");
                                                    String newGroupName = scannerWord.nextLine();
                                                    if(newGroupName.equalsIgnoreCase(oldGroupName)){
                                                        throw new CheckInfo("New name can't be the same as old name.\nTry again");
                                                    }
                                                    System.out.println("Write a description to a new group");
                                                    String newGroupDescription = scannerWord.nextLine();
                                                    System.out.println(groupService.updateGroupName(oldGroupName, new Group(newGroupName,
                                                            newGroupDescription, datebase.getLessons(), datebase.getStudents())));

                                                }catch (CheckInfo e){
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 4:
                                                System.out.println(groupService.getAllGroups());
                                                break;
                                            case 5:
                                                try {
                                                    System.out.println("Input a group name,you want to add students to");
                                                    String groupName = scannerWord.nextLine();
                                                    System.out.println("Input student's name");
                                                    String studentName = scannerWord.nextLine();
                                                    if(studentName.isEmpty()){
                                                        throw new CheckInfo("Name can't be empty.\nTry again");
                                                    }
                                                    System.out.println("Input student's last name");
                                                    String studentLastName = scannerWord.nextLine();
                                                    if(studentLastName.isEmpty()){
                                                        throw new CheckInfo("Last name can't be empty.\nTry again");
                                                    }
                                                    System.out.println("Input student's email");
                                                    String studentEmail = scannerWord.nextLine();
                                                    if(studentEmail.isEmpty()){
                                                        throw new CheckInfo("Email can't be empty.\nTry again");
                                                    }else if (!studentEmail.contains("@") || !studentEmail.contains(".")  ) {
                                                        throw new CheckInfo("Email should contain '@' and 'dot'.\nTry again");
                                                    }
                                                    System.out.println("Create a new password with the length at least of 7 symbols");
                                                    String studentEmailPassword = scannerWord.nextLine();
                                                    if(studentEmailPassword.length()<7){
                                                        throw new CheckInfo("Password should consist of at least 7 symbols");
                                                    }
                                                    System.out.println("Input a student's gender");
                                                    String studentGender = scannerWord.nextLine();
                                                    if (studentGender.equalsIgnoreCase(Gender.FEMALE.name())) {
                                                        studentGender = String.valueOf(Gender.FEMALE);
                                                    } else if (studentGender.equalsIgnoreCase(Gender.MALE.name())) {
                                                        studentGender = String.valueOf(Gender.MALE);
                                                    } else {
                                                        throw new CheckInfo("Error gender\nTry again");
                                                    }
                                                    groupService.addNewStudentToGroup(groupService.getGroups(),groupName,new Student(++studentCounter, studentName, studentLastName, studentEmail, studentEmailPassword, studentGender));
//                                                    studentService.addNewStudentToGroup(groupName,
//                                                            new Student(++studentCounter, studentName, studentLastName, studentEmail, studentEmailPassword, studentGender));
                                                }catch (CheckInfo e){
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 6:
                                                try {
                                                    System.out.println("Input student's email you want to update");
                                                    String oldStudentEmail = scannerWord.nextLine();
                                                    System.out.println("Input new student's first name");
                                                    String newStudentFirstName = scannerWord.nextLine();
                                                    if(newStudentFirstName.isEmpty()){
                                                        throw new CheckInfo("Name can't be empty.\nTry again");
                                                    }
                                                    System.out.println("Input new student's last name");
                                                    String newStudentLastName = scannerWord.nextLine();
                                                    if(newStudentLastName.isEmpty()){
                                                        throw new CheckInfo("Last name can't be empty.\nTry again");
                                                    }
                                                    System.out.println("Input new student's email");
                                                    String newStudentEmail = scannerWord.nextLine();
                                                    if(newStudentEmail.isEmpty()){
                                                        throw new CheckInfo("Email can't be empty.\nTry again");
                                                    } else if (!newStudentEmail.contains("@")) {
                                                        throw new CheckInfo("Email should contain '@'.\nTry again");
                                                    }
                                                    System.out.println("Create a password for a new student");
                                                    String newStudentPassword = scannerWord.nextLine();
                                                    if(newStudentPassword.length()<7){
                                                        throw new CheckInfo("Password should consist of at least 7 symbols");
                                                    }
                                                    System.out.println("Input new student's gender");
                                                    String newStudentGender = scannerWord.nextLine();
                                                    if (newStudentGender.equalsIgnoreCase(Gender.FEMALE.name())) {
                                                        newStudentGender = String.valueOf(Gender.FEMALE);
                                                    } else if (newStudentGender.equalsIgnoreCase(Gender.MALE.name())) {
                                                        newStudentGender = String.valueOf(Gender.MALE);
                                                    } else {
                                                        throw new CheckInfo("Error gender\nTry again");
                                                    }
                                                    System.out.println(groupService.updateStudent(groupService.getGroups(),oldStudentEmail,
                                                            new Student(newStudentFirstName, newStudentLastName,
                                                                    newStudentEmail, newStudentPassword, newStudentGender)));
                                                }catch (CheckInfo e){
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 7:
                                                System.out.println("Input a student name");
                                                String nameToFindStudent = scannerWord.nextLine();
                                                studentService.findStudentByFirstName(groupService.getGroups(), nameToFindStudent);
                                                break;
                                            case 8:
                                                System.out.println("Input a group name to get students from there");
                                                String groupNameToGetStudents = scannerWord.nextLine();
                                                System.out.println(studentService.getAllStudentsByGroupName(groupService.getGroups(), groupNameToGetStudents));
                                                break;
                                            case 9:
                                                System.out.println("Input student's email to get his lessons");
                                                String studentEmailToGetLesson = scannerWord.nextLine();
                                                studentService.getAllStudentLesson(groupService.getGroups(), studentEmailToGetLesson);
                                                break;
                                            case 10:
                                                System.out.println("Input student's email you want to delete");
                                                String studentEmailToDelete = scannerWord.nextLine();
                                                studentService.deleteStudentByEmail(groupService.getGroups(), studentEmailToDelete);
                                                break;
                                            case 11:
                                                try {
                                                    System.out.println("Input group name to add lessons");
                                                    String groupNameToAddLessons = scannerWord.nextLine();
                                                    System.out.println("Input lesson name");
                                                    String lessonName = scannerWord.nextLine();
                                                    if(lessonName.isEmpty()){
                                                        throw new CheckInfo("Lesson name can'be empty.\nTry again");
                                                    }
                                                    System.out.println("Write a lesson description");
                                                    String lessonDescription = scannerWord.nextLine();
                                                    if(lessonDescription.isEmpty()){
                                                        throw new CheckInfo("Lesson description can't be empty");
                                                    }
                                                    System.out.println(lessonService.addNewLessonToGroup(groupService.getGroups(), groupNameToAddLessons, new Lesson(++lessonCounter, lessonName, lessonDescription)));
                                                }catch (CheckInfo e){
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 12:
                                                System.out.println("Input lesson name to get info about it");
                                                String lessonNameToGet = scannerWord.nextLine();
                                                System.out.println(lessonService.getLessonByName(groupService.getGroups(),lessonNameToGet));
                                                break;
                                            case 13:
                                                System.out.println("Input a group name to get all lessons from there");
                                                String groupNameToGetAllLessons = scannerWord.nextLine();
                                                System.out.println(lessonService.getAllLessonByGroupName(groupService.getGroups(), groupNameToGetAllLessons));
                                                break;
                                            case 14:
                                                System.out.println("Input lesson name you want to delete");
                                                String lessonNameToDelete = scannerWord.nextLine();
                                                lessonService.deleteLessonByName(groupService.getGroups(), lessonNameToDelete);
                                                break;
                                            case 15:
                                                System.out.println("Write a group name you want to delete");
                                                String groupNameToDelete = scannerWord.nextLine();
                                                groupService.deleteGroupByName(groupService.getGroups(), groupNameToDelete);
                                                break;
                                                default:
                                                    System.out.println("Number 1-15 are only available");
                                }
                            }
                        }
                    } catch (CheckInfo e) {
                        System.out.println(e.getMessage());
                    }

                }
                        case 2:
                            System.out.println("Input your email");
                            String emailScan = scannerWord.nextLine();
                            System.out.println("Create a new password(at least 7symbols)");
                            String passwordScan = scannerWord.nextLine();
                            try {
                                if (emailScan.equalsIgnoreCase(person.getEmail()) && passwordScan.length() > 7) {
                                    System.out.println("Successfully changed password");
                                    person.setPassword(passwordScan);
                                    System.out.println(person);
                                } else {
                                    throw new CheckInfo("Wrong password creating");
                                }
                            } catch (CheckInfo e) {
                                System.out.println(e.getMessage());
                            } default:
                                System.out.println("Available numbers 1-2 only");
                                }
                            }catch (InputMismatchException e) {
                                System.out.println("Input only numbers");
                                break;
                            }


        }
    }
}
