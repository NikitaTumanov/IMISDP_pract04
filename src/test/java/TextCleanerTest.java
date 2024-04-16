import org.example.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class TextCleanerTest {

    @Test
    public void testReplaceNames() {
        String inputLine = "Hello, my name is John Doe and I live in New York.";
        String expectedLine = "Hello, my name is [censored] and I live in [censored].";
        String actualLine = TextCleaner.replaceNames(inputLine);
        assertEquals(expectedLine, actualLine);
    }

    @Test
    public void testReplacePhoneNumbers() {
        String inputLine = "Please call me at 123-456-7890 for further details.";
        String expectedLine = "Please call me at [censored] for further details.";
        String actualLine = TextCleaner.replacePhoneNumbers(inputLine);
        assertEquals(expectedLine, actualLine);
    }

    @Test
    public void testReplaceLocations() {
        String inputLine = "I visited Paris, France and Tokyo, Japan last summer.";
        String expectedLine = "I visited [censored], [censored] and [censored], [censored] last summer.";
        String actualLine = TextCleaner.replaceLocations(inputLine);
        assertEquals(expectedLine, actualLine);
    }

    @Test
    public void testCleanConfidentialData() throws IOException {
        // Создаем временные файлы для теста
        File inputFile = File.createTempFile("input", ".txt");
        File outputFile = File.createTempFile("output", ".txt");

        // Записываем входные данные во временный файл
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write("I am John Doe, my phone number is 123-456-7890. I live in New York.");
        writer.close();

        // Вызываем метод cleanConfidentialData для временных файлов
        TextCleaner.cleanConfidentialData(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        // Считываем очищенные данные из выходного файла
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));
        String cleanedLine = reader.readLine();
        reader.close();

        // Ожидаемый результат после очистки
        String expectedLine = "I am [censored], my phone number is [censored]. I live in [censored].";

        // Проверяем, что очищенная строка соответствует ожидаемому результату
        assertEquals(expectedLine, cleanedLine);

        // Удаляем временные файлы после теста
        inputFile.delete();
        outputFile.delete();
    }
}
