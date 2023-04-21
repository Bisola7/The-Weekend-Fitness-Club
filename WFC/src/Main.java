import java.util.*;
public class Main {
    public static final Scanner input = new Scanner(System.in);

    public void main(String[]args){
        // Create instances of classes
        FitnessType yogaClass = new FitnessType("YOGA", 10);
        FitnessType pilateClass = new FitnessType("PILATE", 12);
        FitnessType swimmingClass = new FitnessType("SWIMMING", 15);
        FitnessType rockClimbingClass = new FitnessType("ROCK CLIMBING", 20);
        FitnessType zumbaClass = new FitnessType("ZUMBA", 18);

        Customer customer1 = new Customer("Bisola Oladipo", "oladipo@gmail.com", 734989549);
        Customer customer2 = new Customer("Elizabeth Lizzy", "lanre@yahoo.com", 908854321);
        Customer customer3 = new Customer("Olamide Smith", "smith@gmail.com", 745676754);
        Customer customer4 = new Customer("Kacper Cris", "kacper@hotmail.com", 805134921);
        Customer customer5 = new Customer("Bisi Joshua", "bisi@gmail.com", 700057054);

        Lesson lesson1 = new Lesson(1, 1, 1, yogaClass);
        Lesson lesson2 = new Lesson(1, 1, 2, swimmingClass);
        Lesson lesson3 = new Lesson(1, 2, 1, zumbaClass);
        Lesson lesson4 = new Lesson(1, 2, 2, rockClimbingClass);
        Lesson lesson5 = new Lesson(1, 9, 1, yogaClass);
        Lesson lesson6 = new Lesson(1, 9, 2, swimmingClass);
        Lesson lesson7 = new Lesson(1, 10, 1, pilateClass);
        Lesson lesson8 = new Lesson(1, 10, 2, rockClimbingClass);
        Lesson lesson9 = new Lesson(1, 17, 1, yogaClass);
        Lesson lesson10 = new Lesson(1, 17, 2, swimmingClass);
        Lesson lesson11 = new Lesson(1, 18, 1, zumbaClass);
        Lesson lesson12 = new Lesson(1, 18,2, rockClimbingClass);
        Lesson lesson13 = new Lesson(1, 25, 1, yogaClass);
        Lesson lesson14 = new Lesson(1, 25, 2, swimmingClass);
        Lesson lesson15 = new Lesson(1, 26, 1, pilateClass);
        Lesson lesson16 = new Lesson(1, 26,2, rockClimbingClass);
        Lesson lesson17 = new Lesson(2, 2, 1, yogaClass);
        Lesson lesson18 = new Lesson(2, 2, 2, swimmingClass);
        Lesson lesson19 = new Lesson(2, 9, 1, zumbaClass);
        Lesson lesson20 = new Lesson(2, 9, 2, rockClimbingClass);
        Lesson lesson21 = new Lesson(2, 10, 1, yogaClass);
        Lesson lesson22 = new Lesson(2, 10, 2, swimmingClass);
        Lesson lesson23 = new Lesson(2, 17, 1, pilateClass);
        Lesson lesson24 = new Lesson(2, 17, 2, rockClimbingClass);
        Lesson lesson25 = new Lesson(2, 18, 1, yogaClass);
        Lesson lesson26 = new Lesson(2, 18, 2, swimmingClass);
        Lesson lesson27 = new Lesson(2, 25, 1, zumbaClass);
        Lesson lesson28 = new Lesson(2, 25,2, rockClimbingClass);
        Lesson lesson29 = new Lesson(2, 26, 1, yogaClass);
        Lesson lesson30 = new Lesson(2, 26, 2, swimmingClass);
        Lesson lesson31 = new Lesson(3, 2, 1, pilateClass);
        Lesson lesson32 = new Lesson(3, 2,2, rockClimbingClass);

// Add data to system
        yogaClass.addLesson(lesson1);
        yogaClass.addLesson(lesson5);
        yogaClass.addLesson(lesson9);
        yogaClass.addLesson(lesson13);
        yogaClass.addLesson(lesson17);
        yogaClass.addLesson(lesson21);
        yogaClass.addLesson(lesson25);
        yogaClass.addLesson(lesson29);
        swimmingClass.addLesson(lesson2);
        swimmingClass.addLesson(lesson6);
        swimmingClass.addLesson(lesson10);
        swimmingClass.addLesson(lesson14);
        swimmingClass.addLesson(lesson18);
        swimmingClass.addLesson(lesson22);
        swimmingClass.addLesson(lesson26);
        swimmingClass.addLesson(lesson30);
        zumbaClass.addLesson(lesson3);
        zumbaClass.addLesson(lesson11);
        zumbaClass.addLesson(lesson19);
        zumbaClass.addLesson(lesson27);
        rockClimbingClass.addLesson(lesson4);
        rockClimbingClass.addLesson(lesson8);
        rockClimbingClass.addLesson(lesson12);
        rockClimbingClass.addLesson(lesson16);
        rockClimbingClass.addLesson(lesson20);
        rockClimbingClass.addLesson(lesson24);
        rockClimbingClass.addLesson(lesson28);
        rockClimbingClass.addLesson(lesson32);
        pilateClass.addLesson(lesson7);
        pilateClass.addLesson(lesson15);
        pilateClass.addLesson(lesson23);
        pilateClass.addLesson(lesson31);

        System.out.println("Welcome to The Weekend Fitness Club!");
//        System.out.println("Please enter your phone number");
//        String answer = input.next();
        //customer.customer1(answer);
    }
}
