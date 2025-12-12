import java.time.Duration;
import java.util.List;

public class MinDurationFunction implements AnalysisFunction {
    @Override
    public Object apply(List<SleepSession> sessions) {
        return sessions.stream()
                .map(s -> Duration.between(s.getStart(), s.getEnd()).toMinutes())
                .min(Long::compareTo)
                .orElse(0L);
    }

    @Override
    public String getName() {
        return "Minimum session duration (minutes)";
    }
}