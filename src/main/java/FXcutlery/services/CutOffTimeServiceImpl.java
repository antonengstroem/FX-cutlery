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
    private static final String ISO_CANNOT_BE_TRADED_ON_DATE_MESSAGE = "Currency with ISO '%s' cannot be traded on chosen date";
    private static final String ILLEGAL_DATE_VALUE_MESSAGE = "Illegal date value, only 'today', 'tomorrow' or 'after tomorrow' allowed";
    private static final String BASE_ISO_DOES_NOT_EXIST = "Base currency ISO: '%s' does not exist";
    private static final String QUOTE_ISO_DOES_NOT_EXIST = "Quote currency ISO: '%s' does not exist";

    public LocalTime getCutOffTime(String baseIso, String quoteIso, String date) throws IllegalArgumentException {
        final Currency baseCurrency = repo.findById(baseIso.toLowerCase()).orElseThrow(
                        () -> new InvalidIsoCodeException(String.format(BASE_ISO_DOES_NOT_EXIST, baseIso)));

        final Currency quoteCurrency = repo.findById(quoteIso.toLowerCase()).orElseThrow(
                () -> new InvalidIsoCodeException(String.format(QUOTE_ISO_DOES_NOT_EXIST, quoteIso)));

        final String unparsedTimeOfBase = getCutOffTimeOfCurrencyByDate(baseCurrency, date);
        final String unparsedTimeOfQuote = getCutOffTimeOfCurrencyByDate(quoteCurrency, date);

        final LocalTime parsedTimeOfBase = parseToDigitalTime(unparsedTimeOfBase, baseIso);
        final LocalTime parsedTimeOfQuote = parseToDigitalTime(unparsedTimeOfQuote, quoteIso);

        return parsedTimeOfBase.compareTo(parsedTimeOfQuote) < 0 ? parsedTimeOfBase : parsedTimeOfQuote;
    }

    private LocalTime parseToDigitalTime(String unparsedTime, String iso) {
        if(unparsedTime.toLowerCase().equals(Currency.CutOffTimeEnum.NEVER_POSSIBLE.label)) {
            throw new IsoNotTradeableOnDateException(String.format(ISO_CANNOT_BE_TRADED_ON_DATE_MESSAGE, iso));
        }

        return unparsedTime.toLowerCase().equals(Currency.CutOffTimeEnum.ALWAYS_POSSIBLE.label) ? LocalTime.MAX
                : LocalTime.parse(unparsedTime);
    }

    private String getCutOffTimeOfCurrencyByDate(Currency currency, String date) throws IllegalArgumentException {
        final Currency.CutOffDateEnum cutOffDateEnum = Currency.CutOffDateEnum.fromString(date);

        if (cutOffDateEnum == null) {
            throw new InvalidDateException(ILLEGAL_DATE_VALUE_MESSAGE);
        }

        switch (cutOffDateEnum) {
            case TODAY:
                return currency.getToday();
            case TOMORROW:
                return currency.getTomorrow();
            case AFTER_TOMORROW:
                return currency.getAfterTomorrow();
            default:
                throw new InvalidDateException(ILLEGAL_DATE_VALUE_MESSAGE);
        }
    }
}
