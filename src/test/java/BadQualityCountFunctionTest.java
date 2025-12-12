import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class BadQualityCountFunctionTest {

    @Test
    void testBadQualityCount() {
        List<SleepSession> sessions = Arrays.asList(
                new SleepSession(LocalDateTime.now(), LocalDateTime.now().plusHours(8), "BAD"),
                new SleepSession(LocalDateTime.now(), LocalDateTime.now().plusHours(7), "NORMAL"),
                new SleepSession(LocalDateTime.now(), LocalDateTime.now().plusHours(6), "BAD")
        );

        BadQualityCountFunction func = new BadQualityCountFunction();
        assertEquals(2L, func.apply(sessions));
    }
}
