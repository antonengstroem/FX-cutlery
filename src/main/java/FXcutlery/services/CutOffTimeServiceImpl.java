package FXcutlery.services;

import FXcutlery.interfaces.CutOffTimeService;
import FXcutlery.models.Currency;

import java.time.LocalTime;

public class CutOffTimeServiceImpl implements CutOffTimeService {
    public LocalTime getCutOffTime(String cutOffTime1, String cutOffTime2) {
        var time1 = cutOffTime1.equals(Currency.CutOffTimeEnum.ALWAYS_POSSIBLE) ? LocalTime.parse("24:00")
                : LocalTime.parse(cutOffTime1);
        var time2 = cutOffTime1.equals(Currency.CutOffTimeEnum.ALWAYS_POSSIBLE) ? LocalTime.parse("24:00")
                : LocalTime.parse(cutOffTime2);
        return time1.compareTo(time2) < 0 ? time1 : time2;
    }
}
