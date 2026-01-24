package com.nlitvins.social_media.utils;

import java.time.LocalDateTime;

public class TimeUtils {

    public static LocalDateTime roundToMicros(LocalDateTime time) {
        long nanos = time.getNano();
        long micros = Math.round(nanos / 1_000.0);
        return time.withNano((int) (micros * 1_000));
    }

}
