package Main;

import java.util.*;

public class School implements Calculation{
    private final List<String> classesName;
    private final List<String> gradeByClass;
    private final Map<String, Integer> grades = new HashMap<>();

    public School(List<String> classesName, List<String> gradeByClass) {
        this.classesName    = classesName;

        // sort grade to find modus and median
        gradeByClass        = sortAsNumbers(gradeByClass);
        this.gradeByClass   = gradeByClass;

        for(String i: gradeByClass){
            if(grades.containsKey(i)) grades.put(i, grades.get(i) + 1);
            else grades.put(i, 1);
        }
    }

    @Override
    public double getMean() {
        double value = 0.0;
        var count = gradeByClass.size();
        for(String i: gradeByClass){
            value += Double.parseDouble(i);
        }
        double result = value / count;
        double round = Math.round(result);

        return round;
    }

    @Override
    public String getModus() {
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
    public String getMedian(){
        int mid = gradeByClass.size()/2;
        return gradeByClass.get(mid);
    }

    public String writeSchoolTxt(){
        StringBuilder str = new StringBuilder("Berikut Hasil Rekap Nilai Ujian Sekolah\n\n");

        str.append("Mean\t\t: ").append(getMean()).append("\n");
        str.append("Modus(n)\t: ").append(getModus()).append("\n");
        str.append("Median\t\t: ").append(getMedian()).append("\n\n");
        str.append("*n = banyak data\n\n");

        str.append("Sebaran Nilai Seluruh Siswa:\n");
        str.append("Nilai\t| Frekuensi\n");
        for (Map.Entry<String,Integer> entry : grades.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            str.append(key).append("\t\t| ").append(value).append("\n");
        }

        str.append("\nDaftar nama kelas:\n");
        for(String name: classesName){
            str.append("- ").append(name).append("\n");
        }

        return str.toString();
    }
}
