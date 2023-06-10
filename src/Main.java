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
import service.serviceImpl.StudentServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scannerNum = new Scanner(System.in);
//        Scanner scannerWord = new Scanner(System.in);
//        Datebase datebase = new Datebase();
//        Long groupCounter = 0L;
//        int num, num1;
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        if (time.getHour() > 6 && time.getHour() < 12) {
//            System.out.println(date + "\nGood morning. Time is -> " + time.getHour() + ":" + time.getMinute());
//        } else if (time.getHour() > 12 && time.getHour() < 18) {
//            System.out.println(date + "\nGood afternoon. Time is -> " + time.getHour() + ":" + time.getMinute());
//        } else {
//            System.out.println(date + "\nGood evening. Time is -> " + time.getHour() + ":" + time.getMinute());
//        }
//
//
//
//        List<Group> group1 = new ArrayList<>();
//
//        Person person = new Person(1L, "Akylai", "Musaeva", "akylai@gmail.com", "akylai", Gender.FEMALE);
//        while (true) {
//            System.out.println("\nPress 1 if you have an account\nPress 2 if you forget your password and want to change");
//            try {
//                switch (num = scannerNum.nextInt()) {
//                    case 1:
//        while (true) {
//            try {
//                System.out.println("Input email and password!");
//                System.out.println("Input email");
//                String emailScan = scannerWord.nextLine();
//                System.out.println("Input password");
//                String passwordScan = scannerWord.nextLine();
//                if (!person.getEmail().equalsIgnoreCase(emailScan) || !person.getPassword().equalsIgnoreCase(passwordScan)) {
//                    throw new CheckInfo("Wrong email or password input.\nTry again");
//                } else {
//                    System.out.println("""
//                            ---------------------------------------------------------------------------------------
//                            -----------------------------------Choose operation------------------------------------
//                            ---------------------------------------------------------------------------------------
//                            1 -> Add new group                              9  -> Get all student's lesson
//                            2 -> Get group by name                          10 -> Delete student
//                            3 -> Update group name                          11 -> Add new lesson to group
//                            4 -> Get all groups                             12 -> Get lesson by name
//                            5 -> Add new student to group                   13 -> Get all lesson by group name
//                            6 -> Update student                             14 -> Delete lesson
//                            7 -> Find student by first name                 15 -> Delete group
//                            8 -> Get all students by group name             16 -> Exit
//                            """);
//                    switch (num = scannerNum.nextInt()) {
//                        case 1:
//
//
//                            break;
//
//                        default:
//                            System.out.println("Введите числа только от 1-15");
//                    }
//
//                }
//            } catch (CheckInfo e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    case 2:
//        System.out.println("Input your email");
//        String emailScan = scannerWord.nextLine();
//        System.out.println("Create a new password(at least 7symbols)");
//        String passwordScan = scannerWord.nextLine();
//        try {
//            if (emailScan.equalsIgnoreCase(person.getEmail()) && passwordScan.length() > 7) {
//                System.out.println("Successfully changed password");
//                System.out.println(person);
//            } else {
//                throw new CheckInfo("Wrong password creating");
//            }
//        } catch (CheckInfo e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    }catch (InputMismatchException e) {
//                System.out.println("Input only numbers");
//            }
//        }
//    }
//}






public class Main{
    public static void main(String[] args) {
        Scanner scannerNum=new Scanner(System.in);
        Scanner scannerWord=new Scanner(System.in);
        Datebase datebase=new Datebase();
        GroupServiceImpl groupService=new GroupServiceImpl(datebase);
      //  StudentServiceImpl studentService=new StudentServiceImpl(groupService);
        StudentServiceImpl studentService=new StudentServiceImpl(datebase);
        int num;
        Long groupCounter=0L;
        Long studentCounter=0L;
        Long lessonCounter=0L;
        Person person=new Person(1L,"admin","admonova","admin@gmail.com","admin123", Gender.FEMALE);


        while(true){
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
            switch(num=scannerNum.nextInt()){
                case 1:
                    System.out.println("Input group name");
                    String name=scannerWord.nextLine();
                    System.out.println("Input description");
                    String description=scannerWord.nextLine();
                    System.out.println(groupService.addNewGroup(new Group(++groupCounter, name, description, datebase.getLessons(), datebase.getStudents())));
                    break;
                case 2:
                    System.out.println("Input group name you want to find");
                    String grName=scannerWord.nextLine();
                    System.out.println(groupService.getGroupByName(datebase.getGroups(), grName));
                    break;
                case 3:
                    System.out.println("Input a group name you want to update");
                    String oldGroupName=scannerWord.nextLine();
                    System.out.println("Create a new name");
                    String newGroupName=scannerWord.nextLine();
                    System.out.println("Write a description to a new group");
                    String newGroupDescription=scannerWord.nextLine();
                    System.out.println(groupService.updateGroupName(oldGroupName,new Group(newGroupName,
                            newGroupDescription,datebase.getLessons(),datebase.getStudents())));
                    break;
                case 4:
                    System.out.println(groupService.getAllGroups(datebase.getGroups()));
                    break;
                case 5:
                    System.out.println("Input a group name,you want to add students to");
                    String groupName=scannerWord.nextLine();
                    System.out.println("Input student's name");
                    String studentName=scannerWord.nextLine();
                    System.out.println("Input student's last name");
                    String studentLastName=scannerWord.nextLine();
                    System.out.println("Input student's email");
                    String studentEmail=scannerWord.nextLine();
                    System.out.println("Create a new password with the length at least of 7 symbols");
                    String studentEmailPassword=scannerWord.nextLine();
//                    System.out.println("Input a student's gender");
//                    String gender=scannerWord.nextLine();
                    studentService.addNewStudentToGroup(datebase.getGroups(),groupName,
                            new Student(++studentCounter,studentName,studentLastName,studentEmail,studentEmailPassword,Gender.FEMALE));
                    System.out.println("2");
                    break;
                case 6:
                    System.out.println("Input student's email you want to update");
                    String oldStudentEmail=scannerWord.nextLine();
                    System.out.println("Input new student's first name");
                    String newStudentFirstName=scannerWord.nextLine();
                    System.out.println("Input new student's last name");
                    String newStudentLastName=scannerWord.nextLine();
                    System.out.println("Input new student's email");
                    String newStudentEmail=scannerWord.nextLine();
                    System.out.println("Create a password for a new student");
                    String newStudentPassword=scannerWord.nextLine();
//                    System.out.println("Input new student's gender");
//                    String newStudentGender=scannerWord.nextLine();
                    System.out.println(studentService.updateStudent(oldStudentEmail,
                            new Student(newStudentFirstName, newStudentLastName,
                                    newStudentEmail, newStudentPassword, Gender.FEMALE)));
                    break;
                case 7:
                    System.out.println("Input a student name");
                    String nameToFindStudent=scannerWord.nextLine();
                    studentService.findStudentByFirstName(datebase.getGroups(),nameToFindStudent);
                    break;
                case 8:
                    System.out.println("Input a group name to get students from there");
                    String groupNameToGetStudents=scannerWord.nextLine();
                    studentService.getAllStudentsByGroupName(datebase.getGroups(), groupNameToGetStudents);
                    break;
                case 9:

                    break;
                case 10:
                    System.out.println("Input student's email you want to delete");
                    String studentEmailToDelete=scannerWord.nextLine();
                    studentService.deleteStudentByEmail(datebase.getGroups(),studentEmailToDelete);
                    break;
                case 15:
                    System.out.println("Write a group name you want to delete");
                    String groupNameToDelete=scannerWord.nextLine();
                    groupService.deleteGroupByName(datebase.getGroups(),groupNameToDelete);
                    break;




            }
        }
    }
}
