import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class TotalSessionsFunctionTest {

    @Test
    void testTotalSessions() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(LocalDateTime.now(), LocalDateTime.now().plusHours(8), "GOOD"),
                new SleepSession(LocalDateTime.now(), LocalDateTime.now().plusHours(7), "NORMAL")
        );

        TotalSessionsFunction func = new TotalSessionsFunction();
        assertEquals(2, func.apply(sessions));
    }
}
