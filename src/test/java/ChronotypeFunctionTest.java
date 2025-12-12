import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class ChronotypeFunctionTest {

    @Test
    void testJaworonok() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(
                        LocalDateTime.of(2025, 10, 1, 21, 0),  // до 22:00
                        LocalDateTime.of(2025, 10, 2, 6, 0),   // до 07:00
                        "GOOD"
                ),
                new SleepSession(
                        LocalDateTime.of(2025, 10, 2, 20, 0),  // до 22:00
                        LocalDateTime.of(2025, 10, 3, 5, 0),    // до 07:00
                        "NORMAL"
                )
        );

        ChronotypeFunction func = new ChronotypeFunction();
        assertEquals(ChronotypeFunction.Chronotype.JAWORONOK, func.apply(sessions));
    }

    @Test
    void testSova() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),  // после 23:00
                        LocalDateTime.of(2025, 10, 2, 10, 0), // после 09:00
                        "GOOD"
                ),
                new SleepSession(
                        LocalDateTime.of(2025, 10, 2, 23, 5),   // после 23:00
                        LocalDateTime.of(2025, 10, 2, 9, 30), // после 09:00
                        "NORMAL"
                )
        );

        ChronotypeFunction func = new ChronotypeFunction();
        assertEquals(ChronotypeFunction.Chronotype.SOVA, func.apply(sessions));
    }

    @Test
    void testGolub() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(
                        LocalDateTime.of(2025, 10, 1, 22, 30), // между 22:00 и 23:00
                        LocalDateTime.of(2025, 10, 2, 8, 0),      // между 07:00 и 09:00
                        "GOOD"
                ),
                new SleepSession(
                        LocalDateTime.of(2025, 10, 2, 22, 45), // между 22:00 и 23:00
                        LocalDateTime.of(2025, 10, 3, 8, 15),     // между 07:00 и 09:00
                        "NORMAL"
                )
        );

        ChronotypeFunction func = new ChronotypeFunction();
        assertEquals(ChronotypeFunction.Chronotype.GOLUB, func.apply(sessions));
    }
}
