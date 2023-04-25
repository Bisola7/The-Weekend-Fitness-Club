import java.util.ArrayList;
import java.util.HashMap;

public class Timetable {
    private HashMap<String, ArrayList<Lesson>> byDay;
    private HashMap<String, ArrayList<Lesson>> byFitness;

    public ArrayList<Lesson> getAllLessonsByMonth(int month) {
        return allLessons.get(month);
    }

    public void setAllFitnessTypes(ArrayList<FitnessType> allFitnessTypes) {
        this.allFitnessTypes = allFitnessTypes;
    }

    private HashMap<Integer, ArrayList<Lesson>> allLessons = new HashMap<Integer, ArrayList<Lesson>>();


    FitnessType yogaClass = new FitnessType("YOGA", 10);
    FitnessType pilateClass = new FitnessType("PILATE", 12);
    FitnessType swimmingClass = new FitnessType("SWIMMING", 15);
    FitnessType rockClimbingClass = new FitnessType("ROCK CLIMBING", 20);
    FitnessType zumbaClass = new FitnessType("ZUMBA", 18);

    public ArrayList<FitnessType> getAllFitnessTypes() {
        return allFitnessTypes;
    }

    public HashMap<String, ArrayList<Lesson>> getByDay() {
        return byDay;
    }

    private ArrayList<FitnessType> allFitnessTypes = new ArrayList<FitnessType>();



    public Timetable() {
        this.byDay = new HashMap<String, ArrayList<Lesson>>();
        this.byFitness = new HashMap<String, ArrayList<Lesson>>();
        this.createLesson(1, 1,"saturday",1,yogaClass);
        this.createLesson(1, 1, "saturday", 2, swimmingClass);
        this.createLesson(1, 1, "sunday", 1, zumbaClass);
        this.createLesson(1, 1, "sunday", 2, rockClimbingClass);
        this.createLesson(1, 2, "saturday", 1, yogaClass);
        this.createLesson(1, 2, "saturday", 2, swimmingClass);
        this.createLesson(1, 2, "sunday", 1, pilateClass);
        this.createLesson(1, 2, "sunday", 2, rockClimbingClass);
        this.createLesson(1, 3, "saturday", 1, yogaClass);
        this.createLesson(1, 3, "saturday", 2, swimmingClass);
        this.createLesson(1, 3, "sunday", 1, zumbaClass);
        this.createLesson(1, 3,"sunday", 2, rockClimbingClass);
        this.createLesson(1, 4, "saturday", 1,yogaClass);
        this.createLesson(1, 4, "saturday", 2,swimmingClass);
        this.createLesson(1, 4, "sunday", 1,pilateClass);
        this.createLesson(1, 4,"sunday", 2,rockClimbingClass);
        this.createLesson(2, 1, "saturday", 1,yogaClass);
        this.createLesson(2, 1, "saturday", 2,swimmingClass);
        this.createLesson(2, 1, "sunday", 1,zumbaClass);
        this.createLesson(2, 1, "sunday", 2,rockClimbingClass);
        this.createLesson(2, 2, "saturday", 1,yogaClass);
        this.createLesson(2, 2, "saturday", 2,swimmingClass);
        this.createLesson(2, 2, "sunday", 1,pilateClass);
        this.createLesson(2, 2, "sunday", 2,rockClimbingClass);
        this.createLesson(2, 3, "saturday", 1, yogaClass);
        this.createLesson(2, 3, "saturday", 2,swimmingClass);
        this.createLesson(2, 3, "sunday", 1,zumbaClass);
        this.createLesson(2, 3,"sunday", 2,rockClimbingClass);
        this.createLesson(2, 4, "saturday", 1,yogaClass);
        this.createLesson(2, 4, "saturday", 2,swimmingClass);
        this.createLesson(3, 4, "sunday", 1,pilateClass);
        this.createLesson(3, 4,"sunday", 2,rockClimbingClass);

        this.allFitnessTypes.add(yogaClass);
        this.allFitnessTypes.add(swimmingClass);
        this.allFitnessTypes.add(rockClimbingClass);
        this.allFitnessTypes.add(zumbaClass);
        this.allFitnessTypes.add(pilateClass);


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

        allLessons.putIfAbsent(month, new ArrayList<Lesson>());
        allLessons.get(month).add(lesson);
    }

}
