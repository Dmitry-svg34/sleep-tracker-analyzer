import java.time.LocalDateTime;

public class SleepSession {
    private LocalDateTime start;
    private LocalDateTime end;
    private String quality;

    public SleepSession(LocalDateTime start, LocalDateTime end, String quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public String getQuality() { return quality; }
}
