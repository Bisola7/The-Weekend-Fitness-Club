import java.util.ArrayList;

public class Customer {
    private String name;
    private String email;
    private Integer phoneNumber;
    private ArrayList<Lesson> lessons;

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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Lesson> getBookings() {
        return lessons;
    }

    public void setBookings(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
// other fields and methods as required

    public Customer(String name, String email, Integer phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lessons = new ArrayList<Lesson>();
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
