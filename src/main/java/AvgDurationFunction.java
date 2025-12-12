import java.time.Duration;
import java.util.List;

public class AvgDurationFunction implements AnalysisFunction {
    @Override
    public Object apply(List<SleepSession> sessions) {
        if (sessions.isEmpty()) return 0.0;
        long totalMinutes = sessions.stream()
                .mapToLong(s -> Duration.between(s.getStart(), s.getEnd()).toMinutes())
                .sum();
        return (long) Math.round(totalMinutes / sessions.size());
    }

    @Override
    public String getName() {
        return "Average session duration (minutes)";
    }
}
