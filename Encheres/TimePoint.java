public class TimePoint {
  LocalDateTime time_point;

  public TimePoint() {
    time_point = LocalDateTime.now();
  }

  // Le format de date : "YYYY-MM-DD HH:MM"
  public TimePoint(String _time_point) {
    int year = _time_pint.substring(0,4);
    int month = _time_pint.substring(5,7);
    int day = _time_pint.substring(8,10);
    int hour = _time_pint.substring(11,13);
    int minute = _time_pint.substring(14);
    time_point = LocalDateTime.of(year, month, day, hour, minute);
  }

  public static TimePoint now() {
    return LocalDateTime.now();
  }

  public String toSqlDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formatDateTime = time_point.format(formatter);
    return formatDateTime;
  }

  public TimePoint plus(TimeSpan time_span) {
    return this.time_point.plusMinutes(time_span.inMinutes());
  }

  public TimePoint minus(TimeSpan time_span) {
    return this.time_point.minusMinutes(time_span.inMinutes());
  }

  public TimePoint minus(TimePoint next_time_point) {
    return Duration.between(this.time_point, next_time_point);
  }

  public bool compareToUsing(TimePoint next_time_point, GE) {
    if(this.isAfter(next_time_point)) {
      return true;
    }
    return false;
  }

  public bool compareToUsing(TimePoint next_time_point, LT) {
    if(this.isBefore(next_time_point)) {
      return true;
    }
    return false;
  }
}
