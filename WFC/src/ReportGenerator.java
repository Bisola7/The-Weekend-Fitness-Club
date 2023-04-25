import java.util.ArrayList;
import java.util.HashMap;

public class ReportGenerator {
    private Timetable timetable;

    public ReportGenerator(Timetable timetable) {
        this.timetable = timetable;
    }

    public String getMonthlyReport(int month){
        ArrayList<Lesson> allForMonth = timetable.getAllLessonsByMonth(month);
        StringBuilder report = new StringBuilder();
        for (Lesson lesson : allForMonth){
            report.append(lesson.changeLessonToString()).append("\n").append("Attendance: ").append(lesson.getAttendance()).append("---------").append("Average Rating: ").append(lesson.getAverageRating()).append("\n");
        }

        return report.toString();
    }


    public String getChampionReport(int month){
        ArrayList<FitnessType> allFitnessTypes = timetable.getAllFitnessTypes();
        String report = "";
        FitnessType champion = allFitnessTypes.get(0);
        for (FitnessType type : allFitnessTypes){
            if (type.getTotalEarningByMonth(month) > champion.getTotalEarningByMonth(month)){
                champion = type;
            }
        }

        report += "The Fitness Type with the highest earning is: " + champion.getName() + " earning: £" + champion.getTotalEarningByMonth(month) + "\n Earning for other types are:\n";

        for (FitnessType type : allFitnessTypes){
            //if (type.getTotalEarningByMonth(month) > champion.getTotalEarningByMonth(month)){
                if(!type.equals(champion)){
                    report += type.getName() + ": GBP" + type.getTotalEarningByMonth(month) + "\n";
                }
            //}
        }

        return report;
    }
}