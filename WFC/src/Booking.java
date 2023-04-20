public class Booking {
    private Customer customer;
    private Lesson lesson;

    private String bookingID;

    private String status;
    // other fields and methods as required

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Booking(Customer customer, Lesson lesson, String bookingID) {
        this.customer = customer;
        this.lesson = lesson;
        this.bookingID = bookingID;
    }
    // getters and setters for fields
}
