import java.util.List;

public interface AnalysisFunction {
    Object apply(List<SleepSession> sessions);
    String getName();
}