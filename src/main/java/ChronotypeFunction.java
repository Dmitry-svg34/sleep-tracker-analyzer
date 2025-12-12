import java.time.LocalTime;
import java.util.List;

public class ChronotypeFunction implements AnalysisFunction {

    public enum Chronotype {
        JAWORONOK,  // Жаворонок: ложится до 22:00, встаёт до 07:00
        SOVA,       // Сова: ложится после 23:00, встаёт после 09:00
        GOLUB       // Голубь: промежуточный вариант
    }

    @Override
    public Object apply(List<SleepSession> sessions) {
        if (sessions.isEmpty()) {
            return Chronotype.GOLUB; // По умолчанию — «Голубь»
        }

        long jCount = sessions.stream().filter(this::isJaworonok).count();
        long sCount = sessions.stream().filter(this::isSova).count();
        long gCount = sessions.stream().filter(this::isGolub).count();

        // Определяем преобладающий хронотип
        if (jCount >= sCount && jCount >= gCount) {
            return Chronotype.JAWORONOK;
        }
        if (sCount >= jCount && sCount >= gCount) {
            return Chronotype.SOVA;
        }
        return Chronotype.GOLUB;
    }

    private boolean isJaworonok(SleepSession s) {
        LocalTime start = s.getStart().toLocalTime();
        LocalTime end = s.getEnd().toLocalTime();
        return start.isBefore(LocalTime.of(22, 0)) && end.isBefore(LocalTime.of(7, 0));
    }

    private boolean isSova(SleepSession s) {
        LocalTime start = s.getStart().toLocalTime();
        LocalTime end = s.getEnd().toLocalTime();
        return start.isAfter(LocalTime.of(23, 0)) && end.isAfter(LocalTime.of(9, 0));
    }

    private boolean isGolub(SleepSession s) {
        LocalTime start = s.getStart().toLocalTime();
        LocalTime end = s.getEnd().toLocalTime();


        // Проверяем, что время засыпания между 22:00 и 23:00 (включительно начало, исключая конец)
        boolean startInRange = start.isAfter(LocalTime.of(21, 59)) && start.isBefore(LocalTime.of(23, 0));
        // Проверяем, что время пробуждения между 07:00 и 09:00 (включительно начало, исключая конец)
        boolean endInRange = end.isAfter(LocalTime.of(6, 59)) && end.isBefore(LocalTime.of(9, 0));

        return startInRange || endInRange;
    }

    @Override
    public String getName() {
        return "Chronotype";
    }
}