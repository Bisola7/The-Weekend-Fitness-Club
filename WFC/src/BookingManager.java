import java.util.*;

public class BookingManager {
    private ArrayList<Booking> bookings;

    public BookingManager() {
        this.bookings = new ArrayList<Booking>();
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public String registerNewBooking(Customer customer, Lesson lesson){
            String bookingId = generateBookingId();
            Booking booking = new Booking(customer, lesson, bookingId);
            bookings.add(booking);
            customer.addBooking(booking);
            lesson.addCapacity();
            return bookingId;
    }

    public boolean cancelBooking(Booking booking){
        try{
            Customer customer = booking.getCustomer();
            Lesson lesson = booking.getLesson();
            customer.removeLesson(lesson);
            lesson.reduceCapacity();
            booking.setStatus("cancelled");
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean changeBooking(Booking booking, Lesson newLesson){
        try{
            Customer customer = booking.getCustomer();
            Lesson lesson = booking.getLesson();
            customer.removeLesson(lesson);
            lesson.reduceCapacity();
            booking.setStatus("changed");
            customer.addLesson(newLesson);
            newLesson.addCapacity();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean attendLesson(Booking booking, Review rating){
        try{
            Customer customer = booking.getCustomer();
            Lesson lesson = booking.getLesson();
            customer.removeLesson(lesson);
            lesson.reduceCapacity();
            booking.setStatus("attended");
            booking.getLesson().customerReview(rating);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static Random random = new Random();

    public static String generateBookingId() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return sb.toString();
    }

}
