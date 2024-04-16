package org.example;
import java.io.*;

public class TextCleaner {

    public static void main(String[] args) {
        String inputFile = "src/main/resources/input.txt";
        String outputFile = "src/main/resources/output.txt";

        try {
            cleanConfidentialData(inputFile, outputFile);
            System.out.println("Текст успешно очищен от конфиденциальных данных.");
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }

    public static void cleanConfidentialData(String inputFile, String outputFile) throws IOException {
        // Открываем файлы для чтения и записи
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            // Заменяем имена и фамилии на [censored]
            line = replaceNames(line);

            // Заменяем номера телефонов на [censored]
            line = replacePhoneNumbers(line);

            // Заменяем географические данные на [censored]
            line = replaceLocations(line);

            // Записываем очищенную строку в выходной файл
            writer.write(line);
            writer.newLine();
        }


    }

    public static String replaceNames(String line) {
        // Заменяем имена и фамилии (первая буква заглавная, за ней следуют одна или несколько букв)
        String regex = "\\b[A-Z][a-z]+\\s[A-Z][a-z]+\\b";
        return line.replaceAll(regex, "[censored]");
    }

    public static String replacePhoneNumbers(String line) {
        // Заменяем номера телефонов (формат xxx-xxx-xxxx)
        String regex = "\\b\\d{3}-\\d{3}-\\d{4}\\b";
        return line.replaceAll(regex, "[censored]");
    }

    public static String replaceLocations(String line) {
        // Заменяем географические данные (названия городов, штатов, стран и т.п.)
        String regex = "\\b([A-Z][a-z]+(\\s[A-Z][a-z]+)*)\\b";
        return line.replaceAll(regex, "[censored]");
    }
}
