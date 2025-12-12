import java.util.List;

public class TotalSessionsFunction implements AnalysisFunction {
    @Override
    public Object apply(List<SleepSession> sessions) {
        return sessions.size();
    }

    @Override
    public String getName() {
        return "Total sleep sessions";
    }
}