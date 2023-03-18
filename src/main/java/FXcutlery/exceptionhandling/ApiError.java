package FXcutlery.exceptionhandling;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    private HttpStatusCode httpStatusCode;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(HttpStatusCode httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
