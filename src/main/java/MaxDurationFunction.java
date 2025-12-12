import java.time.Duration;
import java.util.List;

public class MaxDurationFunction implements AnalysisFunction {
    @Override
    public Object apply(List<SleepSession> sessions) {
        return sessions.stream()
                .map(s -> Duration.between(s.getStart(), s.getEnd()).toMinutes())
                .max(Long::compareTo)
                .orElse(0L);
    }

    @Override
    public String getName() {
        return "Maximum session duration (minutes)";
    }
}
