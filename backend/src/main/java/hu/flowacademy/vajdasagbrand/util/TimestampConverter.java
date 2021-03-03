package hu.flowacademy.vajdasagbrand.util;

import com.google.cloud.Timestamp;
import lombok.NonNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public final class TimestampConverter {
    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(ldt -> Timestamp.of(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()))).orElse(null);
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(ts -> ts.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
    }
}
