import java.util.*;
public class Main {
    public static Scanner input = new Scanner(System.in);
    static Timetable timeTable = new Timetable();
    static CustomerManager customerManager = new CustomerManager();
    static BookingManager bookingManager = new BookingManager();
    static Customer currentCustomer = null;
    private static void showLessons(ArrayList<Lesson> lessons){
        System.out.println("Month---week---day---session---fitness---price");
        for(int i= 0; i<lessons.size(); i++){
            System.out.println("[" + (int)(i+1) + "] " + lessons.get(i).changeLessonToString());
        }
    }
    private static void showBookings(HashMap<String, Booking> bookings){
        System.out.println("Your bookings:");
        bookings.forEach((key, value) -> {
            System.out.println("BookingID=" + key + ", Lesson=" + value);
        });
    }
    public static void registerCustomer(){
        System.out.println("What is your first name?");
        String firstName = input.next();
        System.out.println("What is your last name?");
        String lastName = input.next();
        String name = firstName + " " + lastName;
        System.out.println("What is your email?");
        String email = input.next();
        System.out.println("What is your phone?");
        String phone = input.next();
        Customer newCustomer = new Customer(name, email, phone);
        boolean successful = customerManager.addCustomer(newCustomer);
        if (successful){
            currentCustomer = newCustomer;
            System.out.println("Registration successful. Your phone number will be your login");
        } else {
            System.out.println("The phone number already exists. Please sign in");
            signIn();
        }
    }
    //add while
    public static Lesson selectLesson (){
        ArrayList<Lesson> lessons;
        System.out.println("by day or fitness");
        System.out.println("select 1 or 2");
        String answer = input.next();
        if (answer.equals("1")){
            System.out.println("sat or sun?");
            String dayType = input.next();
            if (dayType.equals("1")) {
                lessons = timeTable.getByDay("saturday");
            } else if (dayType.equals("2")){
                lessons = timeTable.getByDay("sunday");
            } else {
                return null; //false
            }
        } else if (answer.equals("2")){
            System.out.println("choose fitness type");
            System.out.println("1 for yoga, 2....");
            String fitnessOptions = input.next();
            if (fitnessOptions.equals("1")) {
                lessons = timeTable.getByFitnessType("yoga");
            } else if (fitnessOptions.equals("2")){
                lessons = timeTable.getByFitnessType("pilate");
            } else if (fitnessOptions.equals("3")){
                lessons = timeTable.getByFitnessType("swimming");
            } else if (fitnessOptions.equals("4")){
                lessons = timeTable.getByFitnessType("rock climbing");
            } else if (fitnessOptions.equals("5")){
                lessons = timeTable.getByFitnessType("zumba");
            } else {
                return null; //false
            }
        } else {
            return null; //false
        }
        while (true) {
            showLessons(lessons);
            int selectedOption = input.nextInt();
            Lesson l = lessons.get(selectedOption-1);
            if (!currentCustomer.getLessons().contains(l)){
                return l;
            }
        }
    }

    private static void bookALesson(){
        Lesson lesson = selectLesson();
        String bookingID = bookingManager.registerNewBooking(currentCustomer, lesson);
        System.out.println(bookingID);
    }

    private static void cancelABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("Please enter the booking ID of the booking you want to cancel");
        String bookingID = input.next();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        if (booking == null){
            System.out.println("The booking ID entered does not exist. Please ensure to type in the correct booking ID");
            cancelABooking();
        } else {
            bookingManager.cancelBooking(booking);
        }
    }

    private static void changeABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("Please enter the booking ID of the booking you want to change from");
        String bookingID = input.next();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        System.out.println("Please select the lesson you want to change to");
        Lesson lesson = selectLesson();
        bookingManager.changeBooking(booking, lesson);
    }
    private static void attendABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("enter booking id exactly as shown to attend the booking");
        String bookingID = input.next();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        if (booking == null) {
            System.out.println("The booking ID entered does not exist. Please ensure to type in the correct booking ID");
            attendABooking();
        } else {
            System.out.println("Please enter 1 to only rate a lesson and enter 2 to rate and review a lesson");
            String choice = input.next();
            if (choice.equals("1")){
                System.out.println("Please enter a number (1-5) where 1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");
                int number = input.nextInt();
                Review rating = new Review(number);
                bookingManager.attendLesson(booking, rating);
            } else if (choice.equals("2")) {
                System.out.println("Please enter a number (1-5) where 1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");
                int rating = input.nextInt();
                System.out.println("Please write a review of the lesson");
                String message = input.next();
                Review review = new Review(rating, message);
                bookingManager.attendLesson(booking, review);
            }
        }
    }
    private static void signIn(){
        System.out.println("enter phone");
        String phone = input.next();
        currentCustomer = customerManager.getCustomer(phone);
        if (currentCustomer == null){
            signIn();
        }
    }
    private static void signOut(){
        currentCustomer = null;
        System.out.println("Thank you! You have been signed out");
        launchApp();
    }

    private static void customerActivities() {
        System.out.println("Enter 1 to book a lesson");
        System.out.println("Enter 2 to cancel a booking");
        System.out.println("Enter 3 to attend a lesson");
        System.out.println("Enter 4 to change a booking");
        System.out.println("Enter 5 to sign out");
        int option = input.nextInt();
        if (option == 1) {
            bookALesson();
        } else if (option == 2) {
            cancelABooking();
        } else if (option == 3)  {
            attendABooking();
        } else if (option == 4) {
            changeABooking();
        } else if(option == 5){
            signOut();
        }else {
            System.out.println("Please enter a valid option");
            customerActivities();
        }
    }

    public static  void launchApp(){
        System.out.println("Welcome to The Weekend Fitness Club!");
        System.out.println("Please select an option:");
        System.out.println("Enter 1 to Sign in");
        System.out.println("Enter 2 to Sign up");
        System.out.println("Enter 3 to view report");
        int option = input.nextInt();
        if (option == 1) {
            signIn();
            customerActivities();
        } else if (option == 2) {
            registerCustomer();
            customerActivities();
        } else if (option == 3) {
            System.out.println("How would you like to view report?");
        } else {
            System.out.println("Please enter a valid option");
            launchApp();
        }

    }

    public static void main(String[]args){

        // Create instances of classes
        FitnessType yogaClass = new FitnessType("YOGA", 10);
        FitnessType pilateClass = new FitnessType("PILATE", 12);
        FitnessType swimmingClass = new FitnessType("SWIMMING", 15);
        FitnessType rockClimbingClass = new FitnessType("ROCK CLIMBING", 20);
        FitnessType zumbaClass = new FitnessType("ZUMBA", 18);

        timeTable.createLesson(1, 1,"saturday",1,yogaClass);
        timeTable.createLesson(1, 1, "saturday", 2, swimmingClass);
        timeTable.createLesson(1, 1, "sunday", 1, zumbaClass);
        timeTable.createLesson(1, 1, "sunday", 2, rockClimbingClass);
        timeTable.createLesson(1, 2, "saturday", 1, yogaClass);
        timeTable.createLesson(1, 2, "saturday", 2, swimmingClass);
        timeTable.createLesson(1, 2, "sunday", 1, pilateClass);
        timeTable.createLesson(1, 2, "sunday", 2, rockClimbingClass);
        timeTable.createLesson(1, 3, "saturday", 1, yogaClass);
        timeTable.createLesson(1, 3, "saturday", 2, swimmingClass);
        timeTable.createLesson(1, 3, "sunday", 1, zumbaClass);
        timeTable.createLesson(1, 3,"sunday", 2, rockClimbingClass);
        timeTable.createLesson(1, 4, "saturday", 1,yogaClass);
        timeTable.createLesson(1, 4, "saturday", 2,swimmingClass);
        timeTable.createLesson(1, 4, "sunday", 1,pilateClass);
        timeTable.createLesson(1, 4,"sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 1, "saturday", 1,yogaClass);
        timeTable.createLesson(2, 1, "saturday", 2,swimmingClass);
        timeTable.createLesson(2, 1, "sunday", 1,zumbaClass);
        timeTable.createLesson(2, 1, "sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 2, "saturday", 1,yogaClass);
        timeTable.createLesson(2, 2, "saturday", 2,swimmingClass);
        timeTable.createLesson(2, 2, "sunday", 1,pilateClass);
        timeTable.createLesson(2, 2, "sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 3, "saturday", 1, yogaClass);
        timeTable.createLesson(2, 3, "saturday", 2,swimmingClass);
        timeTable.createLesson(2, 3, "sunday", 1,zumbaClass);
        timeTable.createLesson(2, 3,"sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 4, "saturday", 1,yogaClass);
        timeTable.createLesson(2, 4, "saturday", 2,swimmingClass);
        timeTable.createLesson(3, 4, "sunday", 1,pilateClass);
        timeTable.createLesson(3, 4,"sunday", 2,rockClimbingClass);

        launchApp();
    }

}
