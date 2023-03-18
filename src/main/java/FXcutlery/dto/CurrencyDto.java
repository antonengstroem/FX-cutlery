package FXcutlery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDto {
    private String cutOffTime;

    public CurrencyDto(String cutOffTime) {
        this.cutOffTime = cutOffTime;
    }
}
