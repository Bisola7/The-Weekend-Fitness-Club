import java.util.ArrayList;
import java.util.HashMap;

public class ReportGenerator {

    private ArrayList<Lesson> lessons;

    public ReportGenerator(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void generateCustomersPerLessonReport() {
        HashMap<String, ArrayList<Integer>> customerCountPerLesson = new HashMap<>();

        for (Lesson lesson : lessons) {
            String dateKey = lesson.getMonth() + "/" + lesson.getDay();
            ArrayList<Integer> customerCountList = customerCountPerLesson.getOrDefault(dateKey, new ArrayList<>());
            customerCountList.add(lesson.getAttendance());
            customerCountPerLesson.put(dateKey, customerCountList);
        }

        System.out.println("Customers per lesson report:");
        for (String date : customerCountPerLesson.keySet()) {
            ArrayList<Integer> customerCountList = customerCountPerLesson.get(date);
            int totalCustomers = customerCountList.stream().reduce(0, Integer::sum);
            float averageRating = calculateAverageRatingForDate(date);

            System.out.println(date + ": " + totalCustomers + " customers, " + "average rating: " + averageRating);
        }
    }

    public void generateHighestIncomeReport() {
        HashMap<FitnessType, Integer> incomePerLessonType = new HashMap<>();

        for (Lesson lesson : lessons) {
            FitnessType lessonType = lesson.getFitnessType();
            int lessonPrice = lessonType.getPrice();
            int totalIncome = incomePerLessonType.getOrDefault(lessonType, 0) + lessonPrice;
            incomePerLessonType.put(lessonType, totalIncome);
        }

        FitnessType highestIncomeLessonType = null;
        int highestIncome = -1;

        for (FitnessType lessonType : incomePerLessonType.keySet()) {
            int totalIncome = incomePerLessonType.get(lessonType);
            if (totalIncome > highestIncome) {
                highestIncome = totalIncome;
                highestIncomeLessonType = lessonType;
            }
        }

        System.out.println("Highest income report:");
        System.out.println("The fitness type with the highest income is " + highestIncomeLessonType + " with a total income of " + highestIncome);
    }

    private float calculateAverageRatingForDate(String date) {
        float totalRating = 0;
        int totalLessons = 0;

        for (Lesson lesson : lessons) {
            if (lesson.getMonth() + "/" + lesson.getDay() == date && lesson.getAverageRating() > 0) {
                totalRating += lesson.getAverageRating();
                totalLessons++;
            }
        }

        if (totalLessons == 0) {
            return 0;
        } else {
            return totalRating / totalLessons;
        }
    }
}