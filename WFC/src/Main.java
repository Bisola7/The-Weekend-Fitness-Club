import java.util.*;
public class Main {
    public static Scanner input = new Scanner(System.in);
    static Timetable timeTable = new Timetable();
    static CustomerManager customerManager = new CustomerManager();
    static BookingManager bookingManager = new BookingManager();
    static Customer currentCustomer = null;
    private static void showLessons(ArrayList<Lesson> lessons){
        for(int i= 0; i<lessons.size(); i++){
            System.out.println("[" + (int)(i+1) + "] " + lessons.get(i).changeLessonToString());
        }
    }
    private static void showBookings(HashMap<String, Booking> bookings){
        System.out.println("Your bookings:");
        bookings.forEach((key, lesson) -> {
            Booking booking = currentCustomer.getBooked().get(key);
            System.out.println("BookingID = " + key + ", Lesson = " + lesson.changeBookingToString() + ", Status = " + booking.getStatus());
        });
    }
    public static boolean emailValidation(String email){
        String validateEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(validateEmail);
    }
    public static void registerCustomer(){
        System.out.println("What is your first name?");
        String firstName = input.next();
        System.out.println("What is your last name?");
        String lastName = input.next();
        String name = firstName + " " + lastName;
        String email = "";
        boolean validation = true;
        while (validation){
            System.out.println("What is your email?");
            email = input.next();
            if (emailValidation(email)){
                validation = false;
            } else {
                System.out.println("Invalid email address");
            }
        }
        Customer newCustomer = new Customer(name, email);
        boolean successful = customerManager.addCustomer(newCustomer);
        if (successful){
            currentCustomer = newCustomer;
            System.out.println("Registration successful. Your email will be your login");
        } else {
            System.out.println("The email address already exists. Please sign in");
            signIn();
        }
    }
    //add while
    public static Lesson selectLesson (){
        ArrayList<Lesson> lessons;
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("Enter 1 to view lesson timetable by day (Saturday or Sunday)");
            System.out.println("Enter 2 to view lesson timetable by fitness class (e.g YOGA, ZUMBA)");
            String answer = input.next();
            if (answer.equals("1")) {
                while (true) {
                    System.out.println("Please enter 1 to view lessons that take place on Saturdays");
                    System.out.println("Please enter 2 to view lessons that take place on Sundays");
                    String dayType = input.next();
                    if (dayType.equals("1")) {
                        lessons = timeTable.getByDay("saturday");
                        break;
                    } else if (dayType.equals("2")) {
                        lessons = timeTable.getByDay("sunday");
                        break;
                    } else {
                        System.out.println("Invalid option. Please select a valid option.");
                    }
                }
                break;
            } else if (answer.equals("2")) {
                while (true) {
                    System.out.println("Choose fitness type:");
                    System.out.println("1 for yoga, 2 for pilate, 3 for swimming, 4 for rock climbing, 5 for zumba");
                    String fitnessOptions = input.next();
                    if (fitnessOptions.equals("1")) {
                        lessons = timeTable.getByFitnessType("yoga");
                        break;
                    } else if (fitnessOptions.equals("2")) {
                        lessons = timeTable.getByFitnessType("pilate");
                        break;
                    } else if (fitnessOptions.equals("3")) {
                        lessons = timeTable.getByFitnessType("swimming");
                        break;
                    } else if (fitnessOptions.equals("4")) {
                        lessons = timeTable.getByFitnessType("rock climbing");
                        break;
                    } else if (fitnessOptions.equals("5")) {
                        lessons = timeTable.getByFitnessType("zumba");
                        break;
                    } else {
                        System.out.println("Invalid option. Please select a valid option.");
                    }
                }
                break;
            } else {
                System.out.println("Invalid option. Please select a valid option.");
            }
        }
        while (true) {
            showLessons(lessons);
            int selectedOption = input.nextInt();
            if (selectedOption < 1 || selectedOption > lessons.size()) {
                System.out.println("Invalid option. Please select a valid option.");
                continue;
            }
            Lesson l = lessons.get(selectedOption-1);
            if (currentCustomer.getLessons().contains(l)) {
                System.out.println("You have already selected this lesson. Please select another lesson.");
                continue;
            }
            if (l.isFilled()) {
                System.out.println("This lesson is already full. Please select another lesson.");
                continue;
            }
            return l;
        }
    }

    private static void bookALesson(){
        Lesson lesson = selectLesson();
         String bookingID = bookingManager.registerNewBooking(currentCustomer, lesson);
         System.out.println("This is your bookingID: " + bookingID);
        customerActivities();
    }

    private static void cancelABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("Please enter the booking ID of the booking you want to cancel");
        String bookingID = input.next().toUpperCase();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        if (booking == null){
            System.out.println("The booking ID entered does not exist. Please ensure to type in the correct booking ID");
            cancelABooking();
        } else {
            bookingManager.cancelBooking(booking);
            System.out.println("You have successfully cancelled the booking with booking ID: " +bookingID);
        }
        customerActivities();
    }

    private static void changeABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("Please enter the booking ID of the booking you want to change from");
        String bookingID = input.next().toUpperCase();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        if (booking == null){
            System.out.println("The booking ID entered does not exist. Please ensure to type in the correct booking ID");
            cancelABooking();
        } else {
            System.out.println("Please select the lesson you want to change to");
            Lesson lesson = selectLesson();
            bookingManager.changeBooking(booking, lesson);
        }
        customerActivities();
    }
    private static void attendABooking(){
        showBookings(currentCustomer.getBooked());
        System.out.println("Please enter the booking ID of the lesson you want to attend");
        String bookingID = input.next().toUpperCase();
        Booking booking = currentCustomer.getBooked().get(bookingID);
        if (booking == null) {
            System.out.println("The booking ID entered does not exist. Please ensure to type in the correct booking ID");
            attendABooking();
        } else if (booking.getStatus() == "cancelled") {
            System.out.println("You cannot attend this booking because it has been cancelled");
            attendABooking();
        } else if (booking.getStatus() == "attended") {
            System.out.println("You have already attended this lesson");
            attendABooking();
        } else {
            System.out.println("Thank you for attending the lesson, please select an option:");
            System.out.println("Enter 1 to only rate a lesson");
            System.out.println("Enter 2 to rate and review a lesson");
            String choice = input.next();
            if (choice.equals("1")){
                System.out.println("Please enter a number (1-5) where 1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");
                int number = input.nextInt();
                Review rating = new Review(number);
                bookingManager.attendLesson(booking, rating);
                System.out.println("Thank you! Your rating has been recorded");
            } else if (choice.equals("2")) {
                System.out.println("Please enter a number (1-5) where 1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");
                int rating = input.nextInt();
                System.out.println("Please write a review of the lesson");
                String message = input.next();
                Review review = new Review(rating, message);
                bookingManager.attendLesson(booking, review);
                System.out.println("Thank you! Your review has been recorded");
            }
        }
        customerActivities();
    }
    private static void signIn(){
        System.out.println("Please enter your email address");
        String email = input.next();
        currentCustomer = customerManager.getCustomer(email);
        if (currentCustomer == null){
            System.out.println("This user does not exist");
            launchApp();
        }
    }
    private static void signOut(){
        currentCustomer = null;
        System.out.println("Thank you! You have been signed out");
        launchApp();
    }
    private static void viewReport(){
        ArrayList<Lesson> lessons = new ArrayList<>();
        ReportGenerator reportGenerator = new ReportGenerator(timeTable);
        System.out.println("Enter 1 to view monthly report");
        System.out.println("Enter 2 to view champion");
        int answer = input.nextInt();
        if (answer == 1) {
            System.out.println("Please enter the month of report you want to view (1 or 2)");
            int month = input.nextInt();
            reportGenerator.getMonthlyReport(month);
        } else if (answer == 2){
            System.out.println("Please enter the month of report you want to view (1 or 2)");
            int month = input.nextInt();
            reportGenerator.getChampionReport(month);
        } else {
            System.out.println("Please enter a valid option");
            viewReport();
        }
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
            viewReport();
        } else {
            System.out.println("Please enter a valid option");
            launchApp();
        }

    }

    public static void main(String[]args){
        System.out.println("==============================================");
        System.out.println("     WELCOME TO THE WEEKEND FITNESS CLUB!     ");
        System.out.println("==============================================");
        launchApp();
        //registerCustomer();
        //selectLesson();
    }

}
