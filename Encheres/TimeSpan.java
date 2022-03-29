import java.time.*;

public class TimeSpan {
  Duration time_span;

  public TimeSpan(int minutes) {
    time_span = Duration.ofMinutes(minutes);
  }
  public TimeSpan(LocalDateTime start, LocalDateTime end) {
    time_span = Duration.between(start, end);
  }

  public TimeSpan(Duration duration) {
    time_span = duration;
  }

  public long inMinutes() {
    return this.time_span.toMinutes();
  }
}
