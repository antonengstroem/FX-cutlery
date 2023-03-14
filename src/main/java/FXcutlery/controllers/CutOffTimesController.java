package FXcutlery.controllers;


import FXcutlery.interfaces.CutOffTimeService;
import FXcutlery.repository.CutOffTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class CutOffTimesController {
    @Autowired
    private CutOffTimeService service;
    @Autowired
    private CutOffTimeRepository repo;
    @GetMapping("/cutoff-time")
    public String greeting(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime date,
                           @RequestParam("from") String isoFrom,
                           @RequestParam("to") String isoTo) {


        var currencyFrom = repo.getReferenceById(isoFrom.toLowerCase());
        var currencyTo = repo.getReferenceById(isoTo.toLowerCase());
        var cutOffTimeToday = service.getCutOffTime(currencyFrom.getToday(), currencyTo.getToday());
        String formattedDateTime = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "currency time today: " +  cutOffTimeToday.toString();
    }
}
