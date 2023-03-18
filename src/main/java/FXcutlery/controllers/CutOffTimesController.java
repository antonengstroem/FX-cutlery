package FXcutlery.controllers;

import FXcutlery.dto.CurrencyDto;
import FXcutlery.interfaces.CutOffTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class CutOffTimesController {
    @Autowired
    private CutOffTimeService service;

    @GetMapping("/cutoff-time")
    public ResponseEntity<CurrencyDto> cutOffTime(@RequestParam("date") String date,
                           @RequestParam("from") String isoFrom,
                           @RequestParam("to") String isoTo) {
        final String cutOffTimeToday = service.getCutOffTime(isoFrom, isoTo, date).toString();
        return ResponseEntity.ok().body(new CurrencyDto(cutOffTimeToday));
    }
}
