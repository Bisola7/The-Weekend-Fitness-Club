import java.util.ArrayList;
import java.util.HashMap;

public class Timetable {
    private HashMap<String, ArrayList<Lesson>> byDay;
    private HashMap<String, ArrayList<Lesson>> byFitness;

    public Timetable() {
        this.byDay = new HashMap<String, ArrayList<Lesson>>();
        this.byFitness = new HashMap<String, ArrayList<Lesson>>();
    }
    public ArrayList<Lesson> getByDay(String day){
        return byDay.get(day.toLowerCase());
    }
    public ArrayList<Lesson> getByFitnessType(String fitnessType){
        return byFitness.get(fitnessType.toLowerCase());
    }
    public void createLesson(int month, int week, String day, int session, FitnessType fitnessType){
        Lesson lesson = new Lesson(month, week, day.toLowerCase(), session, fitnessType);
        byDay.putIfAbsent(day.toLowerCase(), new ArrayList<Lesson>());
        byDay.get(day.toLowerCase()).add(lesson);
        byFitness.putIfAbsent(fitnessType.getName().toLowerCase(), new ArrayList<Lesson>());
        byFitness.get(fitnessType.getName().toLowerCase()).add(lesson);
    }

}
