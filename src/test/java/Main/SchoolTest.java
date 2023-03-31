package Main;

import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
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
    @DisplayName("Test Median Positive")
    public void getMeanTest() throws IOException {
        readFile();
        School schl = new School(className, gradeByClass);
        assertEquals(8.318181818181818, schl.getMean());

    }

    @Test
    @DisplayName("Test Median Positive")
    public void getMedianTest() throws IOException {
        readFile();
        School schl = new School(className, gradeByClass);
        assertEquals("8", schl.getMedian());

    }

    @Test
    @DisplayName("Test Median Positive")
    public void getModusTest() throws IOException {
        readFile();
        School schl = new School(className, gradeByClass);
        assertEquals("7 (62)", schl.getModus());
    }

    @Test
    @DisplayName("Test Median Negative")
    public void testMedianError() throws IOException {
        readFile();
        School schl = new School(className, gradeByClass);;
        assertThrows(AssertionFailedError.class, ()->{
            assertEquals(90, schl.getMean());
        });
    }

    @Test
    @DisplayName("Test Modus Negative")
    public void testModusError() throws IOException {
        readFile();
        School schl = new School(className, gradeByClass);
        assertThrows(AssertionFailedError.class, ()->{
            assertEquals("10", schl.getModus());
        });
    }

    @Test
    @DisplayName("Test Mean Negative")
    public void testMeanError() throws IOException {
        readFile();
        School schl = new School(className, gradeByClass);
        assertThrows(AssertionFailedError.class, ()->{
            assertEquals(90, schl.getMean());

        });
    }

}