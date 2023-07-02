import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HomeWork2 {
    public static void main(String[] args) throws SecurityException, IOException {
        myListType();

        ArrayList<Integer> res = (createArray(10, 15));
        System.out.println(res);
        System.out.println(bubbleSort(res));

        studentPerformance();
    }

    // Задание 1) Напишите метод, который определит тип (расширение) файлов из
    // текущей папки и выведет в консоль результат вида
    // 1 Расширение файла: txt
    // 2 Расширение файла: pdf
    // 3 Расширение файла:
    // 4 Расширение файла: jpg
    // метод, который определяет расширение файлов из текущей папки
    // и выводит в консоль результат
    static void myListType() {
        File folder = new File(".");
        File[] listOFiles = folder.listFiles();
        int count = 0;
        for (File file : listOFiles) {

            if (file.isFile()) {
                count += 1;
                String fileName = file.getName();
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                System.out.println(count + ".Расширение файла: " + extension);

            }
        }
    }

    // Задание 2) Реализуйте алгоритм сортировки пузырьком числового массива,
    // результат после каждой итерации запишите в лог-файл.
    // формирование числового массива
    static ArrayList<Integer> createArray(int n, int maxValue) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            list.add(random.nextInt(maxValue));
        return list;
    }

    // сортировка пузырьком числового массива
    static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) throws SecurityException, IOException {
        Logger logger = Logger.getLogger(HomeWork2.class.getName());
        FileHandler fh = new FileHandler("output.log");

        logger.addHandler(fh); // что куда сохр
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        System.out.println("Отсортированный массив: ");
        int k = arr.size();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
            try {
                FileWriter writer = new FileWriter("output.log", true);
                writer.write("Iteration " + (i + 1) + ": " + arr.toString() + "\n");
                writer.close();
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
        return arr;
    }

    // Доп.задание 3) Дана json-строка (можно сохранить в файл и читать из файла)
    // [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
    // {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
    // {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
    // Написать метод(ы), который распарсит json и, используя StringBuilder,
    // создаст строки вида:
    // Студент [фамилия] получил [оценка] по предмету [предмет].
    static void studentPerformance() {
        String jstring = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},\n" +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},\n" +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        // используется Pattern для создания регулярного выражения и компиляции его в
        // объект Pattern
        Pattern pattern = Pattern.compile("\\{\"фамилия\":\"(.*?)\",\"оценка\":\"(.*?)\",\"предмет\":\"(.*?)\"\\}");
        // создан объект Matcher для выполнения поиска в строке
        Matcher matcher = pattern.matcher(jstring);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String surname = matcher.group(1);
            String grade = matcher.group(2);
            String subject = matcher.group(3);
            sb.append("Студент ").append(surname)
                .append(" получил ").append(grade)
                .append(" по предмету ").append(subject).append(".\n");
        }
        System.out.println(sb.toString());
    }

}
