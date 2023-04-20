import java.util.ArrayList;

public class Lesson {

    public final static int CAPACITY = 5;
    private int month;
    private int day;
    private int session;
    private int attendance;
    private int currentCapacity;
    private float averageRating;
    private boolean isFilled;
    private ArrayList<Review> customerReview;

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    private FitnessType fitnessType;

    public Lesson(int month, int day, int session, FitnessType fitnessType) {
        this.month = month;
        this.day = day;
        this.session = session;
        this.fitnessType = fitnessType;
    }

    public ArrayList<Review> getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(ArrayList<Review> customerReview) {
        this.customerReview = customerReview;
    }

    public Lesson(ArrayList<Review> customerReview) {
        this.customerReview = customerReview;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public FitnessType getFitnessType() {
        return fitnessType;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void setFitnessType(FitnessType fitnessType) {
        this.fitnessType = fitnessType;
    }

    public void addCapacity(){
        this.currentCapacity += 1;
        updateCapacity();
    }

    public void reduceCapacity(){
        this.currentCapacity -= 1;
        updateCapacity();
    }

    private void updateCapacity(){
        if(this.currentCapacity == CAPACITY){
            setFilled(true);
        } else{
            setFilled(false);
        }
    }
    public void customerReview(Review addReview){
        customerReview.add(addReview);
    }
}
