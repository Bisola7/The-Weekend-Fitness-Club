import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<Lesson> lessons;
    private HashMap<String, Booking> booked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public HashMap<String, Booking> getBooked() {
        return booked;
    }

    public void setBooked(HashMap<String, Booking> booked) {
        this.booked = booked;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setBookings(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
// other fields and methods as required

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lessons = new ArrayList<Lesson>();
        this.booked = new HashMap<String, Booking>();
    }
    public void addBooking(Booking booking){
        this.booked.put(booking.getBookingID(), booking);
    }
    public boolean addLesson(Lesson lesson) {
        try{
            this.lessons.add(lesson);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean removeLesson(Lesson lesson){
        try{
            this.lessons.remove(lesson);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
