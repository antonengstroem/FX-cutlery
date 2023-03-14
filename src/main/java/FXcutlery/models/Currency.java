package FXcutlery.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "currency_cutoff")
public class Currency {
    @Id
    private String iso;
    private String country;
    private String today;
    private String tomorrow;
    private String afterTomorrow;

    public enum CutOffTimeEnum {
        NEVER_POSSIBLE("never possible"),
        ALWAYS_POSSIBLE("always possible");

        private String possible;

        private CutOffTimeEnum(String whenPossible) {
            this.possible = whenPossible;
        }
    }
}
