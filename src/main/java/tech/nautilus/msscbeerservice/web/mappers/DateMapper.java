package tech.nautilus.msscbeerservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("UTC"));
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(LocalDateTime.ofInstant(offsetDateTime.toInstant(), ZoneOffset.UTC));
    }
}
