package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCleaner {

    public static void main(String[] args) {
        String inputFile = "src/main/java/org/example/input.txt";
        String outputFile = "src/main/java/org/example/output.txt";

        try {
            cleanConfidentialData(inputFile, outputFile);
            System.out.println("Текст успешно очищен от конфиденциальных данных.");
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }

    private static void cleanConfidentialData(String inputFile, String outputFile) throws IOException {
        // Открываем файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        // Открываем файл для записи
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            // Заменяем конфиденциальные данные в текущей строке
            String cleanedLine = cleanLine(line);

            // Записываем очищенную строку в выходной файл
            writer.write(cleanedLine);
            writer.newLine();
        }

        // Закрываем файлы
        reader.close();
        writer.close();
    }

    private static String cleanLine(String line) {
        // Задаем шаблоны для поиска конфиденциальных данных
        Pattern namePattern = Pattern.compile("[A-Z][a-z]+\\s[A-Z][a-z]+"); // Поиск имени и фамилии

        Matcher matcher = namePattern.matcher(line);
        line = matcher.replaceAll("[censored]");

        return line;
    }
}
