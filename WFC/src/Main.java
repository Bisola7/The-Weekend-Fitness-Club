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
    private static void launchApp(){
        System.out.println("welcome");
        System.out.println("book lesson");

    }
    private static void bookALesson(){
        Lesson lesson = selectLesson();
        String bookingID = bookingManager.registerNewBooking(currentCustomer, lesson);
        System.out.println(bookingID);
    }

    private static void cancelABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("enter booking id exactly as shown");
        String bookingID = input.next();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        bookingManager.cancelBooking(booking);
    }

    private static void changeABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("enter booking id exactly as shown");
        String bookingID = input.next();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        System.out.println("select lesson you want to change to");
        Lesson lesson = selectLesson();
        bookingManager.changeBooking(booking, lesson);
    }
    private static void signIn(){
        System.out.println("enter phone");
        String phone = input.next();
        currentCustomer = customerManager.getCustomer(phone);
        if (currentCustomer == null){
            signIn();
        }
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
        } else {
            System.out.println("invalid already exist");
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

    public static void main(String[]args){

        // Create instances of classes
        FitnessType yogaClass = new FitnessType("YOGA", 10);
        FitnessType pilateClass = new FitnessType("PILATE", 12);
        FitnessType swimmingClass = new FitnessType("SWIMMING", 15);
        FitnessType rockClimbingClass = new FitnessType("ROCK CLIMBING", 20);
        FitnessType zumbaClass = new FitnessType("ZUMBA", 18);

//        Customer customer1 = new Customer("Bisola Oladipo", "oladipo@gmail.com", 734989549);
//        Customer customer2 = new Customer("Elizabeth Lizzy", "lanre@yahoo.com", 908854321);
//        Customer customer3 = new Customer("Olamide Smith", "smith@gmail.com", 745676754);
//        Customer customer4 = new Customer("Kacper Cris", "kacper@hotmail.com", 805134921);
//        Customer customer5 = new Customer("Bisi Joshua", "bisi@gmail.com", 700057054);



        timeTable.createLesson(1,1,"saturday",1,yogaClass);
        timeTable.createLesson(1, 1, "saturday", 2, swimmingClass);
        timeTable.createLesson(1, 1, "sunday", 1, zumbaClass);
        timeTable.createLesson(1, 1, "sunday", 2, rockClimbingClass);
        timeTable.createLesson(1, 3, "saturday", 1, yogaClass);
        timeTable.createLesson(1, 3, "sunday", 2, swimmingClass);
        timeTable.createLesson(1, 4, "saturday", 1, pilateClass);
        timeTable.createLesson(1, 4, "sunday", 2, rockClimbingClass);
        timeTable.createLesson(1, 5, "sunday", 1, yogaClass);
        timeTable.createLesson(1, 17, "sunday", 2, swimmingClass);
        timeTable.createLesson(1, 18, "sunday", 2, zumbaClass);
        timeTable.createLesson(1, 18,"sunday", 2, rockClimbingClass);
        timeTable.createLesson(1, 25, "sunday", 1,yogaClass);
        timeTable.createLesson(1, 25, "sunday", 2,swimmingClass);
        timeTable.createLesson(1, 26, "sunday", 1,pilateClass);
        timeTable.createLesson(1, 26,"sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 2, "sunday", 1,yogaClass);
        timeTable.createLesson(2, 2, "sunday", 2,swimmingClass);
        timeTable.createLesson(2, 9, "sunday", 1,zumbaClass);
        timeTable.createLesson(2, 9, "sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 10, "sunday", 1,yogaClass);
        timeTable.createLesson(2, 10, "sunday", 2,swimmingClass);
        timeTable.createLesson(2, 17, "sunday", 1,pilateClass);
        timeTable.createLesson(2, 17, "sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 18, "sunday", 1, yogaClass);
        timeTable.createLesson(2, 18, "sunday", 2,swimmingClass);
        timeTable.createLesson(2, 25, "sunday", 1,zumbaClass);
        timeTable.createLesson(2, 25,"sunday", 2,rockClimbingClass);
        timeTable.createLesson(2, 26, "sunday", 1,yogaClass);
        timeTable.createLesson(2, 26, "sunday", 2,swimmingClass);
        timeTable.createLesson(3, 2, "sunday", 1,pilateClass);
        timeTable.createLesson(3, 2,"sunday", 2,rockClimbingClass);

//        Customer customer = registerCustomer();
//        boolean successful = customerManager.addCustomer(customer);
//        if (successful){
//            System.out.println("done!");
//        } else {
//            System.out.println("exists");
//        }
        registerCustomer();
        bookALesson();
    }
}
