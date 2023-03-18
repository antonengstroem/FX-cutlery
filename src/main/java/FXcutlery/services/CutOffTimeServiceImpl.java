package FXcutlery.services;

import FXcutlery.exceptionhandling.InvalidDateException;
import FXcutlery.exceptionhandling.InvalidIsoCodeException;
import FXcutlery.exceptionhandling.IsoNotTradeableOnDateException;
import FXcutlery.interfaces.CutOffTimeService;
import FXcutlery.models.Currency;
import FXcutlery.repository.CutOffTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class CutOffTimeServiceImpl implements CutOffTimeService {
    @Autowired
    private CutOffTimeRepository repo;

    public LocalTime getCutOffTime(String isoFrom, String isoTo, String date) throws IllegalArgumentException {
        final Currency currencyFrom = repo.findById(isoFrom.toLowerCase()).orElseThrow(
                        () -> new InvalidIsoCodeException(String.format("ISO 'from' value: '%s' does not exist", isoFrom)));

        final Currency currencyTo = repo.findById(isoTo.toLowerCase()).orElseThrow(
                () -> new InvalidIsoCodeException(String.format("ISO 'to' value: '%s' does not exist", isoTo)));

        final String unparsedTimeIsoFrom = getTimeFromCurrencyByDate(currencyFrom, date);
        final String unparsedTimeIsoTo = getTimeFromCurrencyByDate(currencyTo, date);

        final LocalTime parsedTimeFrom = parseToDigitalTime(unparsedTimeIsoFrom, isoFrom);
        final LocalTime parsedTimeTo = parseToDigitalTime(unparsedTimeIsoTo, isoTo);

        return parsedTimeFrom.compareTo(parsedTimeTo) < 0 ? parsedTimeFrom : parsedTimeTo;
    }

    private LocalTime parseToDigitalTime(String unparsedTime, String iso) {
        if(unparsedTime.toLowerCase().equals(Currency.CutOffTimeEnum.NEVER_POSSIBLE.label)) {
            throw new IsoNotTradeableOnDateException(String.format("ISO '%s' cannot be traded on chosen date", iso));
        }

        return unparsedTime.toLowerCase()
                .equals(Currency.CutOffTimeEnum.ALWAYS_POSSIBLE.label) ? LocalTime.MAX
                : LocalTime.parse(unparsedTime);
    }

    private String getTimeFromCurrencyByDate(Currency currency, String date) throws IllegalArgumentException {
        final Currency.CutOffDateEnum cutOffDateEnum = Currency.CutOffDateEnum.fromString(date);

        if (cutOffDateEnum == null) {
            throw new InvalidDateException(
                    "Illegal date value, only 'today', 'tomorrow' or 'after tomorrow' allowed");
        }

        switch (cutOffDateEnum) {
            case TODAY:
                return currency.getToday();
            case TOMORROW:
                return currency.getTomorrow();
            case AFTER_TOMORROW:
                return currency.getAfterTomorrow();
            default:
                throw new InvalidDateException(
                        "Illegal date value, only 'today', 'tomorrow' or 'after tomorrow' allowed");
        }
    }
}
