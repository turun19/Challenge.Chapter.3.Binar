package Main;

import org.junit.jupiter.api.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {
    String[] nilai = new String[]{"10", "9", "8", "7", "8", "9", "8", "6", "6", "7", "9", "8", "7", "9", "10", "9", "8",};
    String[] name = new String[]{"Fathur", "Rizqy", "Binar"};
    private final School schl = new School(Arrays.asList(name), Arrays.asList(nilai));
    private static final String GRADE_FILE_PATH = "D:\\Fathur Files\\Kuliah Semester 6\\Binarian Back End Java\\Chapter 3\\Challenge.Chapter.3.Binar\\res\\data_sekolah.csv";
    private static final List<School> schools = new ArrayList<>();
    private static final List<String> className = new ArrayList<>();
    private static final List<String> gradeByClass = new ArrayList<>();

    private static void readFile() throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(GRADE_FILE_PATH);
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            List<String> strArr = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                var list = List.of(line.split(";"));
                strArr.addAll(list);
                var name = strArr.get(0);
                className.add(name);
                strArr.remove(0);
                schools.add(new School(Collections.singletonList(name), strArr));
                gradeByClass.addAll(strArr);
                strArr.clear();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) br.close();
        }
    }

    @Test
    public void getMeanTest() throws IOException {
        readFile();
        assertEquals(8.0, schl.getMean());

    }

    @Test
    public void TestMedian() throws IOException {
        readFile();
        assertEquals("8", schl.getMedian());

    }

    @Test
    public void getModusTest() throws IOException {
        readFile();
        assertEquals("8 (5)", schl.getModus());
    }

}