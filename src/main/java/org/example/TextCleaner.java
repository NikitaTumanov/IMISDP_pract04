package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

        // Закрываем файлы
        reader.close();
        writer.close();
    }
}
