package FXcutlery.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidIsoCodeException.class)
    public ResponseEntity<ApiError> handleinvalidIsoCodeException(InvalidIsoCodeException invalidIsoCodeException) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, invalidIsoCodeException.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ApiError> handleinvalidDateException(InvalidDateException invalidDateException) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, invalidDateException.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsoNotTradeableOnDateException.class)
    public ResponseEntity<ApiError> handleIsoNotTradeableOnDateException(
            IsoNotTradeableOnDateException isoNotTradeableOnDateException) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, isoNotTradeableOnDateException.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
