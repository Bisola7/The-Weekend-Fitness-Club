import java.util.ArrayList;
import java.util.HashMap;

public class FitnessType {
    private String name;
    private int price;
    private HashMap<Integer, ArrayList<Lesson>> lessonAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HashMap<Integer, ArrayList<Lesson>> getFitnessAvailable() {
        return lessonAvailable;
    }

    public void setFitnessAvailable(HashMap<Integer, ArrayList<Lesson>> fitnessAvailable) {
        this.lessonAvailable = fitnessAvailable;
    }

    public FitnessType(String name, int price) {
        this.name = name;
        this.price = price;
        this.lessonAvailable = new HashMap<Integer, ArrayList<Lesson>>();
    }
    public void addLesson(Lesson lesson) {
        this.lessonAvailable.putIfAbsent(lesson.getMonth(), new ArrayList<Lesson>());
        ArrayList<Lesson> currentMonthArrayList = this.lessonAvailable.get(lesson.getMonth());
        currentMonthArrayList.add(lesson);
    }
    public ArrayList<Lesson> lessonByMonth(int month){
        return lessonAvailable.get(month);
    }

    public int getTotalEarningByMonth(int month){
        ArrayList<Lesson> lessons = this.lessonAvailable.get(month);
        int total = 0;
        for (Lesson lesson : lessons){
            total += lesson.getAttendance() * this.price;
        }

        return  total;
    }

}
