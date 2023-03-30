package Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classroom implements Calculation{
    private final String name;
    private final List<String> studentsGrade;
    private final Map<String, Integer> grades = new HashMap<>();

    public Classroom(String name, List<String> studentsGrade) {
        this.name           = name;
        // sort grade toi find median
        studentsGrade       = sortAsNumbers(studentsGrade);
        this.studentsGrade  = studentsGrade;

        for(String i: studentsGrade){
            if(grades.containsKey(i)) grades.put(i, grades.get(i) + 1);
            else grades.put(i, 1);
        }
    }

    public String generateClassTxt(){
        return "Nama Kelas\t: ".concat(name).concat("\n") +
                "Rata-Rata\t: ".concat(String.valueOf(getMean()).concat("\n")) +
                "Modus(n)\t: ".concat(String.valueOf(getModus())).concat("\n") +
                "Median\t\t: ".concat(String.valueOf(getMedian())).concat("\n\n");
    }

    @Override
    public double getMean(){
        double value = 0.0;
        double bulat = 0;
        var count = studentsGrade.size();

        for(String str: studentsGrade){
            value += Integer.parseInt(str);
        }
        double pembagian = value/count;
        double round = Math.round(pembagian);

        return (count > 0) ? round : 0;
    }

    @Override
    public String getModus(){
        var maxVal = 0;
        var maxKey = "";
        for (Map.Entry<String,Integer> entry : grades.entrySet()) {
            if(maxVal < entry.getValue()){
                maxVal = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        return maxKey + " (" + maxVal + ")";
    }

    @Override
    public String getMedian() {
        int mid = studentsGrade.size()/2;
        return studentsGrade.get(mid);
    }

}
