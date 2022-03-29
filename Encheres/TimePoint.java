import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class TimePoint {
  LocalDateTime time_point;

  public TimePoint() {
    time_point = LocalDateTime.now();
  }

  // Le format de date : "YYYY-MM-DD HH:MM"
  public TimePoint(String _time_point) {
    int year = Integer.parseInt(_time_point.substring(0,4));
    int month = Integer.parseInt(_time_point.substring(5,7));
    int day = Integer.parseInt(_time_point.substring(8,10));
    int hour = Integer.parseInt(_time_point.substring(11,13));
    int minute = Integer.parseInt(_time_point.substring(14));
    time_point = LocalDateTime.of(year, month, day, hour, minute);
  }

  public TimePoint(LocalDateTime _time_point) {
    time_point = _time_point;
  }

  public static TimePoint now() {
    return new TimePoint();
  }

  public String toSqlDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formatDateTime = time_point.format(formatter);
    return formatDateTime;
  }

  public TimePoint plus(TimeSpan time_span) {
    return new TimePoint(this.time_point.plusMinutes(time_span.inMinutes()));
  }

  public TimePoint minus(TimeSpan time_span) {
    return new TimePoint(this.time_point.minusMinutes(time_span.inMinutes()));
  }

  public TimeSpan minus(TimePoint next_time_point) {
    return new TimeSpan(Duration.between(this.time_point, next_time_point.getLocalDateTime()));
  }

  public TimeSpan difference(TimePoint next_time_point) {
    return new TimeSpan(Duration.between(this.time_point, next_time_point.getLocalDateTime()));
  }

  public LocalDateTime getLocalDateTime() {
    return this.time_point;
  }

  public boolean isAfter(TimePoint next_time_point) {
    if(this.time_point.isAfter(next_time_point.getLocalDateTime())) {
      return true;
    }
    return false;
  }

}
