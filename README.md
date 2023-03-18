# FXcutlery REST API documentation:

This api returns cut-off times for currency pairs on a given date.

## Endpoints

'GET /cutoff-time'

### Request Parameters
The definitions uses following format: [Parameter name, Type, Description, Required].
[date, String, The date for which the cut-off time is requested. Accepts 'today',
    'tomorrow', or 'after%20tomorrow', Required]
[from, String, The ISO code of the base currency, Required]
[to, String, The ISO code of the quote currency, Required]

### Response
The API returns a JSON object. If successful the json contains the cut off time for the currency pair on the given day.
In the case that there is no cut off time for the given currency pair, the java.time.LocalTime.MAX value
will be returned. This is to streamline response data handling.

See examples below:
    1. successful call:
        URI: /cutoff-time?date=tomorrow&from=cnh&to=dkk
        JSON: {"cutOffTime":"14:00"}
    2. successful call where one of the currencies are not tradable on given day:
        URI: /cutoff-time?date=today&from=cnh&to=dkk
        JSON {"httpStatusCode":"BAD_REQUEST","message":"ISO 'cnh' cannot be traded on chosen date","timestamp":"2023-03-18T13:27:54.8621885"}
    3. failed call, invalid date parameter:
        URI: /cutoff-time?date=storebededag&from=cnh&to=dkk
        JSON: {"httpStatusCode":"BAD_REQUEST","message":"Illegal date value, only 'today', 'tomorrow' or 'after tomorrow' allowed","timestamp":"2023-03-18T13:30:59.2840064"}
    4. successful call where neither currency have a cut off time for the given date:
        URI: /cutoff-time?date=after%20tomorrow&from=pln&to=bgn
        JSON: {"cutOffTime":"23:59:59.999999999"}

## Error Handling
The API will return an error object with an HTTP status code, an error message and a timestamp if an error occurs.

In the case where neither base nor quote currency is tradable, only the base currency will stated
as the cause of the error.

The following error Scenarios are handled:

1. Invalid 'date' parameter value.
2. Invalid 'from' or/or 'to' values.
3. ISO not tradable on selected date
