import java.util.List;

public class BadQualityCountFunction implements AnalysisFunction {
    @Override
    public Object apply(List<SleepSession> sessions) {
        return sessions.stream()
                .filter(s -> "BAD".equals(s.getQuality()))
                .count();
    }

    @Override
    public String getName() {
        return "Number of BAD quality sessions";
    }
}