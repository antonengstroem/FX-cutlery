package FXcutlery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@AllArgsConstructor
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

        public final String label;

        CutOffTimeEnum(String label) {
            this.label = label;
        }
    }

    public enum CutOffDateEnum {
        TODAY("today"),
        TOMORROW("tomorrow"),
        AFTER_TOMORROW("after tomorrow");

        private final String relativeDate;

        CutOffDateEnum(String relativeDate) {
            this.relativeDate = relativeDate;
        }

        public static CutOffDateEnum fromString(String date) {
            for (CutOffDateEnum b : CutOffDateEnum.values()) {
                if (b.relativeDate.equalsIgnoreCase(date)) {
                    return b;
                }
            }
            return null;
        }
    }
}
