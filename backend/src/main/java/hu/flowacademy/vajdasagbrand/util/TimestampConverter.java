package hu.flowacademy.vajdasagbrand.util;

import com.google.cloud.Timestamp;
import lombok.NonNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class TimestampConverter {
    public static Timestamp toTimestamp(@NonNull LocalDateTime localDateTime) {
        return Timestamp.of(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static LocalDateTime toLocalDateTime(@NonNull Timestamp timestamp) {
        return timestamp.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
