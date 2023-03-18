package FXcutlery.services;

import FXcutlery.exceptionhandling.IsoNotTradeableOnDateException;
import FXcutlery.models.Currency;
import FXcutlery.repository.CutOffTimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CutOffTimeServiceImplTest {

    @Mock
    private static CutOffTimeRepository cutOffTimeRepository;

    @InjectMocks
    private CutOffTimeServiceImpl cutOffTimeService;

    private final Currency currencyAED = new Currency("aed", "united arab emirates", "never possible",
            "14:00", "always possible");
    private final Currency currencyHUF = new Currency("huf", "Hungary", "11:00", "always possible",
            "always possible");

    @BeforeEach
    void setUp() {
        when(cutOffTimeRepository.findById("aed")).thenReturn(Optional.of(currencyAED));
        when(cutOffTimeRepository.findById("huf")).thenReturn(Optional.of(currencyHUF));

    }
    @Test
    void getCutOffTimeTodayCurrencyCannotBeTradedOnDate() {
        final IsoNotTradeableOnDateException e = assertThrows(IsoNotTradeableOnDateException.class, () ->
                cutOffTimeService.getCutOffTime("aed", "huf", "today"));

        assertEquals(e.getMessage(), "ISO 'aed' cannot be traded on chosen date");
    }

    @Test
    void getCutOffTimeToday() {
        assertEquals(cutOffTimeService.getCutOffTime("aed", "huf", "tomorrow").toString(), "14:00");
    }
}
