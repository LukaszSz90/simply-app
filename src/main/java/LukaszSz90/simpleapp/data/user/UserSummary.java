package LukaszSz90.simpleapp.data.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserSummary {

    private String username;
    private String firstName;
    private String lastName;
    @DateTimeFormat
    private LocalDate birthDate;
}
