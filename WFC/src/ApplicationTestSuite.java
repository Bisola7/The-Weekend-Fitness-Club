// import static org.junit.jupiter.api.Assertions.*;

//class BookingManagerTest {
//
//    @org.junit.jupiter.api.BeforeEach
//    void setUp() {
//    }
//
//    @org.junit.jupiter.api.AfterEach
//    void tearDown() {
//    }
//}
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTestSuite {
    private BookingHandler bookingHandler;
    private Customer customer;
    private FitnessType fitnessType;
    private Lesson lesson;
    private Review review;

    @BeforeEach
    void setUp() {
        bookingHandler = new BookingHandler();
        customer = new Customer("John Doe", "johndoe@example.com");
        fitnessType = new FitnessType("Yoga", 10);
        lesson = new Lesson(1, 1, "saturday", 1, fitnessType);
        review = new Review(4, "Great class!");
    }

    @Test
    void testRegisterNewBooking() {
        String bookingId = bookingHandler.registerNewBooking(customer, lesson);
        assertNotNull(bookingId);
        assertEquals(1, bookingHandler.getBookings().size());
        Booking booking = bookingHandler.getBookings().get(0);
        assertEquals(bookingId, booking.getBookingID());
        assertEquals(customer, booking.getCustomer());
        assertEquals(lesson, booking.getLesson());
        assertEquals("unattended", booking.getStatus());
        assertEquals(1, customer.getBooked().size());
        assertTrue(customer.getBooked().containsKey(bookingId));
        assertEquals(1, customer.getLessons().size());
        assertTrue(customer.getLessons().contains(lesson));
    }

    @Test
    void testCancelBooking() {
        String bookingId = bookingHandler.registerNewBooking(customer, lesson);
        Booking booking = bookingHandler.getBookings().get(0);
        boolean result = bookingHandler.cancelBooking(booking);
        assertTrue(result);
        assertEquals("cancelled", booking.getStatus());
        assertEquals(0, customer.getLessons().size());
        assertFalse(customer.getLessons().contains(lesson));
    }

    @Test
    void testChangeBooking() {
        String bookingId = bookingHandler.registerNewBooking(customer, lesson);
        FitnessType newFitnessType = new FitnessType("Pilates", 12);
        Lesson newLesson = new Lesson(1, 1, "saturday", 1, fitnessType);
        boolean result = bookingHandler.changeBooking(bookingHandler.getBookings().get(0), newLesson);
        assertTrue(result);
        assertEquals("changed", bookingHandler.getBookings().get(0).getStatus());
        assertFalse(customer.getLessons().contains(lesson));
        assertEquals(1, customer.getLessons().size());
        assertTrue(customer.getLessons().contains(newLesson));
    }

    @Test
    void testAttendLesson() {
        String bookingId = bookingHandler.registerNewBooking(customer, lesson);
        boolean result = bookingHandler.attendLesson(bookingHandler.getBookings().get(0), review);
        assertTrue(result);
        assertEquals("attended", bookingHandler.getBookings().get(0).getStatus());
        assertEquals(0, customer.getLessons().size());
        assertFalse(customer.getLessons().contains(lesson));
        assertEquals(1, lesson.getAttendance());
        assertEquals(1, lesson.getCustomerReview().size());
        assertTrue(lesson.getCustomerReview().contains(review));
    }

    @Test
    public void testGetAverageRating() {
        Timetable timetable = new Timetable();
        Customer customer1 = new Customer("Bisola Oladipo", "bisola@gmail.com");
        Customer customer2 = new Customer("Smith David", "ola@gmail.com");
        Customer customer3 = new Customer("Lanre Lizzy", "lanre@gmail.com");
        Booking booking1 = new Booking(customer1, lesson, "bgh56");
        Booking booking2 = new Booking(customer2, lesson, "opiut");
        Booking booking3 = new Booking(customer3, lesson, "bh543");
        Review review1 = new Review(5);
        Review review2 = new Review(3);
        Review review3 = new Review(1);
        bookingHandler.attendLesson(booking1, review1);
        bookingHandler.attendLesson(booking2, review2);
        bookingHandler.attendLesson(booking3, review3);
        double expectedAverage = 3.0;
        double actualAverage = lesson.getAverageRating();
        assertEquals(expectedAverage, actualAverage, 0.01);
    }
}
