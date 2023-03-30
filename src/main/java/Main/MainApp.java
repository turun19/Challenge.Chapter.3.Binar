package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final String GRADE_FILE_PATH = "D:\\Fathur Files\\Kuliah Semester 6\\Binarian Back End Java\\Chapter 3\\Challenge.Chapter.3.Binar\\res\\data_sekolah.csv";
    private static final String SCHOOL_FILE_PATH = "D:\\Fathur Files\\Kuliah Semester 6\\Binarian Back End Java\\Chapter 3\\Challenge.Chapter.3.Binar\\res\\data_sekolah.txt";
    private static final String CLASS_FILE_PATH = "D:\\Fathur Files\\Kuliah Semester 6\\Binarian Back End Java\\Chapter 3\\Challenge.Chapter.3.Binar\\res\\data_per_kelas.txt";
    private static final List<Classroom> classroomList = new ArrayList<>();
    private static final List<String> className = new ArrayList<>();
    private static final List<String> gradeByClass = new ArrayList<>();

    public static void main(String[] args) {
        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void menu() throws IOException {
        readFile();
        Scanner scan = new Scanner(System.in);
        String strip = "-".repeat(16);
        int choose;
        boolean kondisi = false;
        int pilih;
        do {
            System.out.println(strip);
            System.out.println("Aplikasi Pengolah Nilai Siswa");
            System.out.println(strip);
            System.out.println("File telah digenerate");
            System.out.println("Pilih Menu");
            System.out.println("1. Generate txt Untuk Menampilkan Nilai Kelas");
            System.out.println("2. Generate txt Untuk Menampilkan Nilai Sekolah");
            System.out.println("0. Exit");
            System.out.print("Tentukan Pilihan : ");
            choose = scan.nextInt();
            switch (choose) {
                case 1 -> {

                    var txt = new StringBuilder();
                    txt.append("Berikut Rekap Nilai Setiap Kelas:\n\n");
                    for(Classroom c: classroomList){
                        txt.append(c.generateClassTxt());
                    }
                    txt.append("*n = banyak data\n");
                    writeFile(CLASS_FILE_PATH, txt.toString());

                    pilih = showMenuFile(scan, strip, CLASS_FILE_PATH);
                    kondisi = pilih == 1;
                }
                case 2 -> {
                    School school = new School(className, gradeByClass);
                    writeFile(SCHOOL_FILE_PATH, school.writeSchoolTxt());
                    pilih = showMenuFile(scan, strip, SCHOOL_FILE_PATH);
                    kondisi = pilih == 1;
                }
                case 0 -> kondisi = true;
                default -> System.out.println("Pilihan Tidak Ada");
            }
        } while (kondisi);
    }

    private static int showMenuFile(Scanner scan, String strip, String pathFile) {
        int pilih;
        System.out.println(strip);
        System.out.println("Aplikasi Pengolah Nilai");
        System.out.println(strip);
        System.out.println("Generate txt untuk menampilkan nilai sudah selesai");
        System.out.println("File telah di generate di: " + pathFile + "\nsilahkan dicek");
        System.out.println(strip);
        System.out.println("\n0. Exit\n1. Kembali ke menu utama");
        System.out.print("Silahkan Pilih : ");
        pilih = scan.nextInt();
        return pilih;
    }


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

    public static void writeFile(String txtFile, String txt) throws IOException {
        BufferedWriter bwr = null;
        try {
            File file = new File(txtFile);
            if (file.createNewFile()) {
                System.out.println("new file is created");
            }
            FileWriter writer = new FileWriter(file);
            bwr = new BufferedWriter(writer);
            bwr.write(txt);
            bwr.newLine();
            bwr.flush();
            bwr.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        } finally {
            if (bwr != null) bwr.close();
        }
    }
}
