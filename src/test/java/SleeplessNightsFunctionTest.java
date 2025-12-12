import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class SleeplessNightsFunctionTest {

    @Test
    void testNoSleeplessNights() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0),
                        "GOOD"
                ),
                new SleepSession(
                        LocalDateTime.of(2025, 10, 2, 22, 30),
                        LocalDateTime.of(2025, 10, 3, 6, 30),
                        "NORMAL"
                )
        );

        SleeplessNightsFunction func = new SleeplessNightsFunction();
        assertEquals(0L, func.apply(sessions));
    }

    @Test
    void testOneSleeplessNight() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0),
                        "GOOD"
                ),
                // Нет сна 2–3 октября (ночь между 2 и 3 октября)
                new SleepSession(
                        LocalDateTime.of(2025, 10, 3, 22, 30),
                        LocalDateTime.of(2025, 10, 4, 6, 30),
                        "NORMAL"
                )
        );

        SleeplessNightsFunction func = new SleeplessNightsFunction();
        assertEquals(0L, func.apply(sessions));
    }
}