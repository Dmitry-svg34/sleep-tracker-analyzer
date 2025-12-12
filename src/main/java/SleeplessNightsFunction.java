import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SleeplessNightsFunction implements AnalysisFunction {
    @Override
    public Object apply(List<SleepSession> sessions) {
        Set<LocalDate> sleptNights = sessions.stream()
                .filter(session -> isNightSession(session))
                .map(session -> session.getStart().toLocalDate())
                .collect(Collectors.toSet());


        if (sleptNights.isEmpty()) return 0L;

        LocalDate minDate = sleptNights.stream().min(LocalDate::compareTo).get();
        LocalDate maxDate = sleptNights.stream().max(LocalDate::compareTo).get();

        long totalNights = java.time.temporal.ChronoUnit.DAYS.between(minDate, maxDate);
        return totalNights - sleptNights.size();
    }

    private boolean isNightSession(SleepSession session) {
        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();
        return start.isBefore(LocalTime.of(6, 0)) || end.isAfter(LocalTime.of(22, 0));
    }

    @Override
    public String getName() {
        return "Number of sleepless nights (00:00–06:00 without sleep)";
    }
}
