import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class MaxDurationFunctionTest {

    @Test
    void testMaxDuration() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(
                        LocalDateTime.of(2025, 10, 1, 22, 15),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        "GOOD"
                ), // 585 минут
                new SleepSession(
                        LocalDateTime.of(2025, 10, 3, 14, 30),
                        LocalDateTime.of(2025, 10, 3, 15, 20),
                        "NORMAL"
                ) // 50 минут
        );

        MaxDurationFunction func = new MaxDurationFunction();
        assertEquals(585L, func.apply(sessions));
    }
}
