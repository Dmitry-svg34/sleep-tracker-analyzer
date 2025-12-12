import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SleepTrackerApp <path-to-log-file>");
            return;
        }

        String filePath = args[0];
        List<SleepSession> sessions;

        try {
            sessions = readLogFile(filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Список всех аналитических функций
        List<AnalysisFunction> functions = Arrays.asList(
                new TotalSessionsFunction(),
                new MinDurationFunction(),
                new MaxDurationFunction(),
                new AvgDurationFunction(),
                new BadQualityCountFunction(),
                new SleeplessNightsFunction(),
                new ChronotypeFunction()
        );

        // Запуск всех функций и вывод результатов
        functions.forEach(func -> {
            Object result = func.apply(sessions);
            System.out.println(func.getName() + ": " + result);
        });
    }

    private static List<SleepSession> readLogFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .filter(line -> !line.trim().isEmpty())
                .map(SleepTrackerApp::parseLine)
                .collect(Collectors.toList());
    }

    private static SleepSession parseLine(String line) {
        String[] parts = line.split(";");
        LocalDateTime start = LocalDateTime.parse(parts[0], FORMATTER);
        LocalDateTime end = LocalDateTime.parse(parts[1], FORMATTER);
        String quality = parts[2].trim();

        return new SleepSession(start, end, quality);
    }
}
