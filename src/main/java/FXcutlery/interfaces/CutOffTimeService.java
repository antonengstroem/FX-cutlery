package FXcutlery.interfaces;

import java.time.LocalTime;

public interface CutOffTimeService {
    LocalTime getCutOffTime(String isoFrom, String isoTo, String date);
}
