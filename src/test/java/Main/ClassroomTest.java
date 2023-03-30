package Main;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.internal.util.io.IOUtil;
import org.opentest4j.AssertionFailedError;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ClassroomTest {
    String[] nilai = new String[]{"10", "9", "8", "7", "8", "9", "8", "6", "6", "7", "9", "8", "7", "9", "10", "9", "8", "7", "7", "6", "7", "8", "9", "10", "9", "8", "6", "7"};
    private static final String GRADE_FILE_PATH = "D:\\Fathur Files\\Kuliah Semester 6\\Binarian Back End Java\\Chapter 3\\Challenge.Chapter.3.Binar\\res\\data_sekolah.csv";
    private static final List<Classroom> classroomList = new ArrayList<>();
    private static final List<String> className = new ArrayList<>();
    private static final List<String> gradeByClass = new ArrayList<>();
    private static String name;




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
                name = strArr.get(0);
                className.add(name);
                strArr.remove(0);
                classroomList.add(new Classroom(name, strArr));
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
    @DisplayName("Test Mean Positif")
    public void getMeanTest() throws IOException {
        readFile();
        Classroom cls = new Classroom(className.get(5), gradeByClass);
        assertEquals(8.318181818181818, cls.getMean());

    }

    @Test
    @DisplayName("Test Median Positif")
    public void getMedianTest() throws IOException {
        readFile();
        Classroom cls = new Classroom(name, gradeByClass);
        assertEquals("8", cls.getMedian());

    }

    @Test
    @DisplayName("Test Modus Positif")
    public void getModusTest() throws IOException {
        readFile();
        Classroom cls = new Classroom(className.get(0), gradeByClass);
        cls.generateClassTxt();
        assertEquals("7 (62)", cls.getModus());
    }

    @Test
    @DisplayName("Test Median Negative")
    public void testMedianError() throws IOException {
        readFile();
        Classroom cls = new Classroom("Fathur", gradeByClass);
        assertThrows(AssertionFailedError.class, ()->{
           assertEquals(90, cls.getMean());
        });
    }

    @Test
    @DisplayName("Test Modus Negative")
    public void testModusError() throws IOException {
        readFile();
        Classroom cls = new Classroom("Fathur", gradeByClass);
        assertThrows(AssertionFailedError.class, ()->{
            assertEquals("10", cls.getModus());
        });
    }

    @Test
    @DisplayName("Test Mean Negative")
    public void testMeanError() throws IOException {
        readFile();
        Classroom cls = new Classroom("Fathur", gradeByClass);
        assertThrows(AssertionFailedError.class, ()->{
            assertEquals(90, cls.getMean());

        });
    }
}
