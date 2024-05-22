package security.skyhawk.nbastats.processor;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerStatsDto {

    @NotEmpty(message = "'playerId' shouldn't be empty")
    String playerId;

    @NotEmpty(message = "'teamId' shouldn't be empty")
    String teamId;

    @NotEmpty(message = "'seasonId' shouldn't be empty")
    String seasonId;

    int points;

    @PositiveOrZero(message = "'rebounds' should be positive")
    int rebounds;

    @PositiveOrZero(message = "'assists' should be positive")
    int assists;

    @PositiveOrZero(message = "'steals' should be positive")
    int steals;

    @PositiveOrZero(message = "'blocks' should be positive")
    int blocks;

    @Min(value = 0, message = "'fouls' should be >= 0")
    @Max(value = 6, message = "'fouls' should be <= 6")
    int fouls;

    @PositiveOrZero(message = "'turnovers' should be positive")
    int turnovers;

    @Min(value = 0, message = "'minutesPlayed' should be >= 0")
    @Max(value = 48, message = "'minutesPlayed' should be >= 48")
    double minutesPlayed;
}
